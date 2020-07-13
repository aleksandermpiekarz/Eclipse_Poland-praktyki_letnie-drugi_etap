package com.internship.repository;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
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

        delimiter = Optional.ofNullable(StringUtils
                .substringBetween(numbers, "//", "\n"))
                .orElse(",|\\n");

         numbers = StringUtils.remove(numbers,"//" + delimiter + "\n");

        if(delimiter.contains("[")&&delimiter.contains("]")) {
            delimiters = StringUtils
                    .substringsBetween(delimiter, "[", "]");

            delimiter = this.toScan(delimiters);
                                                 
         }else {
            delimiters = new String[]{",","\n",delimiter};
        }

        if(numbers.isEmpty()){ // if string is empty, method returns 0;
            return 0;

       }else if(StringUtils.indexOfAny(numbers, delimiters)!=-1) {

           sepNumbers = Arrays.asList(numbers.split(delimiter))
                    .stream()
                    .map(s -> Integer.parseInt(s))
                    .collect(Collectors.toList());

            try {
                this.checkIfNegative(sepNumbers);
            }catch (NegativeNumberException e){
                System.out.println(e);
                return -1;
            }

           sum = sepNumbers
                    .stream().mapToInt(Integer::intValue).filter(i -> i <=1000)
                    .sum();
            return sum;
        }else {
            Integer retValue = Integer.valueOf(numbers);

            try {
                this.checkIfNegative(Arrays.asList(retValue));
            }catch (NegativeNumberException e){
                System.out.println(e);
                return -1;
            }

            if(retValue > 1000) {
                return 0;
            }
            return retValue;
        }
    }

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

    private void checkIfNegative(List<Integer> sepNumbers) throws NegativeNumberException {
        List<Integer> errorList;
        errorList = sepNumbers.stream().filter(i -> i < 0).collect(Collectors.toList());
        if (!errorList.isEmpty()) {
            throw new NegativeNumberException(errorList);
        }
    }

    class NegativeNumberException extends Exception {
        List<Integer> id;

        public NegativeNumberException(List<Integer> id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "negatives not allowed " + id ;
        }
    }

}
