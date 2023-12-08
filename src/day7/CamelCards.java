package day7;

import util.ReadFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static day7.DoubleCustomSort.doubleCustomSort;
import static util.CharacterUtil.countCharacters;

public class CamelCards
{
    public static void main(String[] args)
    {
        String filePath = "src/day7/input";
        List<String> lines = ReadFile.readFileLines(filePath);
        List<GameBid> gameBidList = new ArrayList<>();
        List<GameBid> semiSortedGmeBidList;
        List<GameBid> sortedGmeBidList = new ArrayList<>();

        List<GameBid> fiveOfAKind = new ArrayList<>();//All five cards have the same label: AAAAA
        List<GameBid> fourOfAKind = new ArrayList<>();//Four cards have the same label and one card has a different label: AA8AA
        List<GameBid> fullHouse =
                        new ArrayList<>();//Three cards have the same label, and the remaining two cards share a different label: 23332
        List<GameBid> threeOfAKind =
                        new ArrayList<>();//Three cards have the same label, and the remaining two cards are each different from any other card in the hand: TTT98
        List<GameBid> twoPair =
                        new ArrayList<>();//Two cards share one label, two other cards share a second label, and the remaining card has a third label: 23432
        List<GameBid> onePair =
                        new ArrayList<>();//Two cards share one label, and the other three cards have a different label from the pair and each other: A23A4
        List<GameBid> highCard = new ArrayList<>();//All cards' labels are distinct: 23456

        for (String line : lines)
        {
            gameBidList.add(new GameBid(line));
        }
        semiSortedGmeBidList = doubleCustomSort(gameBidList);

        for (GameBid gameBid : semiSortedGmeBidList)
        {
            Map<Character, Integer> characterCounts = countCharacters(gameBid.getWord());

            if (isAFiveOfAKind(characterCounts))
            {
                fiveOfAKind.add(gameBid);
            }
            else if (isAFourOfAKind(characterCounts))
            {
                fourOfAKind.add(gameBid);
            }
            else if (isAFullHouse(characterCounts))
            {
                fullHouse.add(gameBid);
            }
            else if (isAThreeOfAKind(characterCounts))
            {
                threeOfAKind.add(gameBid);
            }
            else if (isATwoPair(characterCounts))
            {
                twoPair.add(gameBid);
            }
            else if (isAOnePair(characterCounts))
            {
                onePair.add(gameBid);
            }
            else
            {
                highCard.add(gameBid);
            }
        }
        System.out.println("fiveOfAKind " + fiveOfAKind);
        System.out.println("fourOfAKind " + fourOfAKind);
        System.out.println("fullHouse " + fullHouse);
        System.out.println("threeOfAKind " + threeOfAKind);
        System.out.println("twoPair " + twoPair);
        System.out.println("onePair " + onePair);
        System.out.println("highCard " + highCard);

        sortedGmeBidList.addAll(fiveOfAKind);
        sortedGmeBidList.addAll(fourOfAKind);
        sortedGmeBidList.addAll(fullHouse);
        sortedGmeBidList.addAll(threeOfAKind);
        sortedGmeBidList.addAll(twoPair);
        sortedGmeBidList.addAll(onePair);
        sortedGmeBidList.addAll(highCard);

        System.out.println("sortedGmeBidList: " + sortedGmeBidList);
        int sum = countThemAll(sortedGmeBidList);
        System.out.println("Answer: " + sum);

    }

    public static boolean isAFiveOfAKind(Map<Character, Integer> characterCounts)
    {
        for (Map.Entry<Character, Integer> entry : characterCounts.entrySet())
        {
            if (entry.getValue() == 5)
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isAFourOfAKind(Map<Character, Integer> characterCounts)
    {
        for (Map.Entry<Character, Integer> entry : characterCounts.entrySet())
        {
            if (entry.getValue() == 4)
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isAFullHouse(Map<Character, Integer> characterCounts)
    {
        boolean foundA3 = false;
        boolean foundA2 = false;

        for (Map.Entry<Character, Integer> entry : characterCounts.entrySet())
        {
            if (entry.getValue() == 3)
            {
                foundA3 = true;
            }
            if (entry.getValue() == 2)
            {
                foundA2 = true;
            }
        }
        if (foundA3 && foundA2)
        {
            return true;
        }
        return false;
    }

    public static boolean isAThreeOfAKind(Map<Character, Integer> characterCounts)
    {
        for (Map.Entry<Character, Integer> entry : characterCounts.entrySet())
        {
            if (entry.getValue() == 3)
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isATwoPair(Map<Character, Integer> characterCounts)
    {
        int count = 0;
        for (Map.Entry<Character, Integer> entry : characterCounts.entrySet())
        {
            if (entry.getValue() == 2)
            {
                count = count + 1;
            }
        }
        if (count == 2)
        {
            return true;
        }
        return false;
    }

    public static boolean isAOnePair(Map<Character, Integer> characterCounts)
    {
        int count = 0;
        for (Map.Entry<Character, Integer> entry : characterCounts.entrySet())
        {
            if (entry.getValue() == 2)
            {
                count = count + 1;
            }
        }
        if (count == 1)
        {
            return true;
        }
        return false;
    }

    public static int countThemAll(List<GameBid> gameBids)
    {
        int sum = 0;
        int incrementer = gameBids.size();
        for (int i = 0; i < gameBids.size(); i++)
        {
            sum += gameBids.get(i).getBid() * incrementer;
            incrementer--;
        }
        return sum;
    }

}
