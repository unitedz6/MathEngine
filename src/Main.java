import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.lang.reflect.Method;

public class Main {
    public static double[] dataSet = {1, 3, 7, 6, 2};


    public static void main(String[] args) {
        {
            Scanner scan = new Scanner(System.in);
            System.out.println("Statistical Analysis Project\n" + "Portafolio de análisis estadístico");
            System.out.println("English or Spanish?\n/ will always be used to signify the start of a command"+"\nIngles o Espanol?\nEl símbolo / siempre se utilizará para indicar el inicio de un comando.");

            System.out.println(Statistics.returnRange(dataSet));

            System.out.println("Sum:" + MathematicalOperations.add(5.0, 2.0));
            System.out.println("Subtraction: " + MathematicalOperations.subtract(5.0, 2.0));
            System.out.println("Division: " + MathematicalOperations.divide(10.0, 2.0));
            System.out.println("Multiplication: " + MathematicalOperations.multiply(5.0, 2.0, 3));

            System.out.println("Division: " + MathematicalOperations.divide(0, 0));
            System.out.println("Division: " + MathematicalOperations.divide(10.0, 0));
            Vectors v1 = new Vectors(new double[]{1,2,3});
            Vectors v2 = new Vectors(new double[]{4,5,6});
            Vectors addedVector = Vectors.returnAddedVectors(v1, v2);
            System.out.println(Arrays.toString(addedVector.getVector()));

            while (true) {

                String input = scan.nextLine();

                switch(input.toLowerCase()){
                    case "/english":
                        Language.english = true;
                        System.out.println("Language is in English.");
                        break;
                    case "/espanol":
                        Language.english = false;
                        System.out.println("La idioma es en Espanol.");
                        break;

                    case "/ayuda":
                    case "/help":
                        System.out.println(Language.translate(
                                "Which category? ArrayOperations, Statistics, MathematicalOperations, LinearAlgebra",
                                "¿Qué categoría? OperacionesDeArreglos, Estadisticas, OperacionesMatematicas, AlgebraLineal"
                        ));
                        String type = scan.nextLine();
                        getHelp(type, Language.english ? "english" : "spanish");
                }
            }
        }
    }

    public static void getHelp(String type, String language) {
       String[][] selectedHelp;

        switch (type.toLowerCase()) {

            case "/arrayoperations", "/operacionesdearreglos":
                selectedHelp = ArrayOperations.help;
                break;

            case "/statistics", "/estadisticas":
                selectedHelp = Statistics.help;
                break;

            case "/mathematicaloperations", "/operacionesmatematicas":
                selectedHelp = MathematicalOperations.help;
                break;

            case "/linearalgebra", "/algebralineal":
                selectedHelp = LinearAlgebra.help;
                break;


            default:
                System.out.println(Language.translate("Unknown command!", "¡Comando desconocido!"));
                return;
        }
        if (selectedHelp.length == 0){
            System.out.println(Language.translate("Currently no methods in this class!", "¡Actualmente no hay métodos en esta clase!"));

        }

        int nameIndex = Language.english ? 0 : 1;
        int descriptionIndex = Language.english ? 2: 3;
        for (int i = 0; i < selectedHelp.length; i++){
            System.out.println(" - " + selectedHelp[i][nameIndex]);
            System.out.println(" \t " + selectedHelp[i][descriptionIndex]);
        }
    }

    public static double[] createData(Scanner scan) {
        String input = scan.nextLine();
        String[] values = input.split(",");

        double[] data = new double[values.length];

        for (int i = 0; i < data.length; i++) {
            data[i] = Double.parseDouble(values[i].trim());
        }
        return data;
    }
}