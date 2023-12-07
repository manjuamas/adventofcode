package day3;

import util.ReadFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Character.isDigit;
import static util.CharacterUtil.isSymbol;
import static util.NumberUtil.countArray;

public class AdjacentSchematic
{
    public static void main(String[] args)
    {
        String filePath = "src/day3/input";
        List<String> lines = ReadFile.readFileLines(filePath);

        //runTest();
        List<Integer> numbersToAdd = checkQualified(lines);
        System.out.println(numbersToAdd);
        System.out.println(countArray(numbersToAdd));
    }

    public static void runTest()
    {
        String line1 = "..#567#..............";
        String line2 = ".44#1....364.........";
        String line3 = ".......5678#2....456.";
        //  top, bottom, left, right, topLeftDiagonal, topRightDiagonal, bottomLeftDiagonal, bottomRightDiagonal
        List<String> lines = Arrays.asList(line1, line2, line3);
        System.out.println(checkQualified(lines));
    }

    public static List<Integer> checkQualified(List<String> lines)
    {
        List<Integer> numbersToAdd = new ArrayList<>();
        boolean isNearSymbol = false;

        for (int i = 0; i < lines.size(); i++)
        {
            String line = lines.get(i);
            String prviousLine = null;
            String nextLine = null;

            if (i - 1 >= 0)
            {
                prviousLine = lines.get(i - 1);
            }
            if (i + 1 < lines.size())
            {
                nextLine = lines.get(i + 1);

            }

            List<Integer> numbersMatch = new ArrayList<>();
            String collectedNumber = new String();
            int startPos = -1;

            for (int j = 0; j < line.length(); j++)
            {
                if (isDigit(line.charAt(j)))
                {
                    if (startPos == -1)
                    {
                        startPos = j;
                    }

                    collectedNumber = collectedNumber + line.charAt(j);

                }
                else
                {
                    // System.out.println("collectedNumber : "+collectedNumber);
                    if (!collectedNumber.isEmpty())
                    {
                        isNearSymbol = nearSymbol(prviousLine, line, nextLine, collectedNumber, startPos);
                        if (isNearSymbol)
                        {
                            numbersMatch.add(Integer.valueOf(collectedNumber));
                        }
                    }
                    collectedNumber = new String();
                    startPos = -1;
                }

                if (j == line.length() - 1)
                {
                    numbersToAdd.addAll(numbersMatch);
                }
            }
        }

        return numbersToAdd;
    }

    public static boolean nearSymbol(String topLine, String middleLine, String bottomLine, String number, int startPos)
    {
        //System.out.println("startPos : " + startPos);
        char top = '0';
        char bottom = '0';
        char left = '0';
        char right = '0';
        char topLeftDiagonal = '0';
        char topRightDiagonal = '0';
        char bottomLeftDiagonal = '0';
        char bottomRightDiagonal = '0';

        //System.out.println("number " + number+ " pos" + startPos);
        //System.out.println("line 1 :" + topLine);
        //System.out.println("line 2 :" + middleLine);
        //System.out.println("line 3 :" + bottomLine);

        for (int characterPos = startPos; characterPos < (startPos +number.length()); characterPos++)
        {
            if (middleLine != null)
            {
                if (characterPos - 1 >= 0)
                {
                    left = middleLine.charAt(characterPos - 1);
                }
                if (characterPos + 1 < middleLine.length())
                {
                    right = middleLine.charAt(characterPos + 1);
                }
            }

            if (topLine != null)
            {
                if (characterPos - 1 >= 0)
                {
                    topLeftDiagonal = topLine.charAt(characterPos - 1);
                }
                if (characterPos + 1 < middleLine.length())
                {
                    topRightDiagonal = topLine.charAt(characterPos + 1);
                }

                top = topLine.charAt(characterPos);
            }

            if (bottomLine != null)
            {
                if (characterPos - 1 >= 0)
                {
                    bottomLeftDiagonal = bottomLine.charAt(characterPos - 1);
                }
                if (characterPos + 1 < middleLine.length())
                {
                    bottomRightDiagonal = bottomLine.charAt(characterPos + 1);
                }

                bottom = bottomLine.charAt(characterPos);
            }

            /*System.out.println("top "+top+
                                            " bottom " +bottom+
                                            " left " + left+
                                            " right "+ right+
                                            " topLeftDiagonal "+topLeftDiagonal+
                                            " topRightDiagonal "+topRightDiagonal+
                                            " bottomLeftDiagonal "+ bottomLeftDiagonal+
                                            " bottomRightDiagonal "+bottomRightDiagonal);*/

            char[] charArray = { top, bottom, left, right, topLeftDiagonal, topRightDiagonal, bottomLeftDiagonal, bottomRightDiagonal };

            System.out.println(charArray);
            for (char ch : charArray)
            {
                if (isSymbol(ch))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
