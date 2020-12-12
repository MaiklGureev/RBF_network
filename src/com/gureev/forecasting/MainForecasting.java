package com.gureev.forecasting;

public class MainForecasting {

    public static void main(String[] args) {

        NeuralNetwork neuralNetwork = new NeuralNetwork(ConfigData.in, ConfigData.inTest);
        //NeuralNetwork neuralNetwork = new NeuralNetwork(Tools.getNormArray(ConfigData.in), Tools.getNormArray(ConfigData.inTest));
        neuralNetwork.initNeurons(20, 1);

        neuralNetwork.neurons.get(0).arrayC = ConfigCenter.c1;
        neuralNetwork.neurons.get(1).arrayC = ConfigCenter.c2;
        neuralNetwork.neurons.get(2).arrayC = ConfigCenter.c3;
        neuralNetwork.neurons.get(3).arrayC = ConfigCenter.c4;
        neuralNetwork.neurons.get(4).arrayC = ConfigCenter.c5;
        neuralNetwork.neurons.get(5).arrayC = ConfigCenter.c6;
        neuralNetwork.neurons.get(6).arrayC = ConfigCenter.c7;
        neuralNetwork.neurons.get(7).arrayC = ConfigCenter.c8;
        neuralNetwork.neurons.get(8).arrayC = ConfigCenter.c9;
        neuralNetwork.neurons.get(9).arrayC = ConfigCenter.c10;
        neuralNetwork.neurons.get(10).arrayC = ConfigCenter.c11;
        neuralNetwork.neurons.get(11).arrayC = ConfigCenter.c12;
        neuralNetwork.neurons.get(12).arrayC = ConfigCenter.c13;
        neuralNetwork.neurons.get(13).arrayC = ConfigCenter.c14;
        neuralNetwork.neurons.get(14).arrayC = ConfigCenter.c15;
        neuralNetwork.neurons.get(15).arrayC = ConfigCenter.c16;
        neuralNetwork.neurons.get(16).arrayC = ConfigCenter.c17;
        neuralNetwork.neurons.get(17).arrayC = ConfigCenter.c18;
        neuralNetwork.neurons.get(18).arrayC = ConfigCenter.c19;
        neuralNetwork.neurons.get(19).arrayC = ConfigCenter.c20;


//        neuralNetwork.neurons.get(0).arrayC = Tools.getNormArray(ConfigCenter.c1);
//        neuralNetwork.neurons.get(1).arrayC = Tools.getNormArray(ConfigCenter.c2);
//        neuralNetwork.neurons.get(2).arrayC = Tools.getNormArray(ConfigCenter.c3);
//        neuralNetwork.neurons.get(3).arrayC = Tools.getNormArray(ConfigCenter.c4);
//        neuralNetwork.neurons.get(4).arrayC = Tools.getNormArray(ConfigCenter.c5);
//        neuralNetwork.neurons.get(5).arrayC = Tools.getNormArray(ConfigCenter.c6);
//        neuralNetwork.neurons.get(6).arrayC = Tools.getNormArray(ConfigCenter.c7);
//        neuralNetwork.neurons.get(7).arrayC = Tools.getNormArray(ConfigCenter.c8);
//        neuralNetwork.neurons.get(8).arrayC = Tools.getNormArray(ConfigCenter.c9);
//        neuralNetwork.neurons.get(9).arrayC = Tools.getNormArray(ConfigCenter.c10);
//        neuralNetwork.neurons.get(10).arrayC = Tools.getNormArray(ConfigCenter.c11);
//        neuralNetwork.neurons.get(11).arrayC = Tools.getNormArray(ConfigCenter.c12);
//        neuralNetwork.neurons.get(12).arrayC = Tools.getNormArray(ConfigCenter.c13);
//        neuralNetwork.neurons.get(13).arrayC = Tools.getNormArray(ConfigCenter.c14);
//        neuralNetwork.neurons.get(14).arrayC = Tools.getNormArray(ConfigCenter.c15);
//        neuralNetwork.neurons.get(15).arrayC = Tools.getNormArray(ConfigCenter.c16);
//        neuralNetwork.neurons.get(16).arrayC = Tools.getNormArray(ConfigCenter.c17);
//        neuralNetwork.neurons.get(17).arrayC = Tools.getNormArray(ConfigCenter.c18);
//        neuralNetwork.neurons.get(18).arrayC = Tools.getNormArray(ConfigCenter.c19);
//        neuralNetwork.neurons.get(19).arrayC = Tools.getNormArray(ConfigCenter.c20);

        for (Neuron n : neuralNetwork.neurons) {
            n.outNeurons = neuralNetwork.outNeurons;
        }

        for (OutNeuron outNeuron : neuralNetwork.outNeurons) {
            outNeuron.neurons = neuralNetwork.neurons;
        }

        neuralNetwork.trainingWeightsCount = 120000;
        neuralNetwork.trainingCentersCount = 1;
        neuralNetwork.windowSize = 8;

        neuralNetwork.train();
        neuralNetwork.test();

    }
//        neuralNetwork.neurons.get(0).arrayC = ConfigCenter.c1;
//        neuralNetwork.neurons.get(1).arrayC = ConfigCenter.c2;
//        neuralNetwork.neurons.get(2).arrayC = ConfigCenter.c3;
//        neuralNetwork.neurons.get(3).arrayC = ConfigCenter.c4;
//        neuralNetwork.neurons.get(4).arrayC = ConfigCenter.c5;
//        neuralNetwork.neurons.get(5).arrayC = ConfigCenter.c6;
//        neuralNetwork.neurons.get(6).arrayC = ConfigCenter.c7;
//        neuralNetwork.neurons.get(7).arrayC = ConfigCenter.c8;
//        neuralNetwork.neurons.get(8).arrayC = ConfigCenter.c9;
//        neuralNetwork.neurons.get(9).arrayC = ConfigCenter.c10;
//        neuralNetwork.neurons.get(10).arrayC = ConfigCenter.c11;
//        neuralNetwork.neurons.get(11).arrayC = ConfigCenter.c12;
//        neuralNetwork.neurons.get(12).arrayC = ConfigCenter.c13;
//        neuralNetwork.neurons.get(13).arrayC = ConfigCenter.c14;
//        neuralNetwork.neurons.get(14).arrayC = ConfigCenter.c15;
//        neuralNetwork.neurons.get(15).arrayC = ConfigCenter.c16;
//        neuralNetwork.neurons.get(16).arrayC = ConfigCenter.c17;
//        neuralNetwork.neurons.get(17).arrayC = ConfigCenter.c18;
//        neuralNetwork.neurons.get(18).arrayC = ConfigCenter.c19;
//        neuralNetwork.neurons.get(19).arrayC = ConfigCenter.c20;
}
