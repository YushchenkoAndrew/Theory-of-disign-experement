package article.ConcurrencyMode;

import java.util.Arrays;

public class Function1 extends Operation {
    private boolean[][] h;
    private boolean[][] r;
    private BinaryOperation bin = new BinaryOperation();
    private ComputerCoding coding = new ComputerCoding();
    private Sum sum = new Sum();

    public Function1(boolean[][] a, boolean[][] b, boolean[][] c, boolean[][] d, int p) {
        bin.setSizeZ(14);

        r = new boolean[15][p + b.length + 2];
        h = new boolean[14][p + b.length + 2];

        function(a, b, c, d, p);
    }

    private void function(boolean[][] a, boolean[][] b, boolean[][] c, boolean[][] d, int p) {
        for (int i = 0; i < 14; i++) {
            if (i < a.length) {
                boolean isASum = bin.isSum(a, i);
                boolean isADeduct = bin.isDeduct(a, i);
                boolean isBSum = bin.isSum(b, i);
                boolean isBDeduct = bin.isDeduct(b, i);
                boolean isCDeduct = bin.isDeduct(c, i);
                boolean isDSum = bin.isSum(d, i);
                boolean isDDeduct = bin.isDeduct(d, i);


                h[i] = sum.sum(r[i], ((isASum || isADeduct) && (isBSum || isBDeduct)) ? bin.createArrayContainOne(i + p + 2, isADeduct ^ isBDeduct, h[0].length) : new boolean[h[0].length]);

                h[i] = sum.sum(h[i], bin.isSum(c, i) || isCDeduct ? bin.createArrayContainOne(p + 1, isCDeduct, h[i].length) : new boolean[h[0].length]);

                h[i] = sum.sum(h[i], coding.transfer(bin.createRightArray(isBSum || isBDeduct ? a : new boolean[a.length][a[0].length], i + p, i), isBDeduct));
                h[i] = sum.sum(h[i], coding.transfer(bin.createRightArray(isASum || isADeduct ? b : new boolean[b.length][b[0].length], i + p, i), isADeduct));

                h[i] = sum.sum(h[i], Arrays.copyOfRange(coding.transfer(isDSum || isDDeduct ? getZ() : new boolean[d.length][d[0].length + 2], !isDDeduct),
                        0, d.length + 1));

            } else
                h[i] = r[i].clone();

            correction(i, d);
        }

    }

    public void correction(int i, boolean[][] d) {
        bin.createIndexZ(i, h);
        boolean isZSum = (bin.getZ()[i][2] || bin.getZ()[i][3]) && (!bin.getZ()[i][2] || !bin.getZ()[i][3]);
        boolean isZSumDeduct = bin.getZ()[i][2];

        r[i + 1] = sum.sum(h[i], Arrays.copyOfRange(coding.transfer(bin.createRightArray(isZSum || isZSumDeduct ? d : new boolean[d.length][d[0].length], i + 1,
                i + 2), (isZSum || isZSumDeduct) && !isZSumDeduct), 0, h[0].length));

        r[i + 1][0] = r[i + 1][2];
        r[i + 1][1] = r[i + 1][2];
        r[i + 1] = bin.shiftL(r[i + 1], 2);
    }

    public boolean[][] getH() {
        return h;
    }

    public boolean[][] getR() {
        return r;
    }

    public boolean[][] getZ() {
        return bin.getZ();
    }
}
