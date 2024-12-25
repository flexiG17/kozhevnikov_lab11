package LambdaExpressions;

import java.util.function.*;

public class LambdaExpressions {

    public static void main(String[] args) {
        Predicate<Integer> isGreaterThan50 = num -> num > 50;
        System.out.println(isGreaterThan50.test(60));
        System.out.println(isGreaterThan50.test(40));

        Consumer<String> printWeather = str -> System.out.println("Погода на сегодня: " + str);
        printWeather.accept("-15°");
        printWeather.accept("0°");

        Supplier<Double> randomValue = () -> Math.random() * 100;
        System.out.println(randomValue.get());
        System.out.println(randomValue.get());

        Function<Integer, Integer> multiplyByTwo = num -> num * 2;
        System.out.println(multiplyByTwo.apply(10));
        System.out.println(multiplyByTwo.apply(25));

        UnaryOperator<Integer> square = num -> num * num;
        System.out.println(square.apply(4));
        System.out.println(square.apply(7));

        BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;
        System.out.println(sum.apply(10, 20));
        System.out.println(sum.apply(15, 25));
    }
}
