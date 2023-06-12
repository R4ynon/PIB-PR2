package ueb18;

import static ueb18.ApplyAndPrint.applyAndPrint;

public class StaticNested {
    static class StaticNestedClass implements MyFunction{
        @Override
        public int apply(int x){
            int result = 1;
            for (int i = 1; i <= x; i++) {
                result *= i;
            }
            return result;
        }
        public void methodenCheck(){

            //Funktioniert nicht, da apply nicht static ist
            //applyAndPrint(1,10, StaticNested.StaticNestedClass :: apply)
            applyAndPrint(1, 10, new StaticNested.StaticNestedClass() :: apply);
        }
    }
}
