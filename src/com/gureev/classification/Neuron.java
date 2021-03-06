package com.gureev.classification;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Neuron {

    double[] arrayX;//входные данные
    double[] arrayC; //центры нейрона
    double sigma;
    double f;//значение функции

    double n = 0.5;//коэффицент
    double r = 5; //радиус

    List<OutNeuron> outNeurons;

    public Neuron() {
        Random random = new Random();
        sigma = random.nextDouble();
    }

    public void kMeansCorrection() {
        for (int i = 0; i < arrayX.length; i++) {
            arrayC[i] = arrayC[i] + n * (arrayX[i] - arrayC[i]);
        }
    }

    public void calcSigma(List<Neuron> neuronsNeighbors) {
        sigma = 0;
        for (Neuron neuron : neuronsNeighbors) {
            for (int n = 0; n < arrayC.length; n++) {
                sigma += Math.pow(arrayC[n] - neuron.arrayC[n], 2);
            }
        }
//        for (int n = 0; n < neuronsNeighbors.size(); n++) {
//            sigma += Math.pow(arrayC[n] - neuronsNeighbors.get(n).arrayC[n], 2);
//        }
        sigma = Math.sqrt(sigma / r);
    }

    public double calcWeightForFindCenter() {
        double weight = 0;
        for (int i = 0; i < arrayX.length; i++) {
            weight += Math.pow(arrayX[i] - arrayC[i], 2);
        }
        weight = Math.sqrt(weight);
        return weight;
    }

    //гауссовская радиальная функция активации 4.11 и 4.12
    void calcGaussianRadFun() {
        double u = 0;
        //4.12
        for (int k = 0; k < arrayX.length; k++) {
            u += Math.pow(arrayX[k] - arrayC[k], 2) / Math.pow(sigma, 2);
        }
        //4.11
        f = Math.exp(-u / 2);
    }


    @Override
    public String toString() {
//        String weightsString = "";
//        for (int i = 0; i < w.length; i++) {
//            weightsString += Arrays.toString(w[i]);
//        }
        return "{arrayC=" + Arrays.toString(arrayC) +
                ", sigma=" + sigma +
//                ", w=" + weightsString +
                '}';
    }
}
