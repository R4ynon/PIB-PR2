package ueb18;

import java.util.function.IntPredicate;

public class UApplyAndPrint {

    public void quadratZahl() {
        MyFunction fak = (y) -> {
            int result = 1;
            for (int i = 1; i <= y; i++) {
                result *= i;
            }
            return result;
        };
        MyFunction fakIfOdd = fak.conditionateOutput(Predicates.odd());
        ApplyAndPrint.applyAndPrint(1, 10, fakIfOdd);
    }

    public void fakultaet() {
        MyFunction square = (x) -> x * x; // = apply(int i)
        MyFunction squareIfEven = square.conditionateInput(Predicates.even());

        ApplyAndPrint.applyAndPrint(1, 10, squareIfEven);
    }



}