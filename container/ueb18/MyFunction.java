package ueb18;

import java.util.function.IntPredicate;

public interface MyFunction {
    public int apply(int i);

    default MyFunction conditionateInput(IntPredicate t){

        return (x) -> {
            if(t.test(x) == true){
                return apply(x);
            }
                return 0;
        };
    }
    default MyFunction conditionateOutput(IntPredicate t){
        return (x) -> {
            if(t.test(apply(x))){
                return apply(x);
            }
                return 0;
        };
    }

}
