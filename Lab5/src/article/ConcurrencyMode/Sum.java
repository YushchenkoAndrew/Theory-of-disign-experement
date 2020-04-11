package article.ConcurrencyMode;

public class Sum extends Operation {
    private boolean[][] r;
    private boolean[][] h;

    private BinaryOperation bin = new BinaryOperation();

    public Sum(){

    }

    public Sum(boolean[][] x, boolean[][] y){
        bin.setSizeZ(x.length + 2);
        r = new boolean[x.length + 3][4];
        h = new boolean[x.length + 2][4];
        sum(x, y);
    }

    private void sum(boolean[][] x, boolean[][] y){
        for (int i = 1; i <= x.length + 2; i++){
            if (i <= x.length) {
                boolean[] store = r[i - 1].clone();
                for (int j = 3; j >= 0; j--) {
                    r[i][j] = bin.sumBin(x[i - 1][j], y[i - 1][j], store[j]);
                    int k = j == 0 & bin.isP() ? 0 : 1;
                    while (bin.isP() && j - k >= 0) {
                        store[j - k] = bin.sumBin(bin.isP(), store[j - k], false);
                        k++;
                    }
                }
            }
            else
                r[i] = r[i - 1].clone();
            correction(i);
        }
    }

    public void correction(int i){
        bin.createIndexZ(i, i - 1, r);
        h[i - 1] = r[i].clone();
        r[i][0] = r[i][2];
        r[i][1] = r[i][2];
        r[i][2] = r[i][3];
        r[i][3] = false;
    }



    public boolean[] sum(boolean[] a, boolean[] b){
        boolean[] x = a.length > b.length ? a : b;
        boolean[] y = a.length > b.length ? b : a;

        boolean[] z = new boolean[x.length];

        for (int j = x.length - 1; j >= 0; j--) {
            if (j < y.length) {
                z[j] = bin.sumBin(x[j], y[j], z[j]);
                int k = 1;
                while (bin.isP() && j - k >= 0) {
                    z[j - k] = bin.sumBin(bin.isP(), z[j - k], false);
                    k++;
                }
            } else
                z[j] = x[j];
        }

        return z;
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
