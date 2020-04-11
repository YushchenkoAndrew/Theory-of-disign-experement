package article.ConcurrencyMode;

import static java.util.stream.IntStream.*;

public class BinaryOperation {

    public boolean[] coding(char a, char b) {
        if (a == '0' && b == '0') {
            return new boolean[]{false, false, false, false};
        }
        if (a == '0' && b == '1') {
            return new boolean[]{false, false, false, true};
        }
        if (a == '1' && b == '1') {
            return new boolean[]{true, true, true, true};
        }
        if (a == '1' && b == '0') {
            return new boolean[]{false, false, true, false};
        }
        return null;
    }

    private boolean p = false;

    public boolean sumBin(boolean x, boolean y, boolean c) {
        p = x & y | y & c | x & c;
        return x & !p | c & !p | y & !p | x & y & c;
    }

    private boolean[][] z;
    private boolean[][] y;  //for function with an argument

    private ComputerCoding coding;

    public void createIndexZ(int i, int j, boolean[][] h) {
        coding = new ComputerCoding();
        z = coding.createIndexZ(i, j, h, z);
    }

    public void createIndexZ(int i, boolean[][] h) {         // <-- for division
        coding = new ComputerCoding();
        z = coding.createIndexZ(i, h, z);
    }

    public void createIndexY(int i, boolean[][] h) {         // <-- (redundant system) finding root
        coding = new ComputerCoding();
        y = coding.createIndexY(i, h, y);
    }

    public boolean compare(boolean[] x, boolean[] y){      //x > y --> true if x < y false
        int min = x.length > y.length ? y.length : x.length;
        for (int i = 0; i < min; i++)
            if (x[i] ^ y[i]) {
                if (x[i])
                    return true;
                if (y[i])
                    return false;
            }
        return true;
    }

    public boolean[] shiftL(boolean[] x, int start) {
        if (x.length - 1 - start >= 0) System.arraycopy(x, start + 1, x, start, x.length - 1 - start);
        x[x.length - 1] = false;
        return x;
    }

    public boolean[] shiftR(boolean[] x, int start) {
        if (x.length - start >= 0) System.arraycopy(x, start - 1, x, start, x.length - start);
//        x[x.length - 1] = false;
        return x;
    }


    public boolean[][] shift(boolean[][] x, int start, boolean flag) {       //if flag == false then L else right
        boolean[][] z = new boolean[x.length][4];
        if (!flag) {
            range(start, x.length).forEach(i -> z[i - start] = x[i].clone());
        } else {
            range(0, x.length - start).forEach(i -> z[i + start] = x[i].clone());
        }
        return z;
    }


    public boolean[] createArrayContainOne(int index, boolean flag, int size) {
        boolean[] z = new boolean[size];
        if (!flag) {
            z[index] = true;
        } else
            for (int i = 0; i < index + 1; i++) {
                z[i] = true;
            }
        return z;
    }

    private boolean[][] shift(boolean[][] x, int index) {
        boolean[][] z = new boolean[x.length][4];
        for (int i = index; i < x.length; i++)
            z[i] = x[i - index].clone();
        return z;
    }

    public boolean[][][] correctionMantissas(boolean[][] a, boolean[][] b, boolean[][] c, boolean[][] d, int[] exponents) {
        int delta = exponents[0] + exponents[1] - exponents[2];
        if (delta > 0)
            c = shift(c, delta);
        else {
            a = shift(a, (delta / 2 + delta % 2) * (-1));
            b = shift(b, (delta / 2) * (-1));
        }
//        if (exponents[3] < 0)
//            d = shift(d, exponents[3] * (-1));
        return new boolean[][][]{a, b, c, d};
    }

    public boolean[][][] correctionMantissas(boolean[][] x, boolean[][] y, int[] exponents) {
        int delta = exponents[0] - exponents[1];
        if (delta > 0)
            y = shift(y, delta);
        else {
            x = shift(x, delta * (-1));
        }

        return new boolean[][][]{x, y};
    }


    public boolean[][] createRightArray(boolean[][] x, int size, int count) {      //flag is for different situation if false --> multiplication else for division
        boolean[][] z = new boolean[size][4];
        int k = 0;
        for (int i = 0; i < size; i++) {
            if (i < size - count) {
                z[i] = new boolean[]{false, false, false, false};
            } else {
                if (k < x.length)
                    z[i] = x[k].clone();
                k++;
            }
        }
        return z;
    }

    public boolean[][] getOfRange(boolean[][] x, int start, int end){
        boolean[][] z = new boolean[end - start][x[0].length];
        for (int i = start; i < end; i++)
            z[i - start] = x[i].clone();
        return z;
    }

    public boolean isSum(boolean[][] x, int index) {
        return (x[index][2] || x[index][3]) && (!x[index][2] || !x[index][3]);
    }

    public boolean isDeduct(boolean[][] x, int index) {
        return x[index][2];
    }

    public void setSizeZ(int size) {
        z = new boolean[size][4];
    }

    public void setSizeY(int size) {
        y = new boolean[size][4];
    }


    public boolean isP() {
        return p;
    }

    public boolean[][] getZ() {
        return z;
    }

    public boolean[][] getY() {
        return y;
    }
}
