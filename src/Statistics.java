public class Statistics {
    public static String type = "Population";

    public static String[][] help = {
            {"returnMedian", "obtenerMediana",
                    "Returns the middle value of a sorted dataset. If there are two middle values, returns their average.",
                    "Devuelve el valor central de un conjunto de datos ordenados. Si hay dos valores centrales, devuelve su promedio."},

            {"zScoreStandardization", "estandarizarPuntuacionesZ",
                    "Converts each value into a z-score, showing how many standard deviations it is above or below the mean.",
                    "Convierte cada valor en una puntuación Z, indicando cuántas desviaciones estándar está por encima o por debajo de la media."},

            {"returnStandardDeviation", "obtenerDesviacionEstandar",
                    "Measures how spread out the data is around the mean.",
                    "Mide qué tan dispersos están los datos alrededor de la media."},

            {"returnAverage", "obtenerPromedio",
                    "Returns the arithmetic mean by adding all values and dividing by the number of values.",
                    "Devuelve la media aritmética sumando todos los valores y dividiendo entre la cantidad de valores."},

            {"returnRange", "obtenerRango",
                    "Returns the difference between the largest and smallest values in a dataset.",
                    "Devuelve la diferencia entre el valor más grande y el más pequeño de un conjunto de datos."},
    };

    public static double returnRange(double[] data){
        return (ArrayOperations.returnMax(data) - ArrayOperations.findMin(data));
    }


    public static double returnAverage(double[] data){
        int elements = 0;
        double average = 0;
        for (int i = 0; i < data.length; i++){
            average = average + data[i];
            elements++;
        }

        if (elements == 0){
            throw new IllegalArgumentException("Cannot divide by 0!");
        }
        return (average / elements);
    }

    public static double returnStandardDeviation(double[] data, String type){
        double mean = returnAverage(data);
        double sum = 0;
        int denominator;

        for (int i = 0; i < data.length; i++){
            double difference = data[i] - mean;
            sum = sum + (difference * difference);
        }

        if (type.equalsIgnoreCase("Population")){
            denominator = data.length;
        } else if (type.equalsIgnoreCase("Sample")) {
            denominator = data.length - 1;
        } else {
            throw new IllegalArgumentException("Use a sample or population");
        }
        return Math.sqrt(sum / denominator);
    }


    public static double getZScore(){
        return 0;
    }

    public static double[] zScoreStandardization(double[] data){
        double[] zScore = new double[data.length];

        double average = returnAverage(data);
        double standardDeviation = returnStandardDeviation(data, type);
        for (int i = 0; i < data.length; i++){
            zScore[i] = (data[i] - average) / standardDeviation;
        }
        return zScore;
    }

    public static int returnFrequency(double[] data, double value){
        int counter = 0;

        for (int i = 0; i < data.length; i++){
            if (data[i] == value){
                counter++;
            }
        }
        return counter;
    }

    public static int returnMaxFrequencyIndex(double[][] data, double value) {
        int highestFreq = -1;
        int bestIndex = 0;

        for (int i = 0; i < data.length; i++) {
            int currentFrequency = returnFrequency(data[i], value);

            if (currentFrequency > highestFreq) {
                highestFreq = currentFrequency;
                bestIndex = i;
            }
        }
        return bestIndex;
    }

    public static int returnMaxFrequencyIndex(Matrix m, double value) {
        int highestFreq = -1;
        int bestIndex = 0;

        for (int i = 0; i < m.getCols(); i++) {
            int currentFrequency = returnFrequency(m.getColumn(i), value);

            if (currentFrequency > highestFreq) {
                highestFreq = currentFrequency;
                bestIndex = i;
            }
        }
        return bestIndex;
    }

    public static double returnMedian(double[] data){
        double[] medianData = new double[data.length];
        for (int i = 0; i < data.length; i++){
            medianData[i] = data[i];
        }
        boolean oddOrEven;

        if (medianData.length % 2 ==0){
            oddOrEven = true;
        } else {
            oddOrEven = false;
        }

        if (!oddOrEven) {
            return medianData[medianData.length / 2];
        } else {
            return (medianData[medianData.length / 2 - 1] + medianData[medianData.length / 2]) / 2.0;
        }
    }
}
