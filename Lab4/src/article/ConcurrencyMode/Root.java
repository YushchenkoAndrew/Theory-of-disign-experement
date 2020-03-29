package article.ConcurrencyMode;

import article.CorrectionPrint;

public class Root extends Operation {
    private boolean[][] r;
    private boolean[][] h;

    private BinaryOperation bin = new BinaryOperation();
    private ComputerCoding coding = new ComputerCoding();
    private Sum sum = new Sum();


    public Root(boolean[][] x, int p) {
        bin.setSizeY(x.length + p + 3);

        h = new boolean[x.length + p + 3][2 * p + x.length + 2];
        r = new boolean[x.length + p + 3][2 * p + x.length + 2];
        root(x, p);
    }

    private void root(boolean[][] x, int p) {
        for (int i = 0; i < x.length + p; i++) {
            if (i < x.length) {
                h[i] = sum.sum(r[i], x[i][2] || x[i][3] ? bin.createArrayContainOne(2 * p + 1 + (x[i][2] ? 0 : 1), false, h[i].length) : new boolean[h[i].length]);
            } else
                h[i] = r[i].clone();
            CorrectionPrint.printArray(getZ());
            System.out.println();
            correction(i);
        }
    }

    public void correction(int i) {
        bin.createIndexY(i, h);
        boolean[] y;

        if (bin.getY()[i][2])
            y = sum.sum(bin.createArrayContainOne(i + 1, true, h[0].length), bin.shiftL(coding.transfer(coding.transferBaseTwo(bin.createRightArray(bin.getOfRange(bin.getY(), 0, i), i + 1, i + 2)), true), 0));
        else if (bin.getY()[i][3])
            y = sum.sum(bin.createArrayContainOne(i + 3, true, i + 4), coding.transfer(coding.transferBaseTwo(bin.createRightArray(bin.getOfRange(bin.getY(), 0, i), i + 1, i + 2)), true));
        else
            y = new boolean[h[0].length];

        r[i + 1] = sum.sum(h[i], y);

        r[i + 1] = bin.shiftL(r[i + 1], 0);
    }


    public boolean[][] getR() {
        return r;
    }

    public boolean[][] getH() {
        return h;
    }
    public boolean[][] getZ() {
        return bin.getY();
    }
}
