package article.ConcurrencyMode;

import java.util.Arrays;

public class ComputerCoding {
    private Sum sum = new Sum();

    private boolean[] z;
    private boolean[] zDeduct;
    private boolean[] correction;

    public ComputerCoding(){

    }

    public ComputerCoding(boolean[][] x, boolean flag){
        transfer(x, flag);
    }

    public ComputerCoding(boolean[][] x, boolean flag, int concurrencyElement){
        switch (concurrencyElement){
            case -1: {
                transfer(x, flag);
                break;
            }
            case 2: {
                transferBaseTwo(x);
                break;
            }
        }
    }

    public boolean[] transfer(boolean[][] x, boolean flag){     //if flag == 0 --> for DK system else for deduct
        transfer(x, x.length + 2, flag);

        correction = new boolean[x.length + 2];
        for (int i = 0; i < x.length + 2; i++) {
            correction[i] = false;
        }
        correction[x.length + 1] = true;

        return getResult();
    }

    public boolean[] transfer(boolean[] x, boolean flag){     //if flag == 0 --> for DK system else for deduct
        boolean[] correction = new boolean[x.length];
        if (flag) {
            for (int i = 0; i < x.length; i++) {
                x[i] = !x[i];
            }
            correction[x.length - 1] = true;
        }


        return sum.sum(x, correction);
    }


    public boolean[] getResult(){
        return sum.sum(z, sum.sum(zDeduct, correction));
    }

    private void transfer(boolean[][] x, int size, boolean flag){
        z = new boolean[x.length + 2];
        zDeduct = new boolean[size];
        for (int i = 0; i < 2; i++) {
            z[i] = flag;
            zDeduct[i] = !flag;
        }
        for (int i = 2; i < size; i++) {
            zDeduct[i] = flag == x[i - 2][2];
            boolean store = (x[i - 2][2] || x[i - 2][3]) && (!x[i - 2][2] || !x[i - 2][3]);
            z[i] = flag != store;
        }
    }

    public boolean[] transferBaseTwo(boolean[][] x){
        z = new boolean[x.length + 2];
        zDeduct = new boolean[x.length + 2];        // not use need only for not change method getResult
        correction = new boolean[x.length + 2];
        for (int i = 0; i < x.length; i++) {
            if (x[i][2])
                correction[i + 1] = true;
            if (x[i][3])
                z[i + 2] = true;
        }
        return getResult();
    }

    public boolean[][] createIndexZ(int i, int j, boolean[][] h, boolean[][] z) {
        if ((h[i][0] && h[i][1] && h[i][2]) || (!h[i][0] && !h[i][1] && !h[i][2]))
            z[j] = new boolean[]{false, false, false, false};
        if ((!h[i][0] && h[i][1] && !h[i][2]) || (!h[i][0] && !h[i][1] && h[i][2]))
            z[j] = new boolean[]{false, false, false, true};
        if ((h[i][0] && !h[i][1] && h[i][2]) || (h[i][0] && h[i][1] && !h[i][2]))
            z[j] = new boolean[]{true, true, true, true};
        return z;
    }

    public boolean[][] createIndexZ(int i, boolean[][] h, boolean[][] z) {         // <-- for division
        if ((h[i][0] && h[i][1] && h[i][2]) || (!h[i][0] && !h[i][1] && !h[i][2]))
            z[i] = new boolean[]{false, false, false, false};
        if ((!h[i][0] && h[i][1] && !h[i][2]) || (!h[i][0] && !h[i][1] && h[i][2]) || (!h[i][0] && !h[i][1] && !h[i][2] && h[i][3]))
            z[i] = new boolean[]{false, false, false, true};
        if ((h[i][0] && !h[i][1] && h[i][2]) || (h[i][0] && h[i][1] && !h[i][2]) || (h[i][0] && h[i][1] && h[i][2] && !h[i][3]))
            z[i] = new boolean[]{true, true, true, true};
        return z;
    }

    public boolean[][] createIndexY(int i, boolean[][] h, boolean[][] y) {         // <-- (redundant system) finding root
        BinaryOperation bin = new BinaryOperation();
        boolean[][] downEdge = new boolean[y.length][y[0].length];
        downEdge[i + 1][3] = true;      //recording lik this --> {0, 0, 0, 1}    //recording lik this --> {0, 0, 0, 1}
        Sum sum = new Sum(y, downEdge);
        ComputerCoding codingDownEdge = new ComputerCoding(sum.getZ(), false);

        if (bin.compare(h[i], Arrays.copyOfRange(codingDownEdge.getResult(), 2, codingDownEdge.getResult().length))) {
            boolean[][] upEdge = new boolean[y.length][y[0].length];
            upEdge[i][3] = true;

            sum = new Sum(y, upEdge);
            ComputerCoding codingUpEdge = new ComputerCoding(sum.getZ(), false);

            y[i] = bin.compare(bin.shiftR(h[i].clone(), 1), Arrays.copyOfRange(codingUpEdge.getResult(), 2, codingUpEdge.getResult().length)) ? new boolean[]{false, false, true, false} : new boolean[]{false, false, false, true};
        }
        else y[i] = new boolean[]{false, false, false, false};       // 0 <= H < ...
        return y;
    }

}
