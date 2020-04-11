package article.ConcurrencyMode;

public interface CommonOperations {

    boolean[][] getH();

    boolean[][] getR();

    boolean[][] getZ();

    void correction(int i, boolean[][] d);

    void correction(int i);

}
