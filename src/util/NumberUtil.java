package util;

import java.util.List;

public class NumberUtil
{
    public static Integer countArray(List<Integer> numbers){
        int sum = 0;
        for (int number : numbers)
        {
            sum += number;
        }

        return sum;
    }

    public static Integer multiplyArray(List<Integer> numbers){
        int sum = 0;
        for (int number : numbers)
        {
            if(sum==0){
                sum=1;
            }
            sum = sum *number;
        }

        return sum;
    }

    public static Long findLowestNumber(List<Long> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("ArrayList is empty");
        }

        Long min = list.get(0); // Assume the first element is the minimum

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < min) {
                min = list.get(i); // Update the minimum if a smaller element is found
            }
        }

        return min;
    }

    public static String[] extractNumericValues(String input) {
        // Remove non-numeric characters and split by spaces
        return input.replaceAll("[^0-9\\s]", "").trim().split("\\s+");
    }
}
