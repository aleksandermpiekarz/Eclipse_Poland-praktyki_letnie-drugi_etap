package com.internship.repository;

class StringCalculator {

    public int add(String numbers){
        String sepNumbers[];
        int sum;

        if(numbers.isEmpty()){
            return 0;
        }else if(numbers.contains(",")) {
            sepNumbers = numbers.split(",");
            sum = Integer.valueOf(sepNumbers[0]) + Integer.valueOf(sepNumbers[1]);
            return sum;
        }else {
            return Integer.valueOf(numbers);
        }
    }
}
