package article.ConcurrencyMode;

import java.util.Arrays;

public class Division extends Operation {
    private boolean[][] r;
    private boolean[][] h;
    private Sum sum = new Sum();
    private ComputerCoding coding = new ComputerCoding();
    private BinaryOperation bin = new BinaryOperation();

    public Division(boolean[][] x, boolean[][] y){
        bin.setSizeZ(x.length * 2);

        r = new boolean[x.length * 2 + 1][9];
        h = new boolean[x.length * 2][9];


        division(x, y);
    }

    private void division(boolean[][] x, boolean[][] y){
        for (int i = 0; i < x.length * 2; i++){
            if (i < x.length){
                boolean isXSum = bin.isSum(x, i);
                boolean isXDeduct = bin.isDeduct(x, i);
                boolean isYSum = bin.isSum(y, i);
                boolean isYDeduct = bin.isDeduct(y, i);

                h[i] = sum.sum(r[i], isXSum || isXDeduct ? bin.createArrayContainOne(4, isXDeduct, h[i].length) : new boolean[h[0].length]);

                h[i] = sum.sum(h[i], Arrays.copyOfRange(coding.transfer(isYSum || isYDeduct ? getZ() : new boolean[x.length][x[0].length + 2], !isYDeduct),
                        0, x.length + 1));
            }
            else
                h[i] = r[i];
            correction(i, y);
        }
    }

    public void correction(int i, boolean[][] y){
        bin.createIndexZ(i, h);
        boolean isZSum = (bin.getZ()[i][2] || bin.getZ()[i][3]) && (!bin.getZ()[i][2] || !bin.getZ()[i][3]);
        boolean isZSumDeduct = bin.getZ()[i][2];

        r[i + 1] = sum.sum(h[i], Arrays.copyOfRange(coding.transfer(bin.createRightArray(isZSum || isZSumDeduct ? y : new boolean[y.length][y[0].length], i + 1,
                i + 2), (isZSum || isZSumDeduct) && !isZSumDeduct),0, h[0].length));

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

    public boolean[][] getZ(){
        return bin.getZ();
    }
}
