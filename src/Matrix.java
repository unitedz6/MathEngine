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




    public static boolean isCorrectDimension(Matrix m1, Matrix m2){
        if (m1.getCols() == m2.getRows()) {
            System.out.println("Correct Dimension");
            return true;
        } else {
            System.out.println("Incorrect Dimension");
            return false;
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
