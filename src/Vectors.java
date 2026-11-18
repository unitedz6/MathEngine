public class Vectors {
    public static String[][] help = {};
    private double[] data;

    public Vectors(double[] data){
        this.data = data.clone();
    }

    // setters
    public void setVector(double[] data){
        this.data = data.clone();
    }

    public void setElement(int index, double value){
        this.data[index] = value;
    }

    // getters
    public double[] getVector(){
        return this.data.clone();
    }

    public int getDimension(){
        return this.data.length;
    }

    public double getElement(int index){
        return this.data[index];
    }

    // functions
    public static Vectors returnAddedVectors(Vectors... vectors){
        double[] result = new double[vectors[0].data.length];
        for (int i = 0; i < vectors[0].data.length; i++){
            System.out.print("Row " + (i+1) + ": \n");
            for (int j = 0; j < vectors.length; j++){
                System.out.println("[" + vectors[j].data[i] + "]");

                result[i] += vectors[j].data[i];
            }
            System.out.println("=" + result[i]);
        }
        return new Vectors(result);
    }
}
