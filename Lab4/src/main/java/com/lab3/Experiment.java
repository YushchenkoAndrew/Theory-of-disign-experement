package main.java.com.lab3;

import article.MathPackage.Matrix;
import main.java.com.lab4.Math;

public class Experiment {

    private Matrix matrix = new Matrix();
    private Math math = new Math();

    private int[][] x = new int[][]
                    {{1, -1, -1, -1},
                    {1, -1, 1, 1},
                    {1, 1, -1, 1},
                    {1, 1, 1, -1} };

    private int[][] Xplan =
                    {{10, -25, 10},
                    {10, 10, 15},
                    {60, -25, 15},
                    {60, 10, 10} };

    private int f1 = 2;
    private int f2 = 4;

    private double[] b = new double[4];

    private int m = 3;

    private double[][] Y;

    private double Ymin = 192.34;
    private double Ymax = 228.33;

    private double mYtotal;

    private double[] mx = new double[]{0, 0, 0};
    private double[] my = new double[4];

    private double[][] X;

    private void drawLine() {
        System.out.println("\n");
        for (int i = 0; i < 8; i++) {
            System.out.print("-------");
        }
        System.out.println();
    }

    public boolean startExperiment() {
        createPlanMatrix();
        solveEquation();
        return checking();
    }

    private void createPlanMatrix() {
        drawLine();
        System.out.print("|  X1\t  X2\t  X3\t|\tY1\t Y2\t   Y3\t|");
        drawLine();

        Y = new double[4][m];
        for (int i = 0; i < 4; i++) {
            my[i] = 0;
            System.out.printf("|%4d\t%4d\t%4d\t|", Xplan[i][0], Xplan[i][1], Xplan[i][2]);
            for (int j = 0; j < m; j++) {
                Y[i][j] = (java.lang.Math.random() % (Ymax - Ymin)) + Ymin;

                System.out.printf("%10.3f", Y[i][j]);

                my[i] += Y[i][j] / m;
            }

            for (int j = 0; j < 3; j++) {
                mx[j] += Xplan[i][j] / 4.0;
            }
            System.out.print("\t|");
            drawLine();
        }

        mYtotal = (my[0] + my[1] + my[2] + my[3]) / 4;
    }

    private void solveEquation() {
        double[][] A = new double[4][4];
        double[][] Y = new double[4][1];

        //a1, a2, a3
        Y[0][0] = mYtotal;
        Y[1][0] = (Xplan[0][0] * my[0] + Xplan[1][0] * my[1] + Xplan[2][0] * my[2] + Xplan[3][0] * my[3]) / 4.0;
        Y[2][0] = (Xplan[0][1] * my[0] + Xplan[1][1] * my[1] + Xplan[2][1] * my[2] + Xplan[3][1] * my[3]) / 4.0;
        Y[3][0] = (Xplan[0][2] * my[0] + Xplan[1][2] * my[1] + Xplan[2][2] * my[2] + Xplan[3][2] * my[3]) / 4.0;

        A[0][0] = 1;
        A[0][1] = mx[0];
        A[0][2] = mx[1];
        A[0][3] = mx[2];

        A[1][0] = mx[0];
        A[2][0] = mx[1];
        A[3][0] = mx[2];

        //a11, a22, a33
        A[1][1] = (Xplan[0][0] * Xplan[0][0] + Xplan[1][0] * Xplan[1][0] + Xplan[2][0] * Xplan[2][0] + Xplan[3][0] * Xplan[3][0]) / 4.0;
        A[2][2] = (Xplan[0][1] * Xplan[0][1] + Xplan[1][1] * Xplan[1][1] + Xplan[2][1] * Xplan[2][1] + Xplan[3][1] * Xplan[3][1]) / 4.0;
        A[3][3] = (Xplan[0][2] * Xplan[0][2] + Xplan[1][2] * Xplan[1][2] + Xplan[2][2] * Xplan[2][2] + Xplan[3][2] * Xplan[3][2]) / 4.0;

        //a12, a13, a23
        A[1][2] = (Xplan[0][0] * Xplan[0][1] + Xplan[1][0] * Xplan[1][1] + Xplan[2][0] * Xplan[2][1] + Xplan[3][0] * Xplan[3][1]) / 4.0;
        A[1][3] = (Xplan[0][0] * Xplan[0][2] + Xplan[1][0] * Xplan[1][2] + Xplan[2][0] * Xplan[2][2] + Xplan[3][0] * Xplan[3][2]) / 4.0;
        A[2][3] = (Xplan[0][2] * Xplan[0][1] + Xplan[1][2] * Xplan[1][1] + Xplan[2][2] * Xplan[2][1] + Xplan[3][2] * Xplan[3][1]) / 4.0;

        A[2][1] = A[1][2];
        A[3][1] = A[1][3];
        A[3][2] = A[2][3];
        X = matrix.getArguments(A, Y);
    }

    private boolean checking() {
        System.out.println("\nEquation:\nY = ");
        for (int i = 0; i < 4; i++) {
            System.out.printf("%5.3f ", b[i]);
            if (i != 0) {
                System.out.printf("* X%d ", i);
            }
            if (i != 3) {
                System.out.print("+ ");
            }
        }

        System.out.print("\n");

        double[] dispertion = new double[]{ math.findDispersion(Y[0], my[0], m),
                math.findDispersion(Y[1], my[1], m),
                math.findDispersion(Y[2], my[2], m),
                math.findDispersion(Y[3], my[3], m) };

        double totalDispersion = 0;
        double maxDispersion = Integer.MAX_VALUE;

        for (int i = 0; i < 4; i++) {
            if (dispertion[i] > maxDispersion) {
                maxDispersion = dispertion[i];
            }
            totalDispersion += dispertion[i];
        }
        System.out.print("Koharena Measure:\n");
        System.out.printf("Gp = %5.3f\n", maxDispersion / totalDispersion);
        System.out.printf("Gt = %5.3f\n\n", math.koharenaMeasure(f1, f2));

        if (maxDispersion / totalDispersion > math.koharenaMeasure(f1, f2)) {
            m++;
            System.out.print("Dispartion is not Smooth");
            startExperiment();
        }

        double[] beta = new double[]{ math.findCoeficentStudentsa(new int[]{1, 1, 1, 1}, my, 4),
            math.findCoeficentStudentsa(new int[]{-1, -1, 1, 1}, my, 4),
            math.findCoeficentStudentsa(new int[]{-1, 1, -1, 1}, my, 4),
            math.findCoeficentStudentsa(new int[]{-1, 1, 1, -1}, my, 4) };

        double[] t = new double[4];

        int len = 0;
        double[] yPract = new double[]{0, 0, 0, 0};

        System.out.print("Studentsa Measure:\n");
        for (int i = 0; i < 4; i++) {
            t[i] = beta[i] / java.lang.Math.sqrt(totalDispersion / (4 * 4 * m)) * (beta[i] < 0 ? (-1) : 1);

            System.out.printf("t%d = %8.3f\n", i, t[i]);
            for (int j = 0; j < 4; j++) {
                yPract[j] += b[i] * (t[i] < math.studentaMeasure(f1 * f2) ? 0 : 1) * (i == 0 ? 1 : Xplan[j][i - 1]);

            }

        }

        System.out.printf("t = %8.3f\n\n", math.studentaMeasure(f1 * f2));

        System.out.print("Fishera Measure:\n");


        System.out.printf("Sad = %8.3f\n", math.findCoefficientFishera(yPract, my, 4) * m / 2);

        System.out.printf("Fp = %8.3f\n", (math.findCoefficientFishera(yPract, my, 4) * m / 2) / ( totalDispersion / (4 * 4 * m)));
        System.out.printf("Ft = %8.3f\n", math.fisheraMeasure(8, 2));
        if (math.fisheraMeasure(8, 2) < (math.findCoefficientFishera(yPract, my, 4) * m / 2) / (totalDispersion / (4 * 4 * m))) {
            System.out.println("Equation is not edequate");
            return false;
        }
        else
            System.out.println("Equation is edequate");

        System.out.print("\n");
        return true;
    }
}
