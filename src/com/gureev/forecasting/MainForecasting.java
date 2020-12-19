package com.gureev.forecasting;

public class MainForecasting {

    public static void main(String[] args) {

        NeuralNetwork neuralNetwork = new NeuralNetwork(ConfigData.in, ConfigData.inTest);
        neuralNetwork.initNeurons(60, 1);

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
        neuralNetwork.neurons.get(20).arrayC = ConfigCenter.c1;
        neuralNetwork.neurons.get(21).arrayC = ConfigCenter.c2;
        neuralNetwork.neurons.get(22).arrayC = ConfigCenter.c3;
        neuralNetwork.neurons.get(23).arrayC = ConfigCenter.c4;
        neuralNetwork.neurons.get(24).arrayC = ConfigCenter.c5;
        neuralNetwork.neurons.get(25).arrayC = ConfigCenter.c6;
        neuralNetwork.neurons.get(26).arrayC = ConfigCenter.c7;
        neuralNetwork.neurons.get(27).arrayC = ConfigCenter.c8;
        neuralNetwork.neurons.get(28).arrayC = ConfigCenter.c9;
        neuralNetwork.neurons.get(29).arrayC = ConfigCenter.c10;
        neuralNetwork.neurons.get(30).arrayC = ConfigCenter.c11;
        neuralNetwork.neurons.get(31).arrayC = ConfigCenter.c12;
        neuralNetwork.neurons.get(32).arrayC = ConfigCenter.c13;
        neuralNetwork.neurons.get(33).arrayC = ConfigCenter.c14;
        neuralNetwork.neurons.get(34).arrayC = ConfigCenter.c15;
        neuralNetwork.neurons.get(35).arrayC = ConfigCenter.c16;
        neuralNetwork.neurons.get(36).arrayC = ConfigCenter.c17;
        neuralNetwork.neurons.get(37).arrayC = ConfigCenter.c18;
        neuralNetwork.neurons.get(38).arrayC = ConfigCenter.c19;
        neuralNetwork.neurons.get(39).arrayC = ConfigCenter.c20;
        neuralNetwork.neurons.get(40).arrayC = ConfigCenter.c1;
        neuralNetwork.neurons.get(41).arrayC = ConfigCenter.c2;
        neuralNetwork.neurons.get(42).arrayC = ConfigCenter.c3;
        neuralNetwork.neurons.get(43).arrayC = ConfigCenter.c4;
        neuralNetwork.neurons.get(44).arrayC = ConfigCenter.c5;
        neuralNetwork.neurons.get(45).arrayC = ConfigCenter.c6;
        neuralNetwork.neurons.get(46).arrayC = ConfigCenter.c7;
        neuralNetwork.neurons.get(47).arrayC = ConfigCenter.c8;
        neuralNetwork.neurons.get(48).arrayC = ConfigCenter.c9;
        neuralNetwork.neurons.get(49).arrayC = ConfigCenter.c10;
        neuralNetwork.neurons.get(50).arrayC = ConfigCenter.c11;
        neuralNetwork.neurons.get(51).arrayC = ConfigCenter.c12;
        neuralNetwork.neurons.get(52).arrayC = ConfigCenter.c13;
        neuralNetwork.neurons.get(53).arrayC = ConfigCenter.c14;
        neuralNetwork.neurons.get(54).arrayC = ConfigCenter.c15;
        neuralNetwork.neurons.get(55).arrayC = ConfigCenter.c16;
        neuralNetwork.neurons.get(56).arrayC = ConfigCenter.c17;
        neuralNetwork.neurons.get(57).arrayC = ConfigCenter.c18;
        neuralNetwork.neurons.get(58).arrayC = ConfigCenter.c19;
        neuralNetwork.neurons.get(59).arrayC = ConfigCenter.c20;


        for (Neuron n : neuralNetwork.neurons) {
            n.outNeurons = neuralNetwork.outNeurons;
        }

        for (OutNeuron outNeuron : neuralNetwork.outNeurons) {
            outNeuron.neurons = neuralNetwork.neurons;
        }

        neuralNetwork.trainingWeightsCount = 5105;
        //neuralNetwork.trainingWeightsCount = 20000;

        neuralNetwork.trainingCentersCount = 1;
        neuralNetwork.windowSize = 8;

        neuralNetwork.train();
        neuralNetwork.test();

    }

}
