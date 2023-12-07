package day2;

import util.ReadFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameCubes
{
    public static void main(String[] args)
    {
        String filePath = "/Users/manjula/Desktop/hackathon/adventofcode/src/day2/input";
        List<String> lines = ReadFile.readFileLines(filePath);
        Map<Integer, Boolean> isPossible = new HashMap<>();

        for (String line : lines)
        {
            String[] splitArray = line.split("\\s*,\\s*|\\s+");
            ArrayList<String> gameList = new ArrayList<>(Arrays.asList(splitArray));
            Integer key = Integer.valueOf(gameList.get(1).substring(0, gameList.get(1).length() - 1));
            System.out.println(gameList);

            ArrayList<ColourSet> colourSets = new ArrayList<>();

            boolean play = true;
            while (play)
            {
                ColourSet gameSet = new ColourSet();

                for (int i = 0; i < gameList.size(); i++)
                {
                    String word = gameList.get(i);
                    boolean breakLoop = false;
                    if (word.contains(";"))
                    {
                        word = word.substring(0, word.length() - 1);
                        breakLoop = true;
                    }
                    if (i == gameList.size() - 1)
                    {
                        breakLoop = true;
                    }
                    if (word.equals("red"))
                    {
                        gameSet.setReds(Integer.parseInt(gameList.get(i - 1)));
                    }
                    if (word.equals("blue"))
                    {
                        gameSet.setBlues(Integer.parseInt(gameList.get(i - 1)));
                    }
                    if (word.equals("green"))
                    {
                        gameSet.setGreens(Integer.parseInt(gameList.get(i - 1)));
                    }

                    if (breakLoop)
                    {
                        colourSets.add(gameSet);
                        gameSet = new ColourSet();
                    }
                }

                play = false;
            }

            isPossible.put(key, doesContainImpossibleSet(colourSets));
            System.out.println(colourSets);
        }
        System.out.println(isPossible);
        System.out.println("Sum : " + possibleGames(isPossible));

    }

    public static boolean doesContainImpossibleSet(ArrayList<ColourSet> colourSets)
    {
        boolean possible;
        for (ColourSet colourSet : colourSets)
        {
            possible = colourSet.isPossible();
            if (!possible)
            {
                return false;
            }
        }
        return true;
    }

    public static int possibleGames(Map<Integer, Boolean> isPossible)
    {
        List<Integer> trueKeys = new ArrayList<>();

        for (Map.Entry<Integer, Boolean> entry : isPossible.entrySet())
        {
            if (entry.getValue())
            {
                trueKeys.add(entry.getKey());
            }
        }

        int sum = 0;
        for (int number : trueKeys)
        {
            sum += number;
        }
        System.out.println("Possible games : " + trueKeys);
        return sum;
    }
}
