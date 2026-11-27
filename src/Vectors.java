import java.util.Vector;

public class Vectors {

            public static String[][] help = {
                    {"createVector",
                            "Creates a vector using the specified elements and orientation. Defaults to a column vector.",
                            "crearVector",
                            "Crea un vector con los elementos y la orientación especificados. Por defecto, crea un vector columna."},

                    {"getDimension",
                            "Returns the dimension of a vector based on its number of elements.",
                            "obtenerDimension",
                            "Devuelve la dimensión de un vector según su número de elementos."},

                    {"getElement",
                            "Returns the value of a vector element at the specified index.",
                            "obtenerElemento",
                            "Devuelve el valor de un elemento del vector en el índice especificado."},

                    {"getOrientation",
                            "Returns whether a vector is oriented as a row or column.",
                            "obtenerOrientacion",
                            "Devuelve si un vector está orientado como fila o columna."},

                    {"addVectors",
                            "Adds two or more vectors component-wise and returns their sum.",
                            "sumarVectores",
                            "Suma dos o más vectores componente a componente y devuelve su suma."},

                    {"subtractVectors",
                            "Subtracts subsequent vectors from the first vector component-wise.",
                            "restarVectores",
                            "Resta los vectores posteriores del primer vector componente a componente."},

                    {"scalarMultiply",
                            "Multiplies one or more vectors by scalar values and returns the resulting vectors.",
                            "multiplicarPorEscalar",
                            "Multiplica uno o más vectores por valores escalares y devuelve los vectores resultantes."},

                    {"transposeVector",
                            "Transposes one or more vectors by switching their orientation between row and column.",
                            "transponerVector",
                            "Transpone uno o más vectores cambiando su orientación entre fila y columna."},

                    {"toStringVector",
                            "Converts one or more vectors into formatted strings according to their orientation.",
                            "convertirVectorATexto",
                            "Convierte uno o más vectores en cadenas de texto con formato según su orientación."}

            };


    public enum Orientation {
        ROW,
        COLUMN
    }

    private double[] data;
    private Orientation orientation;

    public Vectors(double[] values) {
        this(values, Orientation.COLUMN);
    }

    public Vectors(double[] data, Orientation orientation) {
        this.data = data.clone();
        this.orientation = orientation;
    }


    // setters
    public void setVector(double[] data) {
        this.data = data.clone();
    }

    public void setElement(int index, double value) {
        this.data[index] = value;
    }

    public void setOrientation(Orientation orientation){
        this.orientation = orientation;
    }

    // getters
    public double[] getVector() {
        return this.data.clone();
    }

    public double getVectorX(){
        return data[0];
    }

    public double getVectorY(){
        return data[1];
    }

    public static double getVectorLength(Vectors vector) {
        double summation = 0;

        for (int m = 0; m < vector.data.length; m++){
            summation += MathematicalOperations.exponent(vector.data[m], 2);
        }
        return MathematicalOperations.squareRoot(summation);
    }

    public int getDimension() {
        return this.data.length;
    }

    public double getElement(int index) {
        return this.data[index];
    }

    public Orientation getOrientation(){
        return this.orientation;
    }

    // functions
    public static Vectors addVectors(Vectors... vectors) {
        double[] result = new double[vectors[0].data.length];

        for (int i = 0; i < vectors[0].data.length; i++) {
            System.out.print("Row " + (i + 1) + ": \n");
            for (int j = 0; j < vectors.length; j++) {
                System.out.println("[" + vectors[j].data[i] + "]");

                result[i] += vectors[j].data[i];
            }
            System.out.println("=" + result[i]);
        }
        return new Vectors(result);
    }

    public static Vectors subtractVectors(Vectors... vectors){
        double[] result = new double[vectors[0].data.length];

        for (int i = 0; i < vectors[0].data.length; i++){
            result[i] = vectors[0].data[i];
            for (int j = 1; j < vectors.length; j++){
                result[i] -= vectors[j].data[i];
            }
        }
        return new Vectors(result);
    }

    // impelementation of overloading
    // one scalar, one vector
    public static Vectors scalarMultiply(double scalar, Vectors vector) {
        double[] newVector = vector.getVector();
        double[] resultingVector = new double[newVector.length];

        for (int i = 0; i < newVector.length; i++) {
            resultingVector[i] = (newVector[i] * scalar);
        }
        return new Vectors(resultingVector);
    }

    // multiple scalars, multiple vectors
    public static Vectors[] scalarMultiply(double[] scalars, Vectors... vectors) {
        Vectors[] resultingVectors = new Vectors[vectors.length];

        if (scalars.length != vectors.length) {
            throw new IllegalArgumentException("Number of scalars and number of vectors must match");
        }

        for (int i = 0; i < resultingVectors.length; i++) {
            resultingVectors[i] = scalarMultiply(scalars[i], vectors[i]);
        }
        return resultingVectors;
    }

    // one scalar, multiple vectors
    public static Vectors[] scalarMultiply(double scalar, Vectors... vectors) {
        Vectors[] resultingVectors = new Vectors[vectors.length];

        for (int i = 0; i < resultingVectors.length; i++) {
            resultingVectors[i] = scalarMultiply(scalar, vectors[i]);
        }
        return resultingVectors;
    }


    // overloading

    // method 1: one vector transpose
    public static Vectors transposeVector(Vectors v) {
        Orientation newOrientation;

        if (v.orientation == Orientation.COLUMN) {
            newOrientation = Orientation.ROW;
        } else {
            newOrientation = Orientation.COLUMN;
        }
        return new Vectors(v.getVector(), newOrientation);
    }


    // method 2: all vectors transposed
    public static Vectors[] transposeVector(Vectors[] vectors){
        Vectors[] result = new Vectors[vectors.length];
        for (int i = 0; i < result.length; i++){
            result[i] = transposeVector(vectors[i]);
        }
        return result;
    }


    // method overloading

    // method 1: format one vector
    public static String toStringVector(Vectors v){
        String result = "";

        if (v.getOrientation() == Orientation.COLUMN){
            for (int i = 0; i < v.data.length; i++) {
                result += "[" + v.data[i] + "]";

                if (i < v.data.length - 1) {
                    result += "\n";
                }
            }
            } else {
                result += "[";
                for (int i = 0; i < v.data.length; i++) {
                    result += v.data[i];

                    if (i < v.data.length - 1) {
                        result += " ";
                    }
            }
                result += "]";
        }
        return result;
    }

    // method 2: format multiple vectors
    public static String toStringVector(Vectors[] v) {
        String result = "[";

        for (int i = 0; i < v.length; i++) {
            result += toStringVector(v[i]);

            if (i < v.length - 1) {
                result += "\n\n";
            }
        }
        return result;
    }


    // physics stuff
    public static Vectors displacement(PhysicsParticles particle){
        return subtractVectors(particle.getCurrentPosition(), particle.getPreviousPosition());
    }


    public static Vectors velocity(PhysicsParticles particle, double previousTime, double currentTime){
        return scalarMultiply(MathematicalOperations.divide(1.0, Physics.deltaTime(previousTime, currentTime)), displacement(particle));
    }


    // distance
    public static double vectorDistance(Vectors v1, Vectors v2) {
        return getVectorLength(subtractVectors(v1, v2));
    }
}
