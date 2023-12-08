package day6;

import util.ReadFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static util.NumberUtil.extractNumericValues;
import static util.NumberUtil.multiplyArray;

public class BoatRace
{
    public static void main(String[] args)
    {
        String filePath = "src/day6/input";
        List<String> lines = ReadFile.readFileLines(filePath);

        HashMap<Integer, Integer> gameRecords = new HashMap<>();

        String[] timeArray = extractNumericValues(lines.get(0));
        String[] distanceArray = extractNumericValues(lines.get(1));

        HashMap<Integer, Integer> timeDistanceMap = new HashMap<>();

        //Construct time-distance map
        for (int i = 0; i < timeArray.length; i++)
        {
            int time = Integer.parseInt(timeArray[i]);
            int distance = Integer.parseInt(distanceArray[i]);
            timeDistanceMap.put(time, distance);
        }

        ArrayList<Integer> eligibleTimeCountsForGames = new ArrayList<>();

        for (int time : timeDistanceMap.keySet())
        {
            int currentRecord = timeDistanceMap.get(time);
            int eligibleCombinations = 0;

            for (int i = 1; i < time; i++)
            {
                int distance = (time - i) * i;

                if (distance > currentRecord)
                {
                    eligibleCombinations = eligibleCombinations + 1;
                }
            }
            eligibleTimeCountsForGames.add(eligibleCombinations);
        }
        int totalWinningCombinations = multiplyArray(eligibleTimeCountsForGames);
        //Part 1 -answer
        System.out.println("Part 1 answer: " + totalWinningCombinations);
        //Part 2 - answer
        partTwo(timeArray, distanceArray);
    }

    public static void partTwo(String[] timeArray, String[] distanceArray)
    {
        long time = Long.valueOf(String.join("", timeArray));
        long currentRecord = Long.valueOf(String.join("", distanceArray));

        System.out.println("time: "+time+ " current record: " + currentRecord);
        int eligibleCombinations = 0;

        for (int i = 1; i < time; i++)
        {
            long distance = (time - i) * i;

            if (distance > currentRecord)
            {
                eligibleCombinations = eligibleCombinations + 1;
            }
        }
        System.out.println("Part 2 answer: "+eligibleCombinations);
    }
}
