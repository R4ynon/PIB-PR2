package ueb18;

public class Lambda {
    MyFunction iLambda = (int x) -> x * x;

    MyFunction iiLambda = (int x) -> {
        int result = 1;
        for (int i = 1; i <= x; i++) {
            result *= i;
        }
        return result;
    };

    MyFunction iiiLambda = (int x) -> (int) Math.pow(x, x + 1);

    // fibonacci fehlt
}
