package com.internship.repository;


import org.apache.commons.lang3.StringUtils;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class StringCalculator {

    public int add(String numbers) {
        List<Integer> sepNumbers ;
        List<Integer> errorList;
        String delimiter;
        String[] delimiters = null;
        int sum;

        // here add method looks for delimiters. It can be in the form of //delimiter\n or //[d1][d2]\n
        // if there is no optional first line with delimiters, standard "," and "\n" delimiter is used
        delimiter = Optional.ofNullable(StringUtils
                .substringBetween(numbers, "//", "\n"))
                .orElse(",|\\n");

        // getting rid of the first line ( if exist )
        numbers = StringUtils.remove(numbers,"//" + delimiter+"\n");

        // app method jumps into this method if first line is in the form //[d1][d2]\n
        // then were simply using substringsBetween instead of substringBetween ( plural is the difference ) to
        // get multiple delimiters closed between square brackets
        if(delimiter.contains("[")&&delimiter.contains("]")) {
            delimiters = StringUtils
                    .substringsBetween(delimiter, "[", "]");

            delimiter = this.toScan(delimiters); // toScan is called to get proper form of delimiters, explained below
                                                 // app method
         }

        if(numbers.isEmpty()){ // if string is empty, method returns 0;
            return 0;

            // if string contains some separator it means, there are at least two numbers
            // ",","\n" and single delimiter are left untouched to ensure backward compatibility for previous tasks
        }else if(numbers.contains(",")
                ||numbers.contains("\n")
                ||numbers.contains(delimiter)
                ||StringUtils.indexOfAny(numbers, delimiters)!=-1) {

            // Arrays.asList() is an array with numbers separated by ",", stream is used to
            // convert string array into integer list.
            sepNumbers = Arrays.asList(numbers.split(delimiter))
                    .stream()
                    .map(s -> Integer.parseInt(s))
                    .collect(Collectors.toList());
            errorList = sepNumbers.stream().filter(i -> i < 0).collect(Collectors.toList()); // negative values are stored here

            try {
                if (!errorList.isEmpty()) { // if error list isn't empty, it means that there are negative numbers
                    throw new IllegalArgumentException(); // if there are negative numbers IllegalArgumentException is thrown
                }
            }catch (IllegalArgumentException e){
                System.out.println("negatives not allowed " + errorList.toString());
                return -1;
            }

            // Here again stream is used to filter values below 1000, and calculate sum of that elements
            sum = sepNumbers
                    .stream().mapToInt(Integer::intValue).filter(i -> i <=1000)
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

            if(retValue > 1000) { // if value is greater than 1000 simply we can return 0
                return 0;
            }
            return retValue;
        }
    }

    // this function takes table of delimiters, and transforms into form table[0]|table[1] and so on
    // function toScan is used to put multiple arguments to .split() function
    private String toScan(String[] strings) {
        String finalString = "";

        for(int i = 0; i < strings.length; i++){
            finalString += strings[i];

            if(i != strings.length-1){
                finalString += "|";
            }
        }
        return  finalString;
    }
}
