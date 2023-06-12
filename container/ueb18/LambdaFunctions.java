package ueb18;

public class LambdaFunctions {
    static MyFunction bi = (x) -> x * x;

    static MyFunction bii = (x) -> {
        int result = 1;
        for (int i = 1; i <= x; i++) {
            result *= i;
        }
        return result;
    };

    static MyFunction biii = (x) -> (int) Math.pow(x, x + 1);

    static MyFunction biiii = (x) -> {
        if (x <= 1) {
            return x;
        }
        int fib = 1;
        int prevFib = 1;

        for (int i = 2; i < x; i++) {
            int temp = fib;
            fib += prevFib;
            prevFib = temp;
        }
        return fib;
    };
}
