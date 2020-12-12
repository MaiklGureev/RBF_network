package com.gureev.forecasting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NeuralNetwork {

    double[] input;
    double[] inputTest;

    List<Neuron> neurons;
    List<OutNeuron> outNeurons;

    int trainingWeightsCount = 0;
    int trainingCentersCount = 0;
    int windowSize = 0;

    public NeuralNetwork(double[] input, double[] inputTest) {
        this.input = input;
        this.inputTest = inputTest;
    }

    public void kMeans() {
        int startIndex = 0;
        int currentIndex;
        int countArrays = (int) Math.floor(input.length / windowSize);
        double[] arrayX;

        for (int a = 0; a < countArrays; a++) {
            currentIndex = startIndex + windowSize;

            arrayX = getArrayX(startIndex, currentIndex);

            //System.out.println(Arrays.toString(arrayX));

            int winningCenterId = 0;
            double weight = 0;
            double[] weightsArray = new double[neurons.size()];

            //цикл по нейронам
            for (int n = 0; n < neurons.size(); n++) {
                //подсчёт весов в нейроне
                neurons.get(n).arrayX = arrayX;
                weight = neurons.get(n).calcWeightForFindCenter();
                weightsArray[n] = weight;
            }

            //поиск id нейрона с минимальным весом
            winningCenterId = findWinningCenterId(weightsArray);
            //коррекция центров у нерона
            neurons.get(winningCenterId).kMeansCorrection();

            startIndex = currentIndex;
        }
    }

    public void train() {

        printNeurons();
        for (int t = 0; t < trainingCentersCount; t++) {
            kMeans();
        }
        System.out.println("################################################################################################################################################");
        calcSigma();
        printNeurons();

        for (int t = 0; t < trainingWeightsCount; t++) {

            int currentIndex = windowSize;
            double[] arrayX = getArrayX(0, currentIndex);
            outNeurons.get(0).mse = 0;
            double[] forecastingArray = new double[input.length];

            while (currentIndex != input.length) {
                //цикл по нейронам
                for (int n = 0; n < neurons.size(); n++) {
                    neurons.get(n).arrayX = arrayX;
                }
                outNeurons.get(0).d = input[currentIndex];

                outNeurons.get(0).calcGaussianRadFunctionsForHideNeurons();
                outNeurons.get(0).recalculateWeightsForNeurons();
                outNeurons.get(0).calcLocalMSE();

                forecastingArray[currentIndex] = outNeurons.get(0).y;

                arrayX = addNextValueToArray(arrayX, input[currentIndex]);

                currentIndex++;
            }
//            System.out.println("inputTest = " + Arrays.toString(input));
//            System.out.println("forecastingArray = " + Arrays.toString(forecastingArray));
            System.out.println(t + " MSE = " + outNeurons.get(0).calcMSE(input.length - 1));
        }

    }

    public void test() {

        int currentIndex = 0;
        double[] arrayX = getLastElementsInArray(input, windowSize);
        double[] forecastingArray = new double[inputTest.length];
        outNeurons.get(0).d = inputTest[0];
        outNeurons.get(0).mse = 0;

        while (currentIndex != inputTest.length) {

            //цикл по нейронам
            for (int n = 0; n < neurons.size(); n++) {
                neurons.get(n).arrayX = arrayX;
            }

            outNeurons.get(0).d = inputTest[currentIndex];
            outNeurons.get(0).calcGaussianRadFunctionsForHideNeurons();
            forecastingArray[currentIndex] = outNeurons.get(0).calcOutValue();

            System.out.println(currentIndex + " error = " + outNeurons.get(0).calcLocalMSE());

            arrayX = addNextValueToArray(arrayX, inputTest[currentIndex]);

            currentIndex++;
        }
        System.out.println("################################################################################################################################################");
        System.out.println("MSE = " + outNeurons.get(0).calcMSE(inputTest.length - 1));

        System.out.println("inputTest = " + Arrays.toString(inputTest));
        System.out.println("forecastingArray = " + Arrays.toString(forecastingArray));
        String s = Arrays.toString(forecastingArray);
        s = s.replace(",", "\n");
        s = s.replace(".", ",");
        s = s.replace("[", "");
        s = s.replace("]", "");
        System.out.println(s);

//        System.out.println("###DENORM###");
//        double invLength = Tools.getInvLength(ConfigData.inTest);
//        inputTest = Tools.getDeNormArray(inputTest,invLength);
//        forecastingArray = Tools.getDeNormArray(forecastingArray,invLength);
//
//        System.out.println("inputTest = "+ Arrays.toString(inputTest));
//        System.out.println("forecastingArray = "+Arrays.toString(forecastingArray));

    }

    private double[] getLastElementsInArray(double[] input, int windowSize) {
        double[] tempArray = new double[windowSize];
        int startIndex = input.length - windowSize;
        int currentIndex = 0;
        for (int i = startIndex; i < input.length; i++) {
            tempArray[currentIndex] = input[i];
            currentIndex++;
        }
        return tempArray;
    }

    public int findWinningCenterId(double[] weight) {
        double minWeight = weight[0];
        int id = 0;
        for (int i = 0; i < weight.length; i++) {
            if (weight[i] < minWeight) {
                id = i;
            }
        }
        return id;
    }

    public void calcSigma() {
        List<Neuron> neuronsNeighbors = new ArrayList<>();
        for (Neuron n : neurons) {
            neuronsNeighbors.addAll(neurons);
            neuronsNeighbors.remove(n);
            n.calcSigma(neuronsNeighbors);
            neuronsNeighbors.clear();
        }
    }

    public void initNeurons(int countHiddenNeurons, int countOutputNeurons) {

        neurons = new ArrayList<>();
        outNeurons = new ArrayList<>();

        for (int i = 0; i < countHiddenNeurons; i++) {
            neurons.add(new Neuron());
        }

        for (int i = 0; i < countOutputNeurons; i++) {
            outNeurons.add(new OutNeuron(neurons.size()));
        }

    }

    private void printNeurons() {
        for (int i = 0; i < neurons.size(); i++) {
            System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
            System.out.println("Neuron N" + i);
            System.out.println(neurons.get(i).toString());
        }
        System.out.println();
    }

    private double[] getArrayX(int startIndex, int endIndex) {
        double[] arrayX = new double[windowSize];
        int i = 0;
        for (int j = startIndex; j < endIndex; j++) {
            arrayX[i] = input[j];
            i++;
        }
        return arrayX;
    }

    double[] addNextValueToArray(double[] currentArray, double nextValue) {
        double[] tempArray = new double[currentArray.length];
        for (int i = 0; i < currentArray.length - 1; i++) {
            tempArray[i] = currentArray[i + 1];
        }
        tempArray[tempArray.length - 1] = nextValue;
        return tempArray;
    }
}
