package day5;

import util.ReadFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.CharacterUtil.removeBlankLines;
import static util.NumberUtil.findLowestNumber;

public class SeedLocationMapper
{
    public static void main(String[] args)
    {
        String filePath = "src/day5/input";
        List<String> lines = ReadFile.readFileLines(filePath);
        lines = removeBlankLines(lines);
        List<String> categories = Arrays.asList(
                        "seeds:",
                        "seed-to-soil map:",
                        "soil-to-fertilizer map:",
                        "fertilizer-to-water map:",
                        "water-to-light map:",
                        "light-to-temperature map:",
                        "temperature-to-humidity map:",
                        "humidity-to-location map:");

        ArrayList<String> linesForACategory = new ArrayList<>();
        HashMap<String, ArrayList<Range>> allCategoryRanges = new HashMap<>();
        String source;
        String destination = "";
        int start = 0;
        String[] seeds = new String[0];
        for (int i = 0; i < categories.size(); i++)
        {
            source = categories.get(i);
            if ((i + 1) < categories.size())
            {
                destination = categories.get(i + 1);
            }

            for (int j = start; j < lines.size(); j++)
            {
                if (lines.get(j).contains(source))
                {
                    linesForACategory.add(lines.get(j));
                }
                else if (lines.get(j).contains(destination))
                {
                    start = j;
                    break;
                }
                else
                {
                    linesForACategory.add(lines.get(j));
                }
            }
            if (source.equals("seeds:"))
            {
                seeds = splitSeeds(linesForACategory.get(0));
            }
            constructRange(source, linesForACategory, allCategoryRanges);
            linesForACategory = new ArrayList<>();
        }
        Long lowestLocationNumber = findLowestNumber(findlowestLocation(seeds, categories, allCategoryRanges));
        System.out.println("lowest Location Number : " + lowestLocationNumber);
    }

    public static HashMap<String, ArrayList<Range>> constructRange(
                    final String categaryName,
                    ArrayList<String> linesForACategory,
                    HashMap<String, ArrayList<Range>> allCategoryRanges)
    {
        String[] numberStrings;
        Long[] numbers;
        // HashMap<String, ArrayList<Range>> categoryRanges = new HashMap<>();
        ArrayList<Range> ranges = new ArrayList<>();
        for (int i = 1; i < linesForACategory.size(); i++)
        {
            numberStrings = linesForACategory.get(i).split("\\s+");
            numbers = new Long[numberStrings.length];
            for (int j = 0; j < numberStrings.length; j++)
            {
                numbers[j] = Long.parseLong(numberStrings[j]);
            }
            Range range = new Range(numbers[0], numbers[1], numbers[2]);
            ranges.add(range);
        }
        allCategoryRanges.put(categaryName, ranges);
        System.out.println(allCategoryRanges);

        return allCategoryRanges;
    }

    public static String[] splitSeeds(final String seedsString)
    {
        // Extract numbers after "seeds:" and split into an array
        String[] seedTokens = seedsString.split("\\s+");

        // Skip the first element (which is "seeds:") and create a new array
        String[] seedNumbers = new String[seedTokens.length - 1];
        System.arraycopy(seedTokens, 1, seedNumbers, 0, seedNumbers.length);

        return seedNumbers;
    }
    public static ArrayList<Long> findlowestLocation(
                    String[] seeds,
                    List<String> categories,
                    HashMap<String, ArrayList<Range>> allCategoryRanges)
    {
        ArrayList<Long> destinationPos = new ArrayList<>();
        Long sourceValue;
        ArrayList<Range> ranges;
        for (int i = 0; i < seeds.length; i++)
        {
            sourceValue = Long.parseLong(seeds[i]);
            System.out.println("Starting for seed -----> " + sourceValue);

            for (String category : categories)
            {
                for (Map.Entry<String, ArrayList<Range>> entry : allCategoryRanges.entrySet())
                {
                    if (entry.getKey().equals(category))
                    {
                        ranges = entry.getValue();
                        System.out.println("Category: " + category);
                        sourceValue = getMatchingDestinationPos(sourceValue, ranges);
                        System.out.println(sourceValue);
                    }
                }
            }

            destinationPos.add(sourceValue);
        }
        return destinationPos;
    }

    private static Long getMatchingDestinationPos(Long number, ArrayList<Range> ranges)
    {
        Range range = null;
        for (Range r : ranges)
        {
            Long start = r.getSourceRangeStart();
            Long end = start + r.getRangeLength();

            if (number >= start && number <= end)
            {
                range = r;
                break;
            }
        }
        if (range != null)
        {
            Long pos = number - range.getSourceRangeStart();
            return range.getDestinationRangeStart() + pos;
        }
        return number;
    }
}
