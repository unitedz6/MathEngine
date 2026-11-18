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
}
