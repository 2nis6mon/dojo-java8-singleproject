package org.dojo.java8.exercise1;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

public class FunctionGenerator {

    public static Function<Integer, Integer> plusOneFunction() {
        //TODO replace with lambda syntax
        return new Function<Integer, Integer>() {

            @Override
            public Integer apply(Integer value) {
                return value + 1;
            }
        };
    }

    public static Predicate<Integer> pairNumberPredicate() {
        //TODO replace with lambda syntax
        return new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer % 2 == 0;
            }
        };
    }

    public static Consumer<int[]> incrementArrayValueConsumer() {
        //TODO replace with lambda syntax
        return new Consumer<int[]>() {
            @Override
            public void accept(int[] arrayValues) {
                for (int i = 0; i < arrayValues.length; i++) {
                    arrayValues[i] = arrayValues[i] + 1;

                }
            }
        };
    }

    public static Supplier<Integer> todaySupplier() {
        //TODO replace with lambda syntax
        return new Supplier<Integer>() {
            @Override
            public Integer get() {
                return LocalDate.now().get(ChronoField.DAY_OF_MONTH);

            }
        };
    }

    public static BiFunction<String, Integer, String> concatAsStringFunction() {
        //TODO replace with lambda syntax
        return new BiFunction<String, Integer, String>() {

            @Override
            public String apply(String firstValue, Integer secondValue) {
                return firstValue + " " + secondValue;
            }
        };
    }

    // BinaryOperator is BiFunction with same generic type
    public static BinaryOperator<Integer> sumFunction() {
        //TODO replace with static method reference
        return new BinaryOperator<Integer>() {

            @Override
            public Integer apply(Integer firstValue, Integer secondValue) {
                return Integer.sum(firstValue, secondValue);
            }
        };
    }

    public static Consumer<String> addToListConsumer(List<String> list) {
        //TODO replace with instance method reference
        return new Consumer<String>() {
            @Override
            public void accept(String value) {
                list.add(value);
            }
        };
    }

    public static Function<String, BigDecimal> stringToBiDecimalFunction() {
        //TODO replace with constructor reference
        return new Function<String, BigDecimal>() {
            @Override
            public BigDecimal apply(String value) {
                return new BigDecimal(value);
            }
        };
    }

    // Comparator is also a FunctionalInterface that can be replaced by lambda syntax
    public static Comparator<String> byStringLengthComparator() {
        //TODO replace with lambda syntax
        return new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        };
    }

}
