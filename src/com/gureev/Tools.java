package com.gureev;

public class Tools {

    public static double[][] getNormMatrix(double[][] in) {
        double[][] tempMatrix = in.clone();
        for (int i = 0; i < in.length; i++) {
            for (int j = 1; j < in[0].length; j++) {
                double invLength = getInvLength(in[i]);
                if (in[i][j] != 0 && invLength != 0) {
                    tempMatrix[i][j] = in[i][j] * invLength;
                }
            }
        }
        return tempMatrix;
    }

    public static double[] getNormArray(double[] in) {
        double[] tempArray = new double[in.length];
        for (int i = 0; i < in.length; i++) {
            double invLength = getInvLength(in);
            if (in[i] != 0 && invLength != 0) {
                tempArray[i] = in[i] * invLength;
            }
        }
        return tempArray;

    }

    public static double[] getDeNormArray(double[] in, double invLength) {
        double[] tempArray = new double[in.length];
        for (int i = 0; i < in.length; i++) {
            tempArray[i] = in[i] / invLength;
        }
        return tempArray;
    }

    public static double getInvLength(double[] in) {
        double invLength = 0;
        for (int i = 0; i < in.length; i++) {
            invLength += Math.pow(in[i], 2);
        }
        invLength = 1 / Math.sqrt(invLength);
        return invLength;
    }


}
