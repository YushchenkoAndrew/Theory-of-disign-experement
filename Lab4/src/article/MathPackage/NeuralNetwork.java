package article.MathPackage;

import article.CorrectionPrint;
import article.FilesReaderWriter;

public class NeuralNetwork extends FilesReaderWriter {
    //I know that create 3d Arrays is bad, but it's seem so sexy (useful in my case)
    private double[][][] weight;
    private double[][][] input;
    private double[][][] output;
    public double[][][] wantedResult;
    private double[][][] err;
    private Matrix matrix = new Matrix();
    private final double valueOfLearning = 0.1;
    private final double bias = 0;
    private int numOfTest = 0;
    private long cycleOfLearning = 0;
    private double totalError = 0;

    public NeuralNetwork() {
    }

    /**
     * This constructor while creating can set size of arrays
     * @param numOfLayers       It's number of layers
     * @param numOfDendrites    This is array (array length == (numOfLayers - 1)) contain a number of Dendrites in exact Layer
     */
    public NeuralNetwork(int numOfLayers, int[] numOfDendrites) {
        setNumOfLayers(numOfLayers);
        setNumOfDendrites(numOfDendrites);
    }

    /**
     * I have never used this method and method below. Future me say something about it, Ok?
     * @param numOfLayers
     */
    public void setNumOfLayers(int numOfLayers) {
        output = new double[numOfLayers][][];
        weight = new double[numOfLayers][][];
        err = new double[numOfLayers][][];
    }

    public void setNumOfDendrites(int[] numOfDendrites) {
        for (int i = 0; i < numOfDendrites.length; i++) {
            output[i] = new double[numOfDendrites[i]][numOfDendrites[i]];
            weight[i] = new double[numOfDendrites[i]][numOfDendrites[i]];
            err[i] = new double[numOfDendrites[i]][numOfDendrites[i]];
        }
        input[numOfDendrites.length] = new double[numOfDendrites[numOfDendrites.length - 1]][numOfDendrites[numOfDendrites.length - 1]];
    }

    /**
     * Set grid it's mean set a number of layers and array of Dendrites
     * @param numOfLayers       It's number of layers
     * @param numOfDendrites    This is array (array length == (numOfLayers - 1)) contain a number of Dendrites in exact Layer
     */
    public void setGrid(int numOfLayers, int[] numOfDendrites) {
        setNumOfLayers(numOfLayers);
        setNumOfDendrites(numOfDendrites);
    }

    /**
     * Parhapse I created this method for simplify method below, but I don't for sure.
     * Ok, this method realized a more smaller array from already given
     * @param row   Number of row what we want
     * @param col   Number of columns what we want
     * @param data  Yep, it's right it's our array that we want to compressed
     * @return  Return compressed array
     */
    private double[][] makeCorrectedSizeArray(int row, int col, double[][] data) {
        double[][] store = new double[row][col];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                store[i][j] = data[i][j];
        return store;
    }

    /**
     * This method need for correct load weights from file. It's read in already setting rules by congress.
     * Method read file in concreted paradigm.
     * @param numOfLayers   it's simple question, it's number of layout
     */
    private double[][][] setParameters(int numOfLayers, int numOfLenghtColumns, int numOfWeightColumns) {
        int k = -1;
        int m = 0;
        int storeNumOfColumns = 0;
        double[][][] fileValue = new double[numOfLayers][numOfLenghtColumns][numOfWeightColumns];
        for (int i = 1; i < fileLines.size(); i++) {
            if (fileLines.get(i).charAt(0) == '#') {
                k++;
                if (k != 0)
                    fileValue[k - 1] = makeCorrectedSizeArray(m, storeNumOfColumns, fileValue[k - 1]);
                m = 0;
                continue;
            }
            if (fileLines.get(i).charAt(0) == '*')
                break;
            int n = 0;
            StringBuilder data = new StringBuilder();
            for (int j = 0; j < fileLines.get(i).length(); j++) {
                if ((fileLines.get(i).charAt(j) ==  ' ') || (fileLines.get(i).charAt(j) ==  ';') || (fileLines.get(i).charAt(j) ==  '\t')) {
                    if (data.length() != 0) {
                        fileValue[k][m][n++] = Double.parseDouble(data.toString());
                    }
                    data = new StringBuilder();
                    if (fileLines.get(i).charAt(j) ==  ';') {
                        m++;
                        storeNumOfColumns = n;
                        n = 0;
                    }
                    continue;
                }
                data.append(fileLines.get(i).charAt(j));
            }
        }
        fileValue[k] = makeCorrectedSizeArray(m, storeNumOfColumns, fileValue[k]);
        return fileValue;
    }

    /**
     * This method use for finding size of arrays in data files
     * @return  Return amount of elements for arrays
     */
    private int[] loadFile() {
        super.readFile();

        StringBuilder[] numOfNeuralNet = new StringBuilder[4];
        int index = -1;
        for (int i = 0; i < fileLines.get(0).length(); i++) {
            if (fileLines.get(0).charAt(i) == '$') {
                index++;
                numOfNeuralNet[index] = new StringBuilder();
                continue;
            }
            numOfNeuralNet[index].append(fileLines.get(0).charAt(i));
        }
        int[] data = new int[numOfNeuralNet.length];
        for (int i = 0; i < numOfNeuralNet.length; i++) {
            if (numOfNeuralNet[i].length() == 0)
                break;
            data[i] = Integer.parseInt(numOfNeuralNet[i].toString());
        }
        return data;
    }

    /**
     * This method important in the reason of multiplication matrix and other math operation.
     * It's make the same size like weight array, which has already written from the file.
     */
    private void makeCorrectedSizeArray() {
        for (int i = 0; i < weight.length; i++) {
            err[i] = new double[weight[i].length][1];
            output[i] = new double[weight[i].length][1];
        }
    }

    /**
     * This method exists just for in a moment have a class and loaded data from file
     * @param path  Path to file that U want to read
     */
    public NeuralNetwork(String path) {
        super(path);
        int[] numOfNeuralNet = loadFile();
        weight = setParameters(numOfNeuralNet[0], numOfNeuralNet[1], numOfNeuralNet[2]);
        err = new double[numOfNeuralNet[0]][][];
        output = new double[numOfNeuralNet[0]][][];
        makeCorrectedSizeArray();
    }

    @Override
    public void setReadFilePath(String path) {
        super.setReadFilePath(path);
        int[] numOfNeuralNet = loadFile();
        weight = setParameters(numOfNeuralNet[0], numOfNeuralNet[1], numOfNeuralNet[2]);
        err = new double[numOfNeuralNet[0]][][];
        output = new double[numOfNeuralNet[0]][][];
        makeCorrectedSizeArray();
    }

    @Override
    public void setWriteFilePath(String path) {
        super.setWriteFilePath(path);
    }

    private void printDivider(String symbol) {
        StringBuilder string = new StringBuilder();
        string.append(String.valueOf(symbol).repeat(Math.max(0, weight[0][0].length * 20)));
        super.printIntoTheFileLineByLine(string);
    }

    /**
     * Yep. I know that is messy method and it will be perhaps slowly saved data but
     * IT'S SAVING, Who cares how long does it take?
     * Maybe... In the future I'll change it in more likable/pleasure format but how knows?
     * For Me in the past time is important when you saving weight while your neural network is learning
     */
    private void saveDataOfNeuralNetwork () {
        super.printIntoTheFileLineByLine(new StringBuilder("$%d"), weight.length);
        super.printIntoTheFileLineByLine(new StringBuilder("$%d"), weight[0].length);
        super.printIntoTheFileLineByLine(new StringBuilder("$%d$\n"), weight[0][0].length);
        for (int i = 0; i < weight.length; i++) {
            printDivider("#");
            for (int j = 0; j < weight[i].length; j++) {
                for (int k = 0; k < weight[i][j].length - 1; k++)
                    super.printIntoTheFileLineByLine(new StringBuilder("%.20f\t"), weight[i][j][k]);
                super.printIntoTheFileLineByLine(new StringBuilder("%.20f;\n"), weight[i][j][weight[i][j].length - 1]);
            }
        }
        printDivider("*");
        super.closeOpenFile();
    }

    public void saveData () {
        saveDataOfNeuralNetwork();
    }

    /**
     * In this case this method need for saving our progress. It's like in video games where U need to
     * save your achievement
     * @param path  It's very simple, I think U can get it for what this parameter existed
     */
    public void saveData (String path) {
        super.setWriteFilePath(path);
        saveDataOfNeuralNetwork();
    }

    /**
     * In this case this method need for loading file of input data
     * @param path  It's very simple, I think U can get it for what this parameter existed
     */
    public void setInputFilePath(String path) {
        super.setReadFilePath(path);
        int[] numOfNeuralNet = loadFile();
        input = new double[numOfNeuralNet[0]][numOfNeuralNet[1]][1];
        input = setParameters(numOfNeuralNet[0], numOfNeuralNet[1], 1);
    }

    /**
     * In this case this method need for loading file of output data. It's important data for machine leaning
     * without it "Computer" cannot understand corrected his output data or not. In more simple way we put the face
     * of "Computer" in his mistake.
     * @param path  It's very simple, I think U can get it for what this parameter existed
     */
    public void setCorrectResultFilePath(String path) {
        super.setReadFilePath(path);
        int[] numOfNeuralNet = loadFile();
        wantedResult = new double[numOfNeuralNet[0]][numOfNeuralNet[1]][1];
        wantedResult = setParameters(numOfNeuralNet[0], numOfNeuralNet[1], 1);
    }

    /**
     * In my case, I use function of activation sigmoid, later I will change it, perhaps ).
     * @param x     Argument
     * @return      Return value from function
     */
    private double function(double x) {
        return 1 / (1 + Math.pow(Math.E, -x));
    }


//    private double function(double x) {
//        return (x <= 0 ? 0.001 * x : x > 1 ? 1 + 0.001 * x : x);
//    }
    /**
     * For this method and for methods below their title said enough
     */
    private void calculateOutputResult () {
        for (int j = 0; j < output.length; j++) {
            double[][] store = matrix.multiplication(weight[j], j == 0 ? input[numOfTest] : output[j - 1]);
            for (int i = 0; i < store.length; i++)
                output[j][i][0] = function(store[i][0] + bias);
        }
    }

    private void correctingWeight (int index, int k) {
        for (int i = 0; i < weight[index][k].length; i++) {
            double sum = 0;
            for (int j = 0; j < weight[index][k].length; j++) {
                sum += weight[index][k][j] * (index - 1 >= 0 ? output[index - 1][j][0] : input[numOfTest][j][0]);
            }
            double function = function(sum);
            weight[index][k][i] += this.valueOfLearning * err[index][k][0] * function * (1 - function) * (index - 1 >= 0 ? output[index - 1][i][0] : input[numOfTest][i][0]);
//            weight[index][k][i] += this.valueOfLearning * err[index][k][0] * (sum > 0 ? sum > 1 ? 0.001 : 1 : 0.001) * (index - 1 >= 0 ? output[index - 1][i][0] : input[numOfTest][i][0]);
        }
    }

    private void calculateErrorState () {
        for (int i = 0; i < output[output.length - 1].length; i++) {
            err[err.length - 1][i][0] = wantedResult[numOfTest][i][0] - output[output.length - 1][i][0];
        }

        for (int i = err.length - 2; i >= 0; i--) {
            err[i] = matrix.multiplication(matrix.transposition(weight[i + 1]), err[i + 1]);
        }
    }

    public double totalError() {
        for (double[] doubles : err[err.length - 1])
//            for (double[] aDouble : doubles)
//                totalError += aDouble[0] * aDouble[0];
            totalError += doubles[0] * doubles[0];
        return totalError;
    }

    /**
     * Okay okay I answer what are doing this method, and it's very simple you sure you can't get it, seriously?
     * It's just summarised process of learning in himself he has calculate Output Result, calculate Error...
     */
    private void calculateNeuralNet () {
        calculateOutputResult();

        calculateErrorState();

        for (int j = weight.length - 1; j >= 0; j--)
            for (int i = 0; i < weight[j].length; i++)
                correctingWeight(j, i);

    }


    //todo need to think how to improve this method
    public void machineLearning() {
//        while (true) {
            calculateNeuralNet();
        if (numOfTest == 0)
            totalError = 0;
//            if (i % 1000 == 0)
//                saveData("src\\article\\MathPackage\\NeuralNetworkFiles\\SaveWeights.dat");
//            if(Math.abs(err[err.length - 1][0][0]) + Math.abs(err[err.length - 1][1][0]) + Math.abs(err[err.length - 1][2][0]) < 0.05) {
                numOfTest = (int) (cycleOfLearning % wantedResult.length);
                totalError();
//            System.out.println(totalError() + "   -   " + i);
                cycleOfLearning++;
//                if (totalError() < 0.0000000001)
//                    break;
//                System.out.println(i);
//            }
//        }
    }

    /**
     * Need for getting result from already learned neural network
     * @param numOfTest     This is number of test which you want to take result
     * @return      I think you can get it, just try and you understand
     */
    public double[][] getResult(int numOfTest) {
        this.numOfTest = numOfTest;
        calculateOutputResult();
        return output[output.length - 1];
    }

    public int getNumOfTest() {
        return numOfTest;
    }

    public double[][] getWantedResult(int numOfTest) {
        return wantedResult[numOfTest];
    }

    public long getCycleOfLearning() {
        return cycleOfLearning;
    }

    public int getSizeOfTest() {
        return wantedResult.length;
    }

    public void setCycleOfLearning(long cycleOfLearning) {
        this.cycleOfLearning = cycleOfLearning;
    }


}
