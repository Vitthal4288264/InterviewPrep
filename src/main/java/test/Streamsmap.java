package test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Streamsmap {

    public static void main(String[] args) {


        List<String> lst  = Arrays.asList("hello","world");
        List<String> upperCase = lst.stream().map(String::toUpperCase).collect(Collectors.toList());
        upperCase.forEach(System.out::print);

        //filter
        List<Integer> lstI = Arrays.asList(1,3,5,6,7,8,10);
        List<Integer> evenNums = lstI.stream().filter(n -> n % 2 == 0).toList();
        evenNums.forEach(System.out::println);

        //reduce: int sum = numbers.stream().reduce(0, (a, b) -> a + b);.
        List<Integer> lst2 = Arrays.asList(1,3,5,6,7,8,10);
        int sum = lst2.stream().reduce(0, (a,b) -> (a+b));
        System.out.println("Sum of list values =>"+sum);

        int su1 = lst2.stream().reduce(0, Integer::sum) /2;
        System.out.println("Sum of list values =>"+su1);


        //flatmap

        List<List<String>> nestedLst = Arrays.asList(
                Arrays.asList("apple","orange"), Arrays.asList("mango", "banana"), Arrays.asList("grape","kiwi")
        );

        nestedLst.stream().flatMap(Collection::stream).map(String::toUpperCase).forEach(System.out::println);


    }
}

