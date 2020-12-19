package com.gureev.classification;

import java.util.List;
import java.util.Random;

public class OutNeuron {

    double w0 = 1;
    double y = 0;
    double e = 0;
    double standardOutValue;
    double[] w;//веса
    double d; //ожидаймое зачение
    double n = 0.001;//коэффицент

    List<Neuron> neurons;
    public double mse = 0;

    public OutNeuron(int countHiddenNeurons) {
        w = new double[countHiddenNeurons];
        Random random = new Random();
        //инициализация весов
        for (int i = 0; i < countHiddenNeurons; i++) {
            //w[i] = random.nextDouble();
            w[i] = 0.5;
        }
    }

    //расчёт значений функций для нейронов
    void calcGaussianRadFunctionsForHideNeurons() {
        for (Neuron neuron : neurons) {
            neuron.calcGaussianRadFun();
        }
    }

    //функция ошибки (Е) 4.10
    double calcCellFun() {
        e = 0;
        for (Neuron neuron : neurons) {
            for (int i = 0; i < w.length; i++) {
                e += w[i] * neuron.f;
            }
        }
        e = Math.pow(e - d, 2) / 2;
        return e;
    }


    double calcMainFun() {
        y = 0;
        y += w0;
        int i = 0;
        for (Neuron neuron : neurons) {
            y += w[i] * neuron.f;
            i++;
        }
        return y;
    }

    //пересчёт весов у нейронов (Wis(t+1)) 4.17а
    public void recalculateWeightsForNeurons() {
        calcCellFun();
        calcMainFun();
        int i = 0;
        for (Neuron neuron : neurons) {
            double p = neuron.f * (e - d);
            w[i] = w[i] - n * p;
            i++;
        }
    }


    public void setD(double d) {
        if (d == 0 && standardOutValue == 1) {
            this.d = 0;
        } else if (d == 1 && standardOutValue == 0) {
            this.d = 0;
        } else if (d == 1 && standardOutValue == 1) {
            this.d = 1;
        } else if (d == 0 && standardOutValue == 0) {
            this.d = 1;
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
