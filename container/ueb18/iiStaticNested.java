package ueb18;

class iiStaticNested implements MyFunction {
    @Override
    public int apply(int x) {
        int result = 1;
        for (int i = 1; i <= x; i++) {
            result *= i;
        }
        return result;
    }
}