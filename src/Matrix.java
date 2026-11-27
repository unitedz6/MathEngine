public class Matrix {

    public static String[][] help = {};

    private double[][] data;
    private int rows;
    private int cols;

    public Matrix(double[][] data) {
        this.data = data;
        this.rows = data.length;
        this.cols = data[0].length;
    }

    public static Matrix rotationMatrix(double theta) {
        /*
            R(THETA) = | cos(THETA), -sin(THETA) |
                       | sin(THETA), cos(THETA)  |
         */

        return new Matrix(new double[][]{
                {MathematicalOperations.cos(theta), -MathematicalOperations.sin(theta)},
                {MathematicalOperations.sin(theta), MathematicalOperations.cos(theta)}
        });
    }


    // scalar multiplication


    public static boolean isCorrectDimension(Matrix m1, Matrix m2) {
        if (m1.getCols() == m2.getRows()) {
            System.out.println("Correct Dimension");
            return true;
        } else {
            System.out.println("Incorrect Dimension");
            return false;
        }
    }

    public static boolean isCorrectDimension(Matrix m) {
        return m.getRows() == m.getCols();
    }

    public static Matrix createSubMatrix(Matrix m){
        int bestColumn = Statistics.returnMaxFrequencyIndex(m, 0);

        System.out.println("The best column to delete is: " + bestColumn);
        return m;
    }


    public static Matrix getMinor(Matrix m, int row, int col){
        // remove row, col 0

        double[][] minor = new double[m.getRows()-1][m.getCols()-1];

        int minorRow = 0;

        for (int i = 0; i < m.getRows(); i++){
            if (i == row){
                continue;
            }
            int minorCol = 0;

            for (int j = 0; j < m.getCols(); j++){
                if (j == col){
                    continue;
                }
                minor[minorRow][minorCol] = m.getElement(i, j);

                minorCol++;
            }
            minorRow++;
        }
        return new Matrix(minor);
    }

    public static double calculateDeterminant(Matrix m) {
        int n = m.getRows();
        if (!isCorrectDimension(m)) {
            throw new IllegalArgumentException("Matrix must be square.");
        } else {
            // 1x1
            if (n == 1){
                return m.getElement(0, 0);
                // 2x2
            } else if (n == 2) {
                return
                        (m.getElement(0, 0) * m.getElement(1, 1)) - (m.getElement(0, 1) * m.getElement(1, 0));
                // cofactor expansion
            } else {
                double determinantSum = 0;
                 for (int j = 0; j < n; j++){
                     determinantSum += MathematicalOperations.exponent(-1, j)
                             * m.getElement(0, j)
                             * calculateDeterminant(getMinor(m, 0, j));
                 }
                return determinantSum;
            }
        }
    }

    public static Matrix matrixMultiplication(Matrix m1, Matrix m2){
        double[][] result = new double[m1.getRows()][m2.getCols()];
        if (isCorrectDimension(m1, m2)){
            for (int i = 0; i < m1.getRows(); i++){
                for (int j = 0; j < m2.getCols(); j++){
                    for (int k = 0; k < m1.getCols(); k++){
                        result[i][j] += m1.getElement(i,k) * m2.getElement(k, j);
                    };
                }
            }
        } else {
            throw new IllegalArgumentException("Cannot matrix multiply as dimensions are not the same");
        }
        return new Matrix(result);
    }

    /*
    public static Matrix convertVectorsToMatrix(int partitionAmount, boolean partitionType, Vectors... vectors) {
        // partitionAmount = every k partionType split vectors into according matrix, partitionType = true or false. True=Column, False=Row
        // i.e., k = 2, partitionType = column, count every collection of vector in 2 and separate into matrices

        int totalVectorColumns;
        int totalVectorRows;

        // throw them into a matrix originally



        if (partitionType) {

        } else if (!partitionType) {

        } else {
            throw new IllegalArgumentException("A partition type consists of either a row or column, which is represented as TRUE -> column, FALSE -> row");
        }
    }
    */

    /*
    public static Matrix[][] matrixInverse(){

    }W
     */

    //setters
    public void setMatrix(double[][] data){
        this.data = data;
    }

    public void setRow(int row, double[] values) {
        this.data[row] = values;
    }

    public void setCols(int col, double[] values){
        for (int i = 0; i < data.length; i++) {
            this.data[i][col] = values[i];
        }
    }

    public void setElement(int row, int col, double values){
        this.data[row][col] = values;
    }

    // getters
    public int getRows(){
        return data.length;
    }

    public int getCols(){
        return data[0].length;
    }

    public double getElement(int row, int col){
        return data[row][col];
    }

    public double[] getColumn(int col){
        double[] column = new double[getRows()];
        for (int i = 0; i < getRows(); i++){
                column[i] = getElement(i, col);
        }
        return column;
    }

    public static String toString(Matrix m){
        String result = "";

        for (int i = 0; i < m.getRows(); i++){
            for (int j = 0; j < m.getCols(); j++){
                result += m.getElement(i, j) + " ";

            }
            if (i < m.getRows()-1) {
                result += "\n";
            }
        }
        return result;
    }

}
