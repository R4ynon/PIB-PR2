package ueb18;

import static ueb18.ApplyAndPrint.applyAndPrint;

class iiTopLevel implements MyFunction {
    @Override
    public int apply(int x) {
        int result = 1;
        for (int i = 1; i <= x; i++) {
            result *= i;
        }
        return result;
    }
    public void methodenCheck(){
        applyAndPrint(1, 10, new iiTopLevel() :: apply);
    }

}
