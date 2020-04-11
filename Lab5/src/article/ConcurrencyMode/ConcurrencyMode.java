package article.ConcurrencyMode;

import article.CorrectionPrint;

import java.util.stream.IntStream;

import static java.lang.Math.abs;

public class ConcurrencyMode {
    private boolean[][] x;
    private boolean[][] y;
    private boolean[][] c;
    private boolean[][] d;
    private int p;

    private Sum sum;
    private BinaryOperation bin = new BinaryOperation();
    private Operation operation;

    public void setData(String x, int p) {
//        x = "010100000001";     //testing input for root
//        p = 3;

        this.p = p;
        this.x = new boolean[x.length() / 2][4];
        for (int i = 0; i < x.length(); i += 2)
            this.x[i / 2] = bin.coding(x.charAt(i), x.charAt(i + 1));
    }

    public void setData(String x, String y, int p, int[] exponents) {
//        x = "0001000101";     //testing input for Sum
//        y = "1111110100";
//        x = "0100011101";     //testing input for Multiplication
//        y = "0101111101";
//        x = "0101111100";     //testing input for division
//        y = "0111010111";
//        x = "00000101";     //testing input for hypotenuseFunction
//        y = "00010000";

        this.x = new boolean[x.length() / 2][4];
        this.y = new boolean[x.length() / 2][4];
        this.p = p;
        for (int i = 0; i < x.length(); i += 2) {
            this.x[i / 2] = bin.coding(x.charAt(i), x.charAt(i + 1));
            this.y[i / 2] = bin.coding(y.charAt(i), y.charAt(i + 1));
        }
    }

    public void setData(String a, String b, String c, String d, int p, int[] exponents) {
//        a = "010111";     //testing input for function1
//        b = "010100";
//        c = "010101";
//        d = "010111";
//        exponents = new int[]{1, 2, 1, 2};
//        p = 5;

//        a = "01010111";     //testing input for function1 (second)
//        b = "01011101";
//        c = "01010100";
//        d = "01110101";
//        exponents = new int[]{1, 0, 3, -1};
//        p = 5;

        System.out.println(exponents[0]);
        System.out.println(exponents[1]);
        System.out.println(exponents[2]);
        System.out.println(exponents[3]);

        System.out.println();
        int max = abs(Integer.max(Integer.max(exponents[0], exponents[1]), Integer.max(exponents[2], exponents[3])));

        x = new boolean[a.length() / 2 + max][4];
        y = new boolean[a.length() / 2 + max][4];
        this.c = new boolean[a.length() / 2 + max][4];
        this.d = new boolean[a.length() / 2 + max][4];
        this.p = p;
        for (int i = 0; i < a.length(); i += 2) {
            this.x[i / 2] = bin.coding(a.charAt(i), a.charAt(i + 1));
            this.y[i / 2] = bin.coding(b.charAt(i), b.charAt(i + 1));
            this.c[i / 2] = bin.coding(c.charAt(i), c.charAt(i + 1));
            this.d[i / 2] = bin.coding(d.charAt(i), d.charAt(i + 1));
        }

        testing(x, y, this.c, this.d, exponents);

        boolean[][][] store = bin.correctionMantissas(x, y, this.c, this.d, exponents);
        x = store[0].clone();
        y = store[1].clone();
        this.c = store[2].clone();
        this.d = store[3].clone();
    }

    private int selectedOperation;

    public void makeOperations(int index) {
        selectedOperation = index;
        switch (index) {
            case 0: {
                operation = new Sum(x, y);
                break;
            }
            case 1: {
                operation = new Multiplication(x, y);
                break;
            }
            case 2: {
                operation = new Division(this.x, this.y);
                break;
            }
            case 3: {
                operation = new Function1(x, y, this.c, this.d, p);
                break;
            }
            case 4: {
                operation = new Root(this.x, p);
                break;
            }

            case 5: {
                operation = new PythagorasFunction(this.x, this.y, p);
                break;
            }
        }

        System.out.println();
        CorrectionPrint.printArray(operation.getZ());
        System.out.println();

        CorrectionPrint.printArray(operation.getH());
        System.out.println();
    }

    public boolean[][] getX() {
        return x;
    }

    public boolean[][] getY() {
        return y;
    }

    public boolean[][] getC() {
        return c;
    }

    public boolean[][] getD() {
        return d;
    }


    public boolean[] getInComputerCoding(boolean[][] x) {
        ComputerCoding computerCoding;
        if (selectedOperation == 4 || selectedOperation == 5) {
            computerCoding = new ComputerCoding(x, false, 2);
        }
        else
            computerCoding = new ComputerCoding(x, false, -1);
        return computerCoding.getResult();
    }

    public boolean[] getInComputerCoding(boolean[][] x, int concurrencyElement) {
        ComputerCoding computerCoding;
        computerCoding = new ComputerCoding(x, false, concurrencyElement);
        return computerCoding.getResult();
    }

    //todo to finish testing system

    void testing(boolean[][] a, boolean[][] b, boolean[][] c, boolean[][] d, int[] exponents) {
        Multiplication multiplication = new Multiplication(a, b);
        boolean[][] newC = new boolean[multiplication.getZ().length][4];
        int delta = exponents[0] + exponents[1] - exponents[2];
        int correction = 2;

        IntStream.range(0, c.length).forEach(i -> newC[i + delta + correction] = c[i].clone());

        sum = new Sum(multiplication.getZ(), newC);

        boolean[][] abcResult = new boolean[sum.getZ().length - correction][4];
        IntStream.range(0, sum.getZ().length - correction).forEach(i -> abcResult[i] = sum.getZ()[i + correction].clone());

        int correctionStart = delta - exponents[3] > 0 ? 0 : abs(delta - exponents[3]);

        boolean[][] newD = new boolean[abcResult.length][4];
        IntStream.range(correctionStart, d.length).forEach(i -> newD[i + delta - exponents[3]] = d[i - correctionStart].clone());

        Division division = new Division(abcResult, newD);
        System.out.println();
        CorrectionPrint.printArray(division.getZ());
    }


    public boolean[][] getR() {
        return operation.getR();
    }

    public boolean[][] getH() {
        return operation.getH();
    }

    public boolean[][] getZ() {
        return operation.getZ();
    }
}
