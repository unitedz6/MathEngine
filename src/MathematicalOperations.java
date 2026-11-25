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

    public static final double PI = 3.141592653589793;

    public static double squareRoot(double x) {
        // needs convergence testing
        double result = 1;
        for (int i = 0; i < 20; i++) {
            result = result - (exponent(result, 2) - x) / (2 * result);
        }
        return result;
    }

    // create our own pi estimate function

    public static double absolute(double value){
        if (value < 0){
            value *= -1;
        }
        return value;
    }

    public static double factorial(double value){
        double result = 1;

        for (int i = 1; i <= value; i++){
            result *= i;
        }
        return result;
    }

    public static double exponent(double base, int power)  {
        double result = 1;

        if (power == 0){
            return result;
        }

        if (power < 0) {
            return 1 / exponent(base, -power);
        }

        for (int i = 0; i < power; i++){
            result *= base;
        }

        return result;
    }

    public static double sin(double x){
        double result = 0;
        for (int n = 0; n < 10; n++) {
            result += exponent(-1, n)*exponent(x, (2*n)+1)/factorial((2*n)+1);
        }
        return result;
    }

    public static double cos(double x){
        double result = 0;
        for (int n = 0; n < 10; n++) {
            result += exponent(-1, n)*exponent(x, (2*n))/factorial((2*n));
        }
        return result;
    }

    public static double toRadians(double degrees){
        return degrees * PI / 180;
    }

    public static double toDegrees(double radians){
        return radians * 180 / PI;
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
