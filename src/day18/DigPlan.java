package day18;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static day18.InstructionParser.adjustMap;
import static day18.InstructionParser.readInstructionsFromFile;

public class DigPlan
{
    public static void main(String[] args)
    {
        List<Instruction> instructions = readInstructionsFromFile("src/day18/input");
        List<Point> trenchXYs = digOutTheTrench(instructions);
        trenchXYs=adjustMap(trenchXYs);
        System.out.println("# of trench squares: " + trenchXYs.size());
        //Print thr trench
        char[][] trench = printTrenchMap(trenchXYs);
        List<Point> trenchPlusInteriorXYs = digOutTheInterior(trenchXYs, trench);
        //Print the trench with interior
        printTrenchMap(trenchPlusInteriorXYs);
        System.out.println("# of trench plus interior squares: " + trenchPlusInteriorXYs.size());
    }

    public static List<Point> digOutTheTrench(final List<Instruction> instructions)
    {
        List<Point> trenchXYs = new ArrayList<>();
        int x = 0;
        int y = 0;

        for (Instruction instruction : instructions)
        {
            switch (instruction.direction)
            {
            case "R":
                for (int i = 0; i < instruction.noOfMeters; i++)
                {
                    y++;
                    trenchXYs.add(new Point(x, y));
                }
                break;
            case "L":
                for (int i = 0; i < instruction.noOfMeters; i++)
                {
                    y--;
                    trenchXYs.add(new Point(x, y));
                }
                break;
            case "U":
                for (int i = 0; i < instruction.noOfMeters; i++)
                {
                    x--;
                    trenchXYs.add(new Point(x, y));
                }
                break;
            case "D":
                for (int i = 0; i < instruction.noOfMeters; i++)
                {
                    x++;
                    trenchXYs.add(new Point(x, y));
                }
                break;
            }
        }
        return trenchXYs;
    }

    public static List<Point> digOutTheInterior(final List<Point> trenchXYs, final char[][] trenchMap)
    {
        List<Point> trenchPlusInteriorXYs = new ArrayList<>();
        Point dimensions = findDimensions(trenchXYs);
        int maxX = (int) dimensions.getX();
        int maxY = (int) dimensions.getY();
        boolean leftBoundFound = false;
        boolean rightBoundFound = false;
        boolean upBoundFound = false;
        boolean downBoundFound = false;

        for (int i = 0; i <= maxX; i++)
        {
            for (int j = 0; j <= maxY; j++)
            {
                if (trenchMap[i][j] != '#')
                {
                    // Check Up bounds
                    for (int k = i; k >= 0; k--)
                    {
                        if (trenchMap[k][j] == '#')
                        {
                            upBoundFound = true;
                            break;
                        }
                    }
                    // Check Down bounds
                    for (int k = i; k <= maxX; k++)
                    {
                        if (trenchMap[k][j] == '#')
                        {
                            downBoundFound = true;
                            break;
                        }
                    }
                    // Check Left bounds
                    for (int k = j; k >= 0; k--)
                    {
                        if (trenchMap[i][k] == '#')
                        {
                            leftBoundFound = true;
                            break;
                        }
                    }
                    // Check Right bounds
                    for (int k = j; k <= maxY; k++)
                    {
                        if (trenchMap[i][k] == '#')
                        {
                            rightBoundFound = true;
                            break;
                        }
                    }

                    if (leftBoundFound && rightBoundFound && upBoundFound && downBoundFound)
                    {
                        trenchPlusInteriorXYs.add(new Point(i, j));
                    }
                }
                else
                {
                    trenchPlusInteriorXYs.add(new Point(i, j));
                }
                leftBoundFound = false;
                rightBoundFound = false;
                upBoundFound = false;
                downBoundFound = false;
            }
            System.out.println();
        }
        return trenchPlusInteriorXYs;
    }

    public static char[][] printTrenchMap(List<Point> trenchXYs)
    {
        Point points = findDimensions(trenchXYs);
        int maxX = (int) points.getX();
        int maxY = (int) points.getY();

        for (Point point : trenchXYs)
        {
            maxX = Math.max(maxX, point.x);
            maxY = Math.max(maxY, point.y);
        }
        char[][] trenchMap = new char[maxX + 1][maxY + 1];

        for (Point point : trenchXYs)
        {
            trenchMap[point.x][point.y] = '#';
        }
        // Print the trench map
        for (int i = 0; i <= maxX; i++)
        {
            for (int j = 0; j <= maxY; j++)
            {
                System.out.print(trenchMap[i][j] == '#' ? '#' : '.'); // Use '.' for empty spaces
            }
            System.out.println();
        }

        return trenchMap;
    }

    public static Point findDimensions(List<Point> map)
    {
        int maxX = 0;
        int maxY = 0;
        for (Point point : map)
        {
            maxX = Math.max(maxX, point.x);
            maxY = Math.max(maxY, point.y);
        }
        return new Point(maxX, maxY);
    }
}
