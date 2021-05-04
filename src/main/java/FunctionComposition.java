import java.util.function.Function;

public class FunctionComposition {


    public static void main(String[] args) {
        Function<Integer, Integer> mulFn = (a) -> a * 2; //R
        Function<Integer, Integer> addFn = (a) -> 2 + a; //G
        var fnComp = mulFn.compose(addFn); //R o G
        var result = fnComp.apply(3); //10
        System.out.println(result);
        var fnThen = mulFn.andThen(addFn); //G o R
        result = fnThen.apply(3); //8
        System.out.println(result);
    }


}
