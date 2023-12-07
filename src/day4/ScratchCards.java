package day4;

import util.ReadFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static util.NumberUtil.countArray;

public class ScratchCards
{
    public static void main(String[] args)
    {
        String filePath = "/Users/manjula/Desktop/hackathon/adventofcode/src/day4/input";
        List<String> lines = ReadFile.readFileLines(filePath);
        ArrayList<ArrayList<Integer>> winningGames = new ArrayList<>();
        ArrayList<Integer> gamePoints= new ArrayList<>();

        for (String line : lines)
        {
            String[] splitArray = line.split("\\s*,\\s*|\\s+");
            ArrayList<String> cards = new ArrayList<>(Arrays.asList(splitArray));
            Integer cardNumber = Integer.valueOf(cards.get(1).substring(0, cards.get(1).length() - 1));
            System.out.println(cardNumber);

            boolean startFillingRightGames = false;
            ArrayList<Integer> leftGames = new ArrayList<>();
            ArrayList<Integer> rightGames = new ArrayList<>();

            for (int i = 2; i < cards.size(); i++)
            {
                if (cards.get(i).equals("|"))
                {
                    startFillingRightGames = true;
                    continue;
                }
                if (startFillingRightGames)
                {
                    rightGames.add(Integer.valueOf(cards.get(i)));
                }
                else
                {
                    leftGames.add(Integer.valueOf(cards.get(i)));
                }
            }
            System.out.println(line);
            System.out.println(leftGames);
            System.out.println(rightGames);

            ArrayList<Integer> matchingNumbers = constructMatchingNumbers(leftGames, rightGames);
            if (!matchingNumbers.isEmpty())
            {
                winningGames.add(matchingNumbers);
            }
        }
        System.out.println("Winning games " + winningGames);
        gamePoints =calculatePoints(winningGames);
        System.out.println("Game Points "+gamePoints);
        System.out.println("Total Points "+countArray(gamePoints));
    }

    public static ArrayList<Integer> constructMatchingNumbers(ArrayList<Integer> leftGames, ArrayList<Integer> rightGames)
    {
        ArrayList<Integer> matchingNumbers = new ArrayList<>();

        for (Integer leftNumber : leftGames)
        {
            if (rightGames.contains(leftNumber))
            {
                matchingNumbers.add(leftNumber);
            }
        }

        return matchingNumbers;
    }

    public static ArrayList<Integer> calculatePoints(ArrayList<ArrayList<Integer>> winningGames)
    {
        ArrayList<Integer> pointsArray = new ArrayList<>();
        for (ArrayList<Integer> games : winningGames)
        {
            int previous =0;
            int next=0;
            for (int i = 0; i < games.size(); i++)
            {
                if(i==0){
                    next =1;
                    previous=next;
                }
                else if(i==1){
                    next=2;
                    previous=next;
                }
                else
                {
                    next = previous * 2;
                    previous = next;
                }
            }
            pointsArray.add(next);
        }

        System.out.println(pointsArray);
        return pointsArray;
    }
}
