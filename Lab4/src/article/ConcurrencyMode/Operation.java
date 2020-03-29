package article.ConcurrencyMode;

import article.ConcurrencyMode.CommonOperations;

public class Operation implements CommonOperations {

    @Override
    public boolean[][] getH() {
        return new boolean[0][];
    }

    @Override
    public boolean[][] getR() {
        return new boolean[0][];
    }

    @Override
    public boolean[][] getZ() {
        return new boolean[0][];
    }


    @Override
    public void correction(int i, boolean[][] d) {

    }

    @Override
    public void correction(int i) {

    }
}
