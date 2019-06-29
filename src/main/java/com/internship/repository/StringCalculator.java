package com.internship.repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class StringCalculator {

    public int add(String numbers){
        List<Integer> sepNumbers ;
        int sum;

        if(numbers.isEmpty()){ // if string is empty, method returns 0;
            return 0;
        }else if(numbers.contains(",")) { // if string contains some separator it means, there are at least two numbers

            // Arrays.asList() is an array with numbers separated by ",", stream is used to
            // convert string array into integer list.
            sepNumbers = Arrays.asList(numbers.split(","))
                    .stream()
                    .map(s -> Integer.parseInt(s))
                    .collect(Collectors.toList());

            // Here again stream is used to calculate sum of all elements of the list
            sum = sepNumbers
                    .stream().mapToInt(Integer::intValue)
                    .sum();
            return sum;
        }else {  // if string is not empty and does not contain any separator, it means that there is only one number
            return Integer.valueOf(numbers);
        }


    }
}
