public class MathematicalOperations {
    public static String[][] help = {
            {"add", "Adds two or more numerical values and returns their sum.",
                    "sumar", "Suma dos o más valores numéricos y devuelve el resultado."},

            {"subtract", "Subtracts numerical values in the specified order and returns the difference.",
                    "restar", "Resta valores numéricos en el orden especificado y devuelve la diferencia."},

            {"multiply", "Multiplies two or more numerical values and returns their product.",
                    "multiplicar", "Multiplica dos o más valores numéricos y devuelve el producto."},

            {"divide", "Divides numerical values in the specified order and returns the quotient.",
                    "dividir", "Divide valores numéricos en el orden especificado y devuelve el cociente."},
    };

    public static double squareRoot(double number) {
        return number;
    }

    public static double add(double... numbers) {
        double sum = 0;

        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public static double subtract(double... numbers){
        double difference = numbers[0];

        for (int i = 1; i < numbers.length; i++){
            difference -= numbers[i];
        }
        return difference;
    }

    public static double multiply(double... numbers) {
        double product = 1;

        for (int i = 0; i < numbers.length; i++) {
            product *= numbers[i];
        }
        return product;
    }

    public static double divide(double... numbers) {
        double quotient = numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            try {
                if (numbers[i] == 0 && quotient == 0) {
                    throw new ArithmeticException("Cannot divide by zeroooo");
                } else if (numbers[i] == 0) {
                    throw new ArithmeticException("Undefined: Division by zero");
                }
                quotient /= numbers[i];

            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            }
        }
        return quotient;
    }
}
