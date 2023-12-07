package day4;

import util.ReadFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static day4.ScratchCards.constructMatchingNumbers;

public class ScratchCards2
{
    public static void main(String[] args)
    {
        String filePath = "/Users/manjula/Desktop/hackathon/adventofcode/src/day4/input";
        List<String> lines = ReadFile.readFileLines(filePath);
        HashMap<Integer, ArrayList<Integer>> allGames = new HashMap<>();

        for (String line : lines)
        {
            String[] splitArray = line.split("\\s*,\\s*|\\s+");
            ArrayList<String> cards = new ArrayList<>(Arrays.asList(splitArray));
            Integer cardNumber = Integer.valueOf(cards.get(1).substring(0, cards.get(1).length() - 1));

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

            ArrayList<Integer> matchingNumbers = constructMatchingNumbers(leftGames, rightGames);
            allGames.put(cardNumber, matchingNumbers);
        }
        System.out.println(allGames);
        constructGameTree(allGames);
    }

    public static ArrayList<Integer> constructGameTree(HashMap<Integer, ArrayList<Integer>> allGames)
    {
        ArrayList<Integer> gameNodes = new ArrayList<>();
        int nodes = 0;

        for (Map.Entry<Integer, ArrayList<Integer>> entry : allGames.entrySet())
        {
            System.out.println("**** From the root ****");
            System.out.println(entry);
            nodes = nodes + countNodes(entry, allGames);
            gameNodes.add(nodes);
        }
        System.out.println("Total nodes: "+ nodes);

        return gameNodes;

    }

    public static Integer countNodes(
                    Map.Entry<Integer, ArrayList<Integer>> game,
                    HashMap<Integer, ArrayList<Integer>> allGames)
    {
        int nodes = 0;
        nodes = copiedGames(game, allGames);
        System.out.println("Total nodes in sub tree: "+ nodes);

        return nodes;
    }

    public static Integer copiedGames(Map.Entry<Integer, ArrayList<Integer>> game, HashMap<Integer, ArrayList<Integer>> allGames)
    {
        ArrayList<Integer> copiedGamesKeys = new ArrayList<>();
        int nodes = 1;

        int key = game.getKey(); // Current game key
        ArrayList<Integer> value = game.getValue();// Current game value

        //System.out.println("Key: " + key + ", Value: " + value);

        //Construct the keys of the cards to copy
        for (int i = 0; i < value.size(); i++)
        {
            key = key + 1;
            copiedGamesKeys.add(key);
        }
        //System.out.println("copiedGamesKeys " + copiedGamesKeys);

        //Number of cards copied
        //nodes = copiedGamesKeys.size();

        if (copiedGamesKeys.isEmpty())
        {
            return nodes;
        }
        else
        {
            for (int i = 0; i < copiedGamesKeys.size(); i++)
            {
                int keyToFetch = copiedGamesKeys.get(i);
                Map.Entry<Integer, ArrayList<Integer>> entryToFetch = null;
                for (Map.Entry<Integer, ArrayList<Integer>> entry : allGames.entrySet()) {
                    if (entry.getKey() == keyToFetch) {
                        entryToFetch=entry;
                    }
                }
                //System.out.println("Nodes before " +nodes );
                System.out.println(entryToFetch);

                nodes= nodes + copiedGames(entryToFetch,allGames);
                //System.out.println("Nodes after " +nodes );
            }
        }

        return nodes;
    }
}
