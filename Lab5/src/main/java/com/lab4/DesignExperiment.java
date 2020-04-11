package main.java.com.lab4;

import article.CorrectionPrint;
import article.MathPackage.Matrix;
import main.java.com.lab3.Experiment;

public class DesignExperiment {
    private Matrix matrix = new Matrix();
    private Math math = new Math();

    private Experiment lab3 = new Experiment();

    private int m = 3;
    private int N = 8;

    private int[][] X = new int[8][8];
    private int[][] XPlan = new int[8][3];

    //                            min     max
    private int[] X1 = new int[]{ -25,    -5};
    private int[] X2 = new int[]{  15,    50};
    private int[] X3 = new int[]{ -25,   -15};

    //Shortcut for equation
    private double[][] M;
    private double[][] K;
    private double[][] B;

    private double[][] Y;
    private double YMax = 210;
    private double YMin = 188.4;

    private double totalDispersion;
    private double[] yPractical;

    private void fillMatrix_X() {
        for (int i = 0; i < X.length; i++) {
            int powerTwo = 1;
            for (int j = 3; j >= 1; j--) {
                X[i][j] = (i & powerTwo) != 0 ? 1 : -1;
                powerTwo *= 2;
            }
            X[i][0] = 1;
            X[i][4] = X[i][1] * X[i][2];
            X[i][5] = X[i][1] * X[i][3];
            X[i][6] = X[i][2] * X[i][3];
            X[i][7] = X[i][1] * X[i][2] * X[i][3];

            XPlan[i][0] = X[i][1] == 1 ? X1[1] : X1[0];
            XPlan[i][1] = X[i][2] == 1 ? X2[1] : X2[0];
            XPlan[i][2] = X[i][3] == 1 ? X3[1] : X3[0];
        }
    }

    public boolean start() {
        if (lab3.startExperiment())
            return true;             // Finish!!
        // else Do algorithm below

        fillMatrix_X();
        System.out.println("X:");
        CorrectionPrint.printArray(XPlan);

        createPlanMatrix(m);
        System.out.println("Y:");
        CorrectionPrint.printArray(Y);

        solveEquations();
        System.out.println("Found B arguments:\n");
        CorrectionPrint.printArray(B);

        checkSmoothDispersion();
        checkZeroHypothesis();
        return checkAdequacy();
    }

    private void createPlanMatrix(int m) {
        Y = new double[8][m + 1];

        for (int i = 0; i < 8; i++) {
            double average = 0;
            for (int  j = 0; j < m; j++) {
                Y[i][j] = java.lang.Math.random() * (YMax - YMin + 1) + YMin;
                average += Y[i][j];
            }
            //  Save average value
            Y[i][m] = average / m;
        }
    }

    private double getSumX(int[] col) {
        double sum = 0;
        for (int i = 0; i < N; i++) {
            double temp = XPlan[i][col[0] - 1];
            for (int j = 1; j < col.length; j++)
                temp *= XPlan[i][col[j] - 1];
            sum += temp;
        }
        return sum;
    }

    private double getSumY(int[] col) {
        double sum = 0;
        for (int i = 0; i < N; i++) {
            double temp = Y[i][m];
            for (int j = 0; j < col.length; j++)
                temp *= XPlan[i][col[j] - 1];
            sum += temp;
        }
        return sum;
    }

    private void solveEquations() {
        M = new double[8][8];
        M[0] = new double[]{1,                          getSumX(new int[]{1}), getSumX(new int[]{2}), getSumX(new int[]{3}), getSumX(new int[]{1, 2}), getSumX(new int[]{1, 3}), getSumX(new int[]{2, 3}), getSumX(new int[]{1, 2, 3})};
        M[1] = new double[]{getSumX(new int[]{1}),      getSumX(new int[]{1, 1}), getSumX(new int[]{1, 2}), getSumX(new int[]{1, 3}), getSumX(new int[]{1, 1, 2}), getSumX(new int[]{1, 1, 3}), getSumX(new int[]{2, 1, 3}), getSumX(new int[]{1, 1, 2, 3})};
        M[2] = new double[]{getSumX(new int[]{2}),      getSumX(new int[]{1, 2}), getSumX(new int[]{2, 2}), getSumX(new int[]{2, 3}), getSumX(new int[]{2, 1, 2}), getSumX(new int[]{2, 1, 3}), getSumX(new int[]{2, 2, 3}), getSumX(new int[]{2, 1, 2, 3})};
        M[3] = new double[]{getSumX(new int[]{3}),      getSumX(new int[]{1, 3}), getSumX(new int[]{3, 2}), getSumX(new int[]{3, 3}), getSumX(new int[]{3, 1, 2}), getSumX(new int[]{3, 1, 3}), getSumX(new int[]{3, 2, 3}), getSumX(new int[]{3, 1, 2, 3})};

        M[4] = new double[]{getSumX(new int[]{1, 2}),   getSumX(new int[]{1, 2, 1}), getSumX(new int[]{1, 2, 2}), getSumX(new int[]{1, 2, 3}), getSumX(new int[]{1, 2, 1, 2}), getSumX(new int[]{1, 2, 1, 3}), getSumX(new int[]{1, 2, 2, 3}), getSumX(new int[]{1, 2, 1, 2, 3})};
        M[5] = new double[]{getSumX(new int[]{1, 3}),   getSumX(new int[]{1, 3, 1}), getSumX(new int[]{1, 3, 2}), getSumX(new int[]{1, 3, 3}), getSumX(new int[]{1, 3, 1, 2}), getSumX(new int[]{1, 3, 1, 3}), getSumX(new int[]{1, 3, 2, 3}), getSumX(new int[]{1, 3, 1, 2, 3})};
        M[6] = new double[]{getSumX(new int[]{2, 3}),   getSumX(new int[]{2, 3, 1}), getSumX(new int[]{2, 3, 2}), getSumX(new int[]{2, 3, 3}), getSumX(new int[]{2, 3, 1, 2}), getSumX(new int[]{2, 3, 1, 3}), getSumX(new int[]{2, 3, 2, 3}), getSumX(new int[]{2, 3, 1, 2, 3})};

        M[7] = new double[]{getSumX(new int[]{1, 2, 3}),getSumX(new int[]{1, 2, 3, 1}), getSumX(new int[]{1, 2, 3, 2}), getSumX(new int[]{1, 2, 3, 3}), getSumX(new int[]{1, 2, 3, 1, 2}), getSumX(new int[]{1, 2, 3, 1, 3}), getSumX(new int[]{1, 2, 3, 2, 3}), getSumX(new int[]{1, 2, 3, 1, 2, 3})};

        M[0][0] = N;

        K = new double[8][1];
        K[0][0] = getSumY(new int[0]);
        K[1][0] = getSumY(new int[]{1});
        K[2][0] = getSumY(new int[]{2});
        K[3][0] = getSumY(new int[]{3});
        K[4][0] = getSumY(new int[]{1, 2});
        K[5][0] = getSumY(new int[]{1, 3});
        K[6][0] = getSumY(new int[]{2, 3});
        K[7][0] = getSumY(new int[]{1, 2, 3});


        // Use my own Matrix library for find result of system (library very useful)
        B = matrix.getArguments(M, K);
    }

    private void checkSmoothDispersion() {
        // I use temporally G how finding max Dispersion Value
        double Gp = 0;
        for (double dispersion : math.findDispersion(Y, m)) {
            Gp = java.lang.Math.max(Gp, dispersion);
            totalDispersion += dispersion;
        }
        Gp /= totalDispersion;

        System.out.println("\nKohrena Measure:");

        double Gt = math.koharenaMeasure(m - 1, N);
        if (Gp < Gt)
            System.out.println("Dispersion is Smooth!!\nGp = " + Gp + "\tGt = " + Gt);
        else {
            System.out.println("Dispersion is not Smooth\nGp = " + Gp + "\tGt = " + Gt);
            m++;
            start();
        }

    }

    private void checkZeroHypothesis() {

        double[] beta = new double[8];

        for (int i = 0; i < 4; i++) {
            beta[i] = 0;
            for (int j = 0; j < N; j++)
                // In Y[][m] locate average Value Y
                beta[i] += Y[j][m] * X[j][i] / N;
        }

        double[] t = new double[8];
        yPractical = new double[8];

        System.out.println("\n\nStudentsa Measure:");
        for (int i = 0; i < 4; i++) {
            t[i] = beta[i] / java.lang.Math.sqrt(totalDispersion / (N * N * m)) * (beta[i] < 0 ? (-1) : 1);

            System.out.printf("t%d = %8.3f\n", i, t[i]);

            B[i][0]  = (t[i] < math.studentaMeasure((m - 1) * N) ? 0 : 1);
        }

        for (int i = 0; i < N; i++)
            yPractical[i] = B[0][0] + B[1][0] * XPlan[i][0] + B[2][0] * XPlan[i][1] + B[3][0] * XPlan[i][2] + B[4][0] * XPlan[i][0] * XPlan[i][1]
                    + B[5][0] * XPlan[i][0] * XPlan[i][2] + B[6][0] * XPlan[i][1] * XPlan[i][2] + B[7][0] * XPlan[i][0] * XPlan[i][1] * XPlan[i][2];

        System.out.printf("t = %8.3f\n\n", math.studentaMeasure((m - 1) * N));
    }

    private boolean checkAdequacy() {

        System.out.println("Fishera Measure:\n");

        double[] temp = new double[N];
        for (int i = 0; i < N; i++)
            temp[i] = Y[i][m];

        System.out.printf("Sad = %8.3f\n", math.findCoefficientFishera(yPractical, temp, N) * m / 2);

        System.out.printf("Fp = %8.3f\n", (math.findCoefficientFishera(yPractical, temp, N) * m / 2) / ( totalDispersion / (N * N * m)));
        System.out.printf("Ft = %8.3f\n", math.fisheraMeasure((m - 1) * N, N - 2));
        if (math.fisheraMeasure((m - 1) * N, N - 2) < (math.findCoefficientFishera(yPractical, temp, N) * m / 2) / (totalDispersion / (N * N * m))) {
            System.out.println("Equation is not Adequacy");
            // Start again
            return false;
        }
        else
            System.out.println("Equation is Adequacy");
        return true;
    }
}
