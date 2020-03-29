package article.ConcurrencyMode;

public class Multiplication extends Operation {
    private boolean[][] r;
    private boolean[][] h;

    private BinaryOperation bin = new BinaryOperation();
    private ComputerCoding coding = new ComputerCoding();
    private Sum sum = new Sum();


    public Multiplication(boolean[][] x, boolean[][] y) {
        bin.setSizeZ(x.length * 2 + 2);

        int p = 4;

        h = new boolean[x.length * 2 + 2][p + x.length];
        r = new boolean[x.length * 2 + 3][p + x.length];
        multiplication(x, y);
    }

    private void multiplication(boolean[][] x, boolean[][] y) {
        for (int i = 0; i < x.length * 2 + 2; i++) {
            if (i < x.length) {
                boolean isXSum = bin.isSum(x, i);
                boolean isXDeduct = bin.isDeduct(x, i);
                boolean isYSum = bin.isSum(y, i);
                boolean isYDeduct = bin.isDeduct(y, i);

                boolean[] store = r[i].clone();
                h[i] = sum.sum(store, coding.transfer(bin.createRightArray(isYSum || isYDeduct ? x : new boolean[x.length][x[0].length], i + 2, i), isYDeduct));
                h[i] = sum.sum(h[i], coding.transfer(bin.createRightArray(isXSum || isXDeduct ? y : new boolean[y.length][y[0].length], i + 2, i), isXDeduct));


                if ((isXSum || isXDeduct) && (isYSum || isYDeduct))
                    h[i] = sum.sum(h[i], bin.createArrayContainOne(i + 4, isXDeduct ^ isYDeduct, h[i].length));       //flag for deduct

            } else
                h[i] = r[i].clone();
            correction(i);
        }
    }


    public void correction(int i) {
        bin.createIndexZ(i, i, h);
        r[i + 1] = h[i].clone();
        r[i + 1][0] = r[i + 1][2];
        r[i + 1][1] = r[i + 1][2];
        r[i + 1] = bin.shiftL(r[i + 1], 2);

    }

    public boolean[][] getR() {
        return r;
    }

    public boolean[][] getH() {
        return h;
    }

    public boolean[][] getZ() {
        return bin.getZ();
    }
}
