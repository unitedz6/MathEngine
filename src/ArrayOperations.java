public class ArrayOperations {

    public static String[][] help = {
            {"findMin", "buscarMinimo",
                    "Returns the smallest number in the dataset.",
                    "Devuelve el número más pequeño del conjunto de datos."},

            {"findMax", "buscarMaximo",
                    "Returns the largest number in the dataset.",
                    "Devuelve el número más grande del conjunto de datos."},

            {"getLength", "obtenerLongitud",
                    "Returns the total number of elements in the dataset.",
                    "Devuelve la cantidad total de elementos del conjunto de datos."},

            {"sortAscending", "ordenarAscendente",
                    "Returns the dataset sorted from smallest to largest.",
                    "Devuelve los datos ordenados de menor a mayor."}
    };

    public static double findMin(double[] data) {
        double starting = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] < starting) {
                starting = data[i];
            }
        }
        return starting;
    }

    public static int returnDataLength(double[] data) {
        return data.length;
    }

    public static double[] returnSortedList(double[] data) {

        double startingComparison;
        double secondComparison;
        double highest;

        for (int i = 0; i < data.length; i++) {
            startingComparison = data[i];
            highest = startingComparison;
            for (int k = i + 1; k < data.length; k++) {
                secondComparison = data[k];

                if (startingComparison > secondComparison) {
                    highest = data[i];

                    data[i] = data[k];
                    data[k] = highest;

                    startingComparison = data[i];

                } else if (startingComparison < secondComparison) {
                    highest = data[k];
                } else {
                    highest = data[k]; // start right most elemenent even if data[i]==data[k]
                }

                System.out.println("Our first variable we are comparing (i): " + startingComparison + " with our second variable, (j):" + secondComparison + " and our highest value: " + highest);

            }
        }
        return data;
    }

    public static double returnMax(double[] data) {
        double starting = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] > starting) {
                starting = data[i];
            }
        }
        return starting;
    }
}
