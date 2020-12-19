package com.gureev.classification;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {

    double[][] input;
    double[] output;
    double[][] inputTest;
    double[] outputTest;

    List<Neuron> neurons;
    List<OutNeuron> outNeurons;

    public NeuralNetwork(double[][] input, double[] output, double[][] inputTest, double[] outputTest) {
        this.input = input;
        this.output = output;
        this.inputTest = inputTest;
        this.outputTest = outputTest;
    }

    public void kMeans() {

        //цикл по входному списку
        for (int i = 0; i < input.length; i++) {

            int winningCenterId = 0;
            double weight = 0;
            double[] weightsArray = new double[neurons.size()];

            //цикл по нейронам
            for (int n = 0; n < neurons.size(); n++) {
                //подсчёт весов в нейроне
                neurons.get(n).arrayX = input[i];
                weight = neurons.get(n).calcWeightForFindCenter();
                weightsArray[n] = weight;
            }

            //поиск id нейрона с минимальным весом
            winningCenterId = findWinningCenterId(weightsArray);

            //коррекция центров у нерона
            neurons.get(winningCenterId).kMeansCorrection();
        }

    }

    public void train() {

        printNeurons();
        for (int t = 0; t < 1; t++) {
            for (int n = 0; n < input.length; n++) {
                kMeans();
            }
        }
        System.out.println("########################################################################");
        //printNeurons();

        calcSigma();

        for (int t = 0; t < 1; t++) {

            double counter = 0;
            for (int i = 0; i < 10; i++) {

                for (int n = 0; n < neurons.size(); n++) {
                    neurons.get(n).arrayX = input[i];
                }

                for (int j = 0; j < outNeurons.size(); j++) {
                    outNeurons.get(j).setD(output[i]);
                    outNeurons.get(j).calcGaussianRadFunctionsForHideNeurons();
                    outNeurons.get(j).recalculateWeightsForNeurons();
                    outNeurons.get(j).calcLocalMSE();
                }
                double value0 = outNeurons.get(0).y;
                double value1 = outNeurons.get(1).y;
                double foundClass;
                if (value0 > value1) {
                    foundClass = 0;
                } else {
                    foundClass = 1;
                }
                if (foundClass == output[i]) {
                    counter++;
                }
            }
            System.out.println(t + " Total right result: " + (int) counter + "/" + output.length + " error: " + Math.ceil(counter / output.length * 100) / 100);
        }

    }

    private void printNeurons() {
        for (int i = 0; i < neurons.size(); i++) {
            System.out.println("//////////////////////////////////////////////////////////////////////////////////");
            System.out.println("Neuron N" + i);
            System.out.println(neurons.get(i).toString());
        }
        System.out.println();
    }

    public void test() {
        double counter = 0;
        for (int i = 0; i < inputTest.length; i++) {
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            for (int n = 0; n < neurons.size(); n++) {
                neurons.get(n).arrayX = inputTest[i];
            }
            for (int j = 0; j < outNeurons.size(); j++) {
                outNeurons.get(j).setD(outputTest[i]);
                outNeurons.get(j).calcGaussianRadFunctionsForHideNeurons();
                outNeurons.get(j).calcMainFun();
                outNeurons.get(j).calcCellFun();
//                System.out.println("OutNeuron N" + j);
//                System.out.println("    y=" + outNeurons.get(j).y);
//                System.out.println("    E=" + outNeurons.get(j).e);
            }
            double value0 = outNeurons.get(0).y;
            double value1 = outNeurons.get(1).y;
            double foundClass;
            if (value0 > value1) {
                foundClass = 0;
            } else {
                foundClass = 1;
            }
            System.out.println("Detected class " + foundClass + ".Right class: " + outputTest[i]);
            if (foundClass == outputTest[i]) {
                counter++;
            }
        }
        System.out.println("Total right result: " + (int) counter + "/" + outputTest.length + " " + (1 - Math.ceil(counter / outputTest.length * 100) / 100));
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


}
