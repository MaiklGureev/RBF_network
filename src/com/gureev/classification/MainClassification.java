package com.gureev.classification;

import com.gureev.Tools;

public class MainClassification {


    public static void main(String[] args) {
        //wine
        //0-red 1-white
        double[] c1 = new double[]{7.8, 0.41, 0.68, 1.7, 0.467, 18, 69, 0.9973, 3.08, 1.31, 9.3, 5}; //0
        double[] c2 = new double[]{7.4, 0.7, 0, 1.9, 0.076, 11, 34, 0.9978, 3.51, 0.56, 9.4, 5}; //0
        double[] c3 = new double[]{7.3, 0.65, 0, 1.2, 0.065, 15, 21, 0.9946, 3.39, 0.47, 10, 7}; //0
        double[] c4 = new double[]{7, 0.27, 0.36, 20.7, 0.045, 45, 170, 1.001, 3, 0.45, 8.8, 6}; //1
        double[] c5 = new double[]{8.3, 0.42, 0.62, 19.25, 0.04, 41, 172, 1.0002, 2.98, 0.67, 9.7, 5}; //1
        double[] c6 = new double[]{6.8, 0.28, 0.36, 8, 0.045, 28, 123, 0.9928, 3.02, 0.37, 11.4, 6}; //1

        c1 = Tools.getNormArray(c1);
        c2 = Tools.getNormArray(c2);
        c3 = Tools.getNormArray(c3);
        c4 = Tools.getNormArray(c4);
        c5 = Tools.getNormArray(c5);
        c6 = Tools.getNormArray(c6);

        double[][] in = Tools.getNormMatrix(Config.in);
        double[][] inTest = Tools.getNormMatrix(Config.inTest);

        NeuralNetwork neuralNetwork = new NeuralNetwork(in, Config.out, inTest, Config.outTest);
        neuralNetwork.initNeurons(6, 2);

        neuralNetwork.neurons.get(0).arrayC = c1;
        neuralNetwork.neurons.get(1).arrayC = c2;
        neuralNetwork.neurons.get(2).arrayC = c3;
        neuralNetwork.neurons.get(3).arrayC = c4;
        neuralNetwork.neurons.get(4).arrayC = c5;
        neuralNetwork.neurons.get(5).arrayC = c6;

        neuralNetwork.outNeurons.get(0).standardOutValue = 0;
        neuralNetwork.outNeurons.get(1).standardOutValue = 1;

        for (Neuron n : neuralNetwork.neurons) {
            n.outNeurons = neuralNetwork.outNeurons;
        }

        for (OutNeuron outNeuron : neuralNetwork.outNeurons) {
            outNeuron.neurons = neuralNetwork.neurons;
        }

        neuralNetwork.train();
        //neuralNetwork.test();

    }


}
