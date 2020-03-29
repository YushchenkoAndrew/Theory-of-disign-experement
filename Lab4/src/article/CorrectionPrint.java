package article;

import java.util.ArrayList;
import java.util.List;

public class CorrectionPrint {

    public static StringBuilder rightPrint(boolean[][] register, int i) {
        StringBuilder str = new StringBuilder();
        for (int j = 0; j < register[i].length; j++) {
            if (j == 2)
                str.append(".");
            str.append(register[i][j] ? "1" : "0");
        }
        return str;
    }

    public static StringBuilder getCorrectedZ(boolean[][] z, int i, boolean flag) {    //if flag == 1 it return for correctionTableString else for TextField
        StringBuilder str = new StringBuilder();
        for (int k = 0; k <= i; k++) {
            if (z[k][2] && z[k][3])
                str.append(flag ? " \u03051\u0305 " : "11");
            else if (!z[k][2] && z[k][3])
                str.append(flag ? "1" : "01");
            else if (!z[k][2] && !z[k][3])
                str.append(flag ? "0" : "00");
            else if (z[k][2] && !z[k][3])
                str.append(flag ? "2" : "10");
        }
        return str;
    }

    public static String getCorrectedZ(boolean[] z, int i) {
        StringBuilder str = new StringBuilder();
        for (int k = 0; k <= i + 2; k++) {
            if (k == 2)
                str.append(".");
            if (k < z.length)
                str.append(z[k] ? "1" : "0");
        }
        return str.toString();
    }


    public static void printArray(boolean[][] x) {
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++)
                System.out.print(x[i][j] ? 1 : 0);
            System.out.println();
        }
        System.out.println();
    }

    public static void printArray(boolean[] x) {
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i] ? 1 : 0);
        }
        System.out.println();
    }

    public static void printArray(int[] x) {
        for (int i = 0; i < x.length; i++) {
            System.out.printf("%5d", x[i]);
        }
        System.out.println();
    }

    public static void printArray(double[] x) {
        for (int i = 0; i < x.length; i++) {
            System.out.printf("%10.3f", x[i]);
        }
        System.out.println();
    }

    public static void printArray(int[][] x) {
        for (int i = 0; i < x.length; i++)
            printArray(x[i]);

        System.out.println();
    }

    public static void printArray(double[][] x) {
        if (x == null)
            return;

        for (int i = 0; i < x.length; i++) {
            printArray(x[i]);
        }
        System.out.println();
    }
}
