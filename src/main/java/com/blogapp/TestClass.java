package com.blogapp;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestClass {
    /*public static void main(String[] args){
        Predicate<Integer> result = n->n%2!=0;   -----anothother pgm
        boolean val = result.test(100);
        System.out.println(val);
    }*/

   /* public static void main(String[] args) {
        List<Integer> list = Arrays.asList(10, 21, 24, 20);
        List<Integer> newList = list.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
        System.out.println(newList);
    }*/
    /*public static void main(String[] args) {
        List<String> data = Arrays.asList("adam", "mike", "smith", "sam");
        List<String> newData = data.stream().filter(str -> str.startsWith("s")).collect(Collectors.toList());
        System.out.println(newData);
    }*/

    public static void main(String[] args) {
        List<String> data = Arrays.asList("adam", "mike", "smith", "sam");
        List<String> newData = data.stream().distinct().collect(Collectors.toList());/*............avoid duplicates*/
        System.out.println(newData);
    }

}
