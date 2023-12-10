package day9;

import util.ReadFile;

import java.util.ArrayList;
import java.util.List;

public class Extrapolator
{
    public static void main(String[] args)
    {
        String filePath = "src/day9/input";
        List<String> lines = ReadFile.readFileLines(filePath);
        List<List<Integer>> originalReadings = new ArrayList<>();
        int sumOfExtrapolated = 0;

        for (String line : lines)
        {
            String[] numberStrings = line.split("\\s");
            List<Integer> numbers = new ArrayList<>();

            for (String numberString : numberStrings)
            {
                numbers.add(Integer.parseInt(numberString));
            }
            originalReadings.add(numbers);
        }
        for (List<Integer> numbers : originalReadings)
        {
            List<List<Integer>> numberLines = new ArrayList<>();
            numberLines.add(numbers);
            List<List<Integer>> allLines = buildNextLineForReadingSet(numberLines);
            numberLines.addAll(allLines);
            sumOfExtrapolated += extrapolateNext(numberLines);
        }
        System.out.println("Answer: " + sumOfExtrapolated);

    }

    public static List<List<Integer>> buildNextLineForReadingSet(List<List<Integer>> previous)
    {
        List<List<Integer>> allLines = new ArrayList<>();
        if (previous == null || previous.isEmpty())
        {
            return previous;
        }
        List<Integer> previousList = previous.get(previous.size() - 1);

        final List<Integer> nextList = new ArrayList<>();
        for (int i = 0; i < previousList.size() - 1; i++)
        {
            nextList.add(previousList.get(i + 1) - previousList.get(i));
        }
        allLines.add(nextList);
        if (!nextList.isEmpty() && nextList.get(nextList.size() - 1) != 0)
        {
            allLines.addAll(buildNextLineForReadingSet(allLines));
        }
        return allLines;
    }

    public static int extrapolateNext(List<List<Integer>> numberLines)
    {
        int sum = 0;
        //System.out.println("Before " + numberLines);
        for (int i = numberLines.size() - 1; i > 0; i--)
        {
            List<Integer> previous = numberLines.get(i);
            List<Integer> next = numberLines.get(i - 1);

            int lastNumInNext = next.get(next.size() - 1);
            int lastNumInPrevious = previous.get(previous.size() - 1);
            if (i == 0)
            {
                previous.add(0);
            }
            next.add(lastNumInNext + lastNumInPrevious);
        }
        List<Integer> firstLine = numberLines.get(0);
        sum += firstLine.get(firstLine.size() - 1);

        //System.out.println("After " + numberLines);
        return sum;
    }

}
