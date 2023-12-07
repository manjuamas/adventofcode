package day1;

import util.ReadFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberWordCounter
{
    public static void main(String[] args)
    {
        String filePath = "src/day1/input";
        List<String> numberWords = Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten");
        List<String> lines = ReadFile.readFileLines(filePath);

        List<String> newLines = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();

        for (String line : lines)
        {
            System.out.println(line);

            String converted = convertNumberWords(line, numberWords);
            newLines.add(converted);
            System.out.println(converted);

        }
        for (String line : newLines)
        {
            numbers.add(Integer.valueOf(line));
        }

        int sum = 0;
        //System.out.println(numbers);
        for (int number : numbers)
        {
            sum += number;
        }
        System.out.println(numbers);
        System.out.println(sum);
    }
    private static String convertNumberWords(String inputString, List<String> numberWords)
    {
        Map<Integer, Integer> numberWordAndPosition = new HashMap<>();
        if (inputString == null && inputString.isEmpty())
        {
            return "0";
        }

        String temp = inputString;
        for (String numberWord : numberWords)
        {
            int startIndex = 0;
            temp = inputString;
            int actualPos = 0;
            for (int i = 0; i < temp.length(); i++)
            {
                if (temp.contains(numberWord))
                {
                    startIndex = actualPos + temp.indexOf(numberWord);
                    int hold =temp.indexOf(numberWord);
                    System.out.println("word: " + numberWord + " startIndex: " + startIndex);
                    temp = temp.substring(hold + 1);

                    actualPos = actualPos + inputString.indexOf(temp);
                    numberWordAndPosition.put(startIndex, replaceNumberWord(numberWord));
                }
            }
        }

        temp = inputString;
        for (int i = 0; i < temp.length(); i++)
        {
            char firstCharacter = temp.charAt(i);
            if (Character.isDigit(firstCharacter))
            {
                int digitValue = firstCharacter - '0';
                numberWordAndPosition.put(i, digitValue);
            }
        }

        Integer lowestKey = numberWordAndPosition.keySet().stream().min(Integer::compare).orElse(null);
        Integer highestKey = numberWordAndPosition.keySet().stream().max(Integer::compare).orElse(null);

        if (lowestKey != null && highestKey != null)
        {
            String h = String.valueOf(numberWordAndPosition.get(lowestKey)) + String.valueOf(numberWordAndPosition.get(highestKey));
           System.out.println(numberWordAndPosition);

            return h;
        }

        return inputString;
    }

    private static int replaceNumberWord(String numberWord)
    {
        switch (numberWord)
        {
        case "one":
            return 1;
        case "two":
            return 2;
        case "three":
            return 3;
        case "four":
            return 4;
        case "five":
            return 5;
        case "six":
            return 6;
        case "seven":
            return 7;
        case "eight":
            return 8;
        case "nine":
            return 9;
        }
        return 0;
    }

}
