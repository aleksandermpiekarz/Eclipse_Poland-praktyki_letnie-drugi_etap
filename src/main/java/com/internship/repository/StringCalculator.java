package com.internship.repository;


import org.apache.commons.lang3.StringUtils;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class StringCalculator {

    public int add(String numbers) {
        List<Integer> sepNumbers ;
        List<Integer> errorList;
        int sum;


        String delimiter = Optional.ofNullable(StringUtils
                .substringBetween(numbers, "//", "\n"))
                .orElse(",|\\n");
        numbers = StringUtils.remove(numbers,("//" + delimiter)+"\n");

        if(numbers.isEmpty()){ // if string is empty, method returns 0;
            return 0;
        }else if(numbers.contains(",")
                ||numbers.contains("\n")
                ||numbers.contains(delimiter)) { // if string contains some separator it means, there are at least two numbers

            // Arrays.asList() is an array with numbers separated by ",", stream is used to
            // convert string array into integer list.
            sepNumbers = Arrays.asList(numbers.split(delimiter))
                    .stream()
                    .map(s -> Integer.parseInt(s))
                    .collect(Collectors.toList());
            errorList = sepNumbers.stream().filter(i -> i < 0).collect(Collectors.toList());

            try {
                if (!errorList.isEmpty()) {
                    throw new IllegalArgumentException();
                }
            }catch (IllegalArgumentException e){
                System.out.println("negatives not allowed " + errorList.toString());
                return -1;
            }

            // Here again stream is used to calculate sum of all elements of the list
            sum = sepNumbers
                    .stream().mapToInt(Integer::intValue)
                    .sum();
            return sum;
        }else {  // if string is not empty and does not contain any separator, it means that there is only one number
            Integer retValue = Integer.valueOf(numbers);
            try {
                if (retValue < 0) {
                    throw new IllegalArgumentException();
                }
            }catch (IllegalArgumentException e){
                System.out.println("negatives not allowed " + retValue);
                return -1;
            }
            return retValue;
        }
    }



}
