package article.MathPackage;

import article.CorrectionPrint;

/**
 * It's created for make Neural Web
 * It's created by using a book, named "Math for Engineer"
 * the reason of why variable is uppercase, because it's MATRIX!!!
 * Created by Andrew Yushchenko
 */

public final class Matrix {

    public boolean isSymmetric (double[][] A) {      //matrix A have to be a square Matrix

        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < i; j++)
                if (A[i][j] != A[j][i])
                    return false;
        return true;
    }

    private double[][] transpositionSquareMatrix(double[][] A) {      //make transpositionSquareMatrix Matrix towards to main diagonal
        double[][] C = new double[A.length][A.length];      //matrix A have to be a square Matrix

        for (int i = 0; i < A.length; i++) {
            C[i][i] = A[i][i];
            for (int j = 0; j < i; j++) {
                C[i][j] = A[j][i];
                C[j][i] = A[i][j];
            }
        }
        return C;
    }

    public boolean isSkewSymmetric (double[][] A) {//matrix A have to be a square Matrix, element a(i, k) = -a(i , k)

        for (int i = 0; i < A.length; i++) {
            if (A[i][i] != 0)
                return false;
            for (int j = 0; j < i; j++)
                if (A[i][j] != -A[j][i])
                    return false;
        }
        return true;
    }

    public boolean isDiagonalMatrix (double[][] A) {//matrix A have to be a square Matrix, element a(i, k) = -a(i , k)

        for (int i = 0; i < A.length; i++) {
            if (A[i][i] == 0)
                return false;
            for (int j = 0; j < i; j++)
                if ((A[i][j] != 0) || (A[j][i] != 0))
                    return false;
        }
        return true;
    }

    public double[][] transposition(double[][] A) {
        if (A.length == A[0].length)
            return transpositionSquareMatrix(A);
        double[][] C = new double[A[0].length][A.length];

        for(int i = 0; i < A.length; i++)
            for (int j = 0; j < A[0].length; j++)
                C[j][i] = A[i][j];

        return C;
    }

    /**
     * Just a simple sum two Matrix
     * row and columns have to be a same
     * @param A     First Matrix
     * @param B     Second Matrix
     * @return      return result of sum
     */
    public double[][] sum(double[][] A, double[][] B) {
        double[][] C = new double[A.length][B[0].length];

        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < B[0].length; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }

    public double[][] multiplication(double[][] A, double k) {
        if (A == null || k == 0)
            return null;
        double[][] C = new double[A.length][A[0].length];

        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < A[0].length; j++)
                C[i][j] = A[i][j] * k;
        return C;
    }

    public double[][] multiplication(double[][] A, double[][] B) {
        if (A == null || B == null)
            return null;
        double[][] C = new double[A.length][B[0].length];

        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < B[0].length; j++)
                for (int k = 0; k < A[0].length; k++)
                    C[i][j] += A[i][k] * B[k][j];
        return C;
    }

    private double determinantSecondRangeMatrix(double[][] A) {
        return A[0][0] * A[1][1] - A[0][1] * A[1][0];
    }

    public double determinant(double[][] A) {
        if (A.length == 1)
            return A[0][0];
        if (A.length == 2) {
            return determinantSecondRangeMatrix(A);

        }
        if (A.length < 10) {
            double determinant = 0;
            for (int i = 0; i < A.length; i++)
                determinant += A[0][i] * determinant(cutRowAndColumn(A.clone(), 0, i)) * (i % 2 == 1 ? -1 : 1);
            return determinant;
        }
        return determinantMethodGauss(A.clone(), 0);
    }

    private void decrementRow(double[][] A, int index) {
        if (A[index][index] == 0) return;
        for (int i = index + 1; i < A.length; i++) {
            double store = A[i][index] / A[index][index];
//            A[i][index] = 0;      //it's not so important to use so, we can miss it
            for (int j = index + 1; j < A.length; j++)
                A[i][j] = A[i][j] - A[index][j] * store;
        }

    }

    /**
     * This method ground by recursion solving, so we know how that can end :)
     * This method invented Gauss and this position of solving determinant seem more powerful(take less time for finding it)
     * @param A     Matrix
     * @param index     this is index of row which program decrement with another
     * @return      return determinant
     */
    private double determinantMethodGauss(double[][] A, int index) {
        if (index == A.length)
            return 1;
        decrementRow(A, index);
        return A[index][index] * determinantMethodGauss(A,index + 1);
    }

    /**
     * This method need for calculate determinant, method named cut rows and colums in my case row always set 0
     *
     * @param A -- Matrix
     * @param column    column for cut
     * @param row       row for cut
     * @return  this method return a second dimension array
     */
    private double[][] cutRowAndColumn(double[][] A, int row, int column) {
        double[][] B = new double[A.length - 1][A[0].length - 1];

        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B.length; j++)
                B[i][j] = A[i + (i < row ? 0 : 1)][j + (j < column ? 0 : 1)];
        }
        return B;
    }

    public double[][] getTurnedMatrix(double[][] A) {
        double[][] B = new double[A.length][A[0].length];

        if (determinant(A) == 0)
            return null;

        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < A.length; j++)
                B[j][i] = determinant(cutRowAndColumn(A, i, j)) * ((i + j) % 2 == 1 ? -1 : 1);

        return multiplication(B, 1 / determinant(A));
    }

    /**
     * it's method just exist for make main code seem easier to read
     * @param i     index of row
     * @param j     index of column
     * @param length    length for compare
     * @return  return a number for make correct piece matrix
     */
    private byte getIndexOfPieceMatrix(int i, int j, int length) {
        if ((i < length) && (j < length))
            return 0;
        if (i < length)
            return 1;
        if (j < length)
            return 2;
        return 3;
    }

    public double[][] getArguments(double[][] A, double[][] Y) {
        if (A.length < 40)
            return multiplication(getTurnedMatrix(A), Y);
        return getArgumentsPiecesMethod(A, Y);
    }

    /**
     * It's more useful for solve a bigger Matrix
     * @param A     Martix
     * @param Y     Martix (result of system)
     * @return    Matrix of X
     */
    private double[][] getArgumentsPiecesMethod(double[][] A, double[][] Y) {
//        I don't know, theoretically I need to check determinant but you sure that it's really need to
//        if (determinant(A) == 0)
//            return null;

        double[][][] a = new double[4][][];     //it's Matrix A but separate in 4 little Matrix
        int storeValue = A.length / 2 + A.length % 2;
        a[0] = new double[A.length - storeValue][A.length - storeValue];
        a[1] = new double[A.length - storeValue][storeValue];
        a[2] = new double[storeValue][A.length - storeValue];
        a[3] = new double[storeValue][storeValue];

        double[][][] y = new double[2][][];     //idea the same like in a Matrix, we know that count of columns always the same, soo...
        y[0] = new double[Y.length - storeValue][Y[0].length];
        y[1] = new double[storeValue][Y[0].length];

        //Matrix seemed like {A1, A2, A3, A4}, in which A1 (so on ...) its the small piece of A Matrix

        for(int i = 0; i < A.length; i++) {
            int correction = i < A.length / 2 ? 0 : A.length / 2;
            for (int j = 0; j < A.length; j++) {
                byte index = getIndexOfPieceMatrix(i, j, A.length / 2);
                a[index][i - correction][j - (j < A.length / 2 ? 0 : A.length / 2)] = A[i][j];
            }
            y[i < A.length / 2 ? 0 : 1][i - correction][0] = Y[i][0];
        }

        a[3] = getTurnedMatrix(a[3]);     //in that moment we don't need Matrix A3, so we can just use it for store

        if (a[3] == null)
            return null;

        double[][] store = multiplication(a[1], a[3]);

        double[][] store2 = getTurnedMatrix(sum(a[0], multiplication(multiplication(store, a[2]), -1)));

        if (store2 == null)
            return null;

        double[][] X1 = multiplication(store2, sum(y[0], multiplication(multiplication(store, y[1]), -1)));

        return combineMatrix(X1, multiplication(a[3], sum(y[1], multiplication(multiplication(a[2], X1), -1))));
    }

    /**
     * Just combine two Matrix into one
     * Matrix need to be the same, rule like in sum 2 Matrix
     * @param A     First Matrix
     * @param B     Second Matrix
     * @return      return them combination
     */
    private double[][] combineMatrix(double[][] A, double[][] B) {
        double[][] C = new double[A.length + B.length][A[0].length];

        for (int i = 0; i < (A.length + B.length); i++)
            for (int j = 0; j < A[0].length; j++)
                C[i][j] = i < A.length ? A[i][j] : B[i - A.length][j];

        return C;
    }

    public double[][] rotationMatrixOZ(double degree) {
        return new double[][]{{Math.cos(degree), -Math.sin(degree), 0}, {Math.sin(degree), Math.cos(degree), 0}, {0, 0, 1}};
    }

    public double[][] rotationMatrixOX(double degree) {
        return new double[][]{{1, 0, 0}, {0, Math.cos(degree), -Math.sin(degree)}, {0, Math.sin(degree), Math.cos(degree)}};
    }

    public double[][] rotationMatrixOY(double degree) {
        return new double[][]{{Math.cos(degree), 0, Math.sin(degree)}, {0, 1, 0}, {-Math.sin(degree), 0, Math.cos(degree)}};
    }
}
