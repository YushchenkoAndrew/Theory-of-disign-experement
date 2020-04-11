package article.ConcurrencyMode;

public class PythagorasFunction extends Operation {
    private boolean[][] h;
    private boolean[][] r;
    private BinaryOperation bin = new BinaryOperation();
    private ComputerCoding coding = new ComputerCoding();
    private Sum sum = new Sum();

    public PythagorasFunction(boolean[][] x, boolean[][] y, int p) {
        bin.setSizeY(2 * x.length + p);

        h = new boolean[2 * x.length + p][2 * p + 2 * x.length];
        r = new boolean[2 * x.length + p][2 * p + 2 * x.length];
        pythagorasFunction(x, y, p);
    }

    private void pythagorasFunction(boolean[][] x, boolean[][] y, int p) {
        for (int i = 0; i < getZ().length - 1; i++) {
            if (i < x.length) {
                h[i] = sum.sum(r[i], x[i][2] || x[i][3] ? coding.transferBaseTwo(bin.createRightArray(x , i + 2 * p - (x[i][2] ? 1 : 0), i)) : new boolean[h[i].length]);
                h[i] = sum.sum(h[i], y[i][2] || y[i][3] ? coding.transferBaseTwo(bin.createRightArray(y , i + 2 * p - (y[i][2] ? 1 : 0), i)) : new boolean[h[i].length]);

                h[i] = sum.sum(h[i], (x[i][2] || x[i][3]) ? bin.createArrayContainOne(i + 2 * p + 1 + (x[i][2] ? 0 : 2), false, h[0].length) : new boolean[h[0].length]);
                h[i] = sum.sum(h[i], (y[i][2] || y[i][3]) ? bin.createArrayContainOne(i + 2 * p + 1 + (y[i][2] ? 0 : 2), false, h[0].length) : new boolean[h[0].length]);
            } else
                h[i] = r[i].clone();
            correction(i);
        }
    }

    public void correction(int i) {
        bin.createIndexY(i, h);
        boolean[] z;
        if (bin.getY()[i][2])
            z = sum.sum(bin.createArrayContainOne(i + 1, true, h[0].length), bin.shiftL(coding.transfer(coding.transferBaseTwo(bin.createRightArray(bin.getOfRange(bin.getY(), 0, i), i + 1, i + 2)), true), 0));
        else if (bin.getY()[i][3])
            z = sum.sum(bin.createArrayContainOne(i + 3, true, i + 4), coding.transfer(coding.transferBaseTwo(bin.createRightArray(bin.getOfRange(bin.getY(), 0, i), i + 1, i + 2)), true));
        else
            z = new boolean[h[0].length];

        r[i + 1] = sum.sum(h[i], z);

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
