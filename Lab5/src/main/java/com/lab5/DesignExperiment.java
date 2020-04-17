package main.java.com.lab5;

import article.CorrectionPrint;
import article.MathPackage.Matrix;
import main.java.com.lab4.Math;

public class DesignExperiment {
    private Matrix matrix = new Matrix();
    private Math math = new Math();
    private main.java.com.lab4.DesignExperiment lab4 = new main.java.com.lab4.DesignExperiment();

    private int m = 3;
    private int N = 15;

    private double[][] X = new double[15][10];
    private double[][] XPlan = new double[15][10];

    //                            min     max
    private int[] X1 = new int[]{  -5,     4};
    private int[] X2 = new int[]{  -2,     7};
    private int[] X3 = new int[]{  -1,     2};

    //Shortcut for equation
    private double[][] M;
    private double[][] K;
    private double[][] B;

    private double[][] Y;
    private double YMax = 204.3;
    private double YMin = 197.4;

    private double totalDispersion;
    private double[] yPractical;

    private void fillMatrix_X() {
        for (int i = 0; i < 8; i++) {
            int powerTwo = 1;
            for (int j = 2; j >= 0; j--) {
                X[i][j] = (i & powerTwo) != 0 ? 1 : -1;
                powerTwo *= 2;
            }
            X[i][3] = X[i][0] * X[i][1];
            X[i][4] = X[i][0] * X[i][2];
            X[i][5] = X[i][1] * X[i][2];
            X[i][6] = X[i][0] * X[i][1] * X[i][2];
            X[i][7] = X[i][0] * X[i][0];
            X[i][8] = X[i][1] * X[i][1];
            X[i][9] = X[i][2] * X[i][2];

            XPlan[i][0] = X[i][1] == 1 ? X1[1] : X1[0];
            XPlan[i][1] = X[i][2] == 1 ? X2[1] : X2[0];
            XPlan[i][2] = X[i][3] == 1 ? X3[1] : X3[0];

            XPlan[i][3] = XPlan[i][0] * XPlan[i][1];
            XPlan[i][4] = XPlan[i][0] * XPlan[i][2];
            XPlan[i][5] = XPlan[i][1] * XPlan[i][2];
            XPlan[i][6] = XPlan[i][0] * XPlan[i][1] * XPlan[i][2];
            XPlan[i][7] = XPlan[i][0] * XPlan[i][0];
            XPlan[i][8] = XPlan[i][1] * XPlan[i][1];
            XPlan[i][9] = XPlan[i][2] * XPlan[i][2];
        }
        for (int i = 0; i < 3; i++) {
            X[8 + i * 2][i] = -1.215;
            X[9 + i * 2][i] = 1.215;

            X[8 + i * 2][7 + i] = X[8][0] * X[8][0];
            X[9 + i * 2][7 + i] = X[8][7];
        }

        for (int i = 8; i < X.length; i++){
            XPlan[i][0] = (X1[0] + X1[1]) / 2.0;
            XPlan[i][1] = (X2[0] + X2[1]) / 2.0;
            XPlan[i][2] = (X3[0] + X3[1]) / 2.0;

            XPlan[i][3] = XPlan[i][0] * XPlan[i][1];
            XPlan[i][4] = XPlan[i][0] * XPlan[i][2];
            XPlan[i][5] = XPlan[i][1] * XPlan[i][2];
            XPlan[i][6] = XPlan[i][0] * XPlan[i][1] * XPlan[i][2];
            XPlan[i][7] = XPlan[i][0] * XPlan[i][0];
            XPlan[i][8] = XPlan[i][1] * XPlan[i][1];
            XPlan[i][9] = XPlan[i][2] * XPlan[i][2];
        }

        XPlan[8][0] += (-1.215) * (X1[1] - (X1[0] + X1[1]) / 2.0);
        XPlan[9][0] += 1.215 * (X1[1] - (X1[0] + X1[1]) / 2.0);

        XPlan[10][1] += (-1.215) * (X2[1] - (X2[0] + X2[1]) / 2.0);
        XPlan[11][1] += 1.215 * (X2[1] - (X2[0] + X2[1]) / 2.0);

        XPlan[12][2] += (-1.215) * (X3[1] - (X3[0] + X3[1]) / 2.0);
        XPlan[13][2] += 1.215 * (X3[1] - (X3[0] + X3[1]) / 2.0);

        XPlan[8][7] = XPlan[8][0] * XPlan[8][0];
        XPlan[9][7] = XPlan[9][0] * XPlan[9][0];

        XPlan[10][8] = XPlan[10][1] * XPlan[10][1];
        XPlan[11][8] = XPlan[11][1] * XPlan[11][1];

        XPlan[12][9] = XPlan[12][2] * XPlan[12][2];
        XPlan[13][9] = XPlan[13][2] * XPlan[13][2];
    }

    public void start() {
        if (lab4.start())
            return;             // Finish!!
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
        checkAdequacy();
    }

    private void createPlanMatrix(int m) {
        Y = new double[15][m + 1];

        for (int i = 0; i < Y.length; i++) {
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
        return sum / N;
    }

    private double getSumY(int[] col) {
        double sum = 0;
        for (int i = 0; i < N; i++) {
            double temp = Y[i][m];
            for (int j = 0; j < col.length; j++)
                temp *= XPlan[i][col[j] - 1];
            sum += temp;
        }
        return sum / N;
    }

    private void solveEquations() {
        M = new double[11][11];
        M[0] = new double[]{1,                          getSumX(new int[]{1}),    getSumX(new int[]{2}),    getSumX(new int[]{3}),    getSumX(new int[]{4}),    getSumX(new int[]{5}),    getSumX(new int[]{6}),    getSumX(new int[]{7}),                          0,                       0,                          0};
        M[1] = new double[]{getSumX(new int[]{1}),      getSumX(new int[]{1, 1}), getSumX(new int[]{1, 2}), getSumX(new int[]{1, 3}), getSumX(new int[]{1, 4}), getSumX(new int[]{1, 5}), getSumX(new int[]{1, 6}), getSumX(new int[]{1, 7}), getSumX(new int[]{1, 8}), getSumX(new int[]{1, 9}), getSumX(new int[]{1, 10})};
        M[2] = new double[]{getSumX(new int[]{2}),      getSumX(new int[]{1, 2}), getSumX(new int[]{2, 2}), getSumX(new int[]{2, 3}), getSumX(new int[]{2, 4}), getSumX(new int[]{2, 5}), getSumX(new int[]{2, 6}), getSumX(new int[]{2, 7}), getSumX(new int[]{2, 8}), getSumX(new int[]{2, 9}), getSumX(new int[]{2, 10})};
        M[3] = new double[]{getSumX(new int[]{3}),      getSumX(new int[]{1, 3}), getSumX(new int[]{3, 2}), getSumX(new int[]{3, 3}), getSumX(new int[]{3, 4}), getSumX(new int[]{3, 5}), getSumX(new int[]{3, 6}), getSumX(new int[]{3, 7}), getSumX(new int[]{3, 8}), getSumX(new int[]{3, 9}), getSumX(new int[]{3, 10})};

        M[4] = new double[]{getSumX(new int[]{4}),      getSumX(new int[]{4, 1}), getSumX(new int[]{4, 2}), getSumX(new int[]{4, 3}), getSumX(new int[]{4, 4}), getSumX(new int[]{4, 5}), getSumX(new int[]{4, 6}), getSumX(new int[]{4, 7}), getSumX(new int[]{4, 8}), getSumX(new int[]{4, 9}), getSumX(new int[]{4, 10})};
        M[5] = new double[]{getSumX(new int[]{5}),      getSumX(new int[]{5, 1}), getSumX(new int[]{5, 2}), getSumX(new int[]{5, 3}), getSumX(new int[]{5, 4}), getSumX(new int[]{5, 5}), getSumX(new int[]{5, 6}), getSumX(new int[]{5, 7}), getSumX(new int[]{5, 8}), getSumX(new int[]{5, 9}), getSumX(new int[]{5, 10})};
        M[6] = new double[]{getSumX(new int[]{6}),      getSumX(new int[]{6, 1}), getSumX(new int[]{6, 2}), getSumX(new int[]{6, 3}), getSumX(new int[]{6, 4}), getSumX(new int[]{6, 5}), getSumX(new int[]{6, 6}), getSumX(new int[]{6, 7}), getSumX(new int[]{6, 8}), getSumX(new int[]{6, 9}), getSumX(new int[]{6, 10})};

        M[7] = new double[]{getSumX(new int[]{7}),      getSumX(new int[]{7, 1}), getSumX(new int[]{7, 2}), getSumX(new int[]{7, 3}), getSumX(new int[]{7, 4}), getSumX(new int[]{7, 5}), getSumX(new int[]{7, 6}), getSumX(new int[]{7, 7}), getSumX(new int[]{7, 8}), getSumX(new int[]{7, 9}), getSumX(new int[]{7, 10})};
        M[8] = new double[]{getSumX(new int[]{8}),      getSumX(new int[]{8, 1}), getSumX(new int[]{8, 2}), getSumX(new int[]{8, 3}), getSumX(new int[]{8, 4}), getSumX(new int[]{8, 5}), getSumX(new int[]{8, 6}), getSumX(new int[]{8, 7}), getSumX(new int[]{8, 8}), getSumX(new int[]{8, 9}), getSumX(new int[]{8, 10})};
        M[9] = new double[]{getSumX(new int[]{9}),      getSumX(new int[]{9, 1}), getSumX(new int[]{9, 2}), getSumX(new int[]{9, 3}), getSumX(new int[]{9, 4}), getSumX(new int[]{9, 5}), getSumX(new int[]{9, 6}), getSumX(new int[]{9, 7}), getSumX(new int[]{9, 8}), getSumX(new int[]{9, 9}), getSumX(new int[]{9, 10})};
        M[10] = new double[]{getSumX(new int[]{10}),    getSumX(new int[]{10, 1}),getSumX(new int[]{10, 2}),getSumX(new int[]{10, 3}),getSumX(new int[]{10, 4}),getSumX(new int[]{10, 5}),getSumX(new int[]{10, 6}),getSumX(new int[]{10, 7}),getSumX(new int[]{10, 8}),getSumX(new int[]{10, 9}),getSumX(new int[]{10, 10})};


        K = new double[11][1];          //  Contain Y * X(i), in matrix performance it's --> M * B = K (A * X = Y (Y = K, X = B, M = A))  
        K[0][0] = getSumY(new int[0]);
        K[1][0] = getSumY(new int[]{1});
        K[2][0] = getSumY(new int[]{2});
        K[3][0] = getSumY(new int[]{3});
        K[4][0] = getSumY(new int[]{4});
        K[5][0] = getSumY(new int[]{5});
        K[6][0] = getSumY(new int[]{6});
        K[7][0] = getSumY(new int[]{7});
        K[8][0] = getSumY(new int[]{8});
        K[9][0] = getSumY(new int[]{9});


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

        double[] beta = new double[N];

        for (int i = 0; i < 10; i++) {
            beta[i] = 0;
            for (int j = 0; j < N; j++)
                // In Y[][m] locate average Value Y
                beta[i] += Y[j][m] * X[j][i];
        }

        double[] t = new double[N];
        yPractical = new double[N];

        System.out.println("\n\nStudentsa Measure:");
        for (int i = 0; i < 10; i++) {
            t[i] = beta[i] / java.lang.Math.sqrt(totalDispersion / (N * N * m)) * (beta[i] < 0 ? (-1) : 1);

            System.out.printf("t%d = %8.3f\n", i, t[i]);

            B[i][0]  = (t[i] < math.studentaMeasure((m - 1) * N) ? 0 : 1);
        }

        for (int i = 0; i < N; i++)
            yPractical[i] = B[0][0] + B[1][0] * XPlan[i][0] + B[2][0] * XPlan[i][1] + B[3][0] * XPlan[i][2] + B[4][0] * XPlan[i][3]
                    + B[5][0] * XPlan[i][4] + B[6][0] * XPlan[i][5] + B[7][0] * XPlan[i][6] + B[8][0] * XPlan[i][7] + B[9][0] * XPlan[i][8] + B[10][0] * XPlan[i][9];

        System.out.printf("t = %8.3f\n\n", math.studentaMeasure((m - 1) * N));
    }

    private void checkAdequacy() {

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
            m++;
            start();
        }
        else
            System.out.println("Equation is Adequacy");
    }

}
