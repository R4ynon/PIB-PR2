package ueb18;

import java.util.function.IntPredicate;

public class Predicates {

    public static IntPredicate even() {
        return (x) -> {
            if (x % 2 == 0) {
                return true;
            }
            return false;
        };
    }

    public static IntPredicate odd(){
        return new IntPredicate() {
            @Override
            public boolean test(int x) {
                if(x%2 !=0){
                    return true;

                }
                return false;
            }
        };
    }

}
