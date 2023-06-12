package ueb18;
public class ApplyAndPrint {
    public static void applyAndPrint(int i,  int j, MyFunction lambda){
        for(int m = i; m <= j; m++){
            System.out.println(lambda.apply(m));
        }
    }

}
