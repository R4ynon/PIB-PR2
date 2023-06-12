package ueb18;

public class Anonymous {
    MyFunction iAnonymous = new MyFunction() {
        @Override
        public int apply(int x) {
            return x * x;
        }
    };

    MyFunction iiAnonymous = new MyFunction() {
        @Override
        public int apply(int x) {
            int result = 1;
            for (int i = 1; i <= x; i++) {
                result *= i;
            }
            return result;
        }
    };

    MyFunction iiiAnonymous = new MyFunction() {
        @Override
        public int apply(int x) {
            return (int) Math.pow(x, x + 1);
        }
    };

    MyFunction iiiiAnonymous = new MyFunction(){
        @Override
        public int apply(int x){
            if(x == 1 || x == 2){
                return 1;
            }else{
                return apply(x - 1) + apply(x - 2);
            }
        }
    };
}
