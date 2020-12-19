package com.gureev.forecasting;

import java.util.List;
import java.util.Random;

public class OutNeuron {

    double w0 = 1;
    double y = 0;
    double[] w;//веса
    double d; //ожидаймое зачение
    double n = 0.015;//коэффицент

    List<Neuron> neurons;
    double mse = 0;

    public OutNeuron(int countHiddenNeurons) {
        w = new double[countHiddenNeurons];
        Random random = new Random();
        //инициализация весов
        for (int i = 0; i < countHiddenNeurons; i++) {
            w[i] = random.nextDouble();
            //w[i] = 0.5;
        }
    }

    double calcOutValue() {
        y = 0;
        y += w0;
        int i = 0;
        for (Neuron neuron : neurons) {
            y += w[i] * neuron.f;
            i++;
        }
        y = Math.ceil(y * 100) / 100;
        return y;
    }

    //расчёт значений функций для нейронов
    void calcGaussianRadFunctionsForHideNeurons() {
        for (Neuron neuron : neurons) {
            neuron.calcGaussianRadFun();
        }
    }

    //пересчёт весов у нейронов (Wis(t+1)) 4.17а
    public void recalculateWeightsForNeurons() {
        calcOutValue();
        int i = 0;
        for (Neuron neuron : neurons) {
            double p = neuron.f * (y - d);
            w[i] = w[i] - n * p;
            i++;
        }
    }


    //функция MSE
    double calcMSE(double m) {
        mse = Math.sqrt(mse * (1.0 / (m - 1.0)));
        return mse;
    }

    double calcLocalMSE() {
        double localMSE = Math.pow((y - d), 2);
        mse += localMSE;
        return Math.sqrt(localMSE);
    }


}
