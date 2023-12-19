package day17;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static day17.CoordinateMapper.getRightBottomCoordinates;
import static day17.CoordinateMapper.getValue;

public class ShortestPath
{
    static final ArrayList<int[]> heatLossMap = getHeatLossMap();
    static List<Block> visited = Collections.synchronizedList(new ArrayList<>());
    static List<Block> unvisited = Collections.synchronizedList(new ArrayList<>());
    static int[] bottomRight = getRightBottomCoordinates(heatLossMap);
    static int sum = 0;

    public static void main(String[] args)
    {
        Block startBlock = new Block(0, 0, "INITIAL", getValue(heatLossMap, 0, 0), null);
        startBlock.visited = true;
        visited.add(startBlock);
        createBlockMap(startBlock);
        outerLoop:
        while (true)
        {
            unvisited.removeAll(visited);
            for (Block b : visited)
            {
                if (b.x == bottomRight[0] && b.y == bottomRight[1])
                {
                    System.out.println("A: " + bottomRight[0]);
                    sum = countHeatLossTotal(b);
                    break outerLoop;
                }
            }
            for (Block b : unvisited)
            {
                if (b.x == bottomRight[0] && b.y == bottomRight[1])
                {
                    System.out.println("B: " + bottomRight[0]);
                    sum = countHeatLossTotal(b);
                    break outerLoop;
                }
            }

            List<Block> unvisitedCopy = new ArrayList<>(unvisited);

            boolean conditionMet = false;

            for (Block bb : unvisitedCopy)
            {
                conditionMet = createBlockMap(bb);
                if (conditionMet)
                {
                    break outerLoop;
                }
            }

        }

        System.out.println("****** " + visited);
        System.out.println("****** " + unvisited);
        System.out.println("****** " + sum);
    }

    public static boolean createBlockMap(final Block block)
    {
        String direction;
        int x;
        int y;
        if (block == null)
        {
            return true;
        }
        else
        {
            direction = block.enterDirection;
            x = block.x;
            y = block.y;
            block.visited = true;
            visited.add(block);
        }

        if (direction.equals("LEFT") || direction.equals("RIGHT") || direction.equals("INITIAL"))
        {
            for (int i = 0; i < 3; i++)
            {
                if (x - i >= 0)
                {
                    Block b = new Block(x - i, y, "UP", getValue(heatLossMap, x - i, y), block);
                    unvisited.add(b);
                    //System.out.println(b);
                    if (b.x == bottomRight[0] && b.y == bottomRight[1])
                    {
                        System.out.println("#### " + b);
                        sum = countHeatLossTotal(b);
                        return true;
                    }

                }
            }

            for (int i = 0; i < 3; i++)
            {
                if (x + i < heatLossMap.size())
                {
                    Block b = new Block(x + i, y, "DOWN", getValue(heatLossMap, x + i, y), block);
                    unvisited.add(b);
                    //System.out.println(b);
                    if (b.x == bottomRight[0] && b.y == bottomRight[1])
                    {
                        System.out.println("#### " + b);
                        sum = countHeatLossTotal(b);
                        return true;
                    }

                }
            }
        }

        if (direction.equals("UP") || direction.equals("DOWN") || direction.equals("INITIAL"))
        {
            for (int i = 0; i < 3; i++)
            {
                if (y - i >= 0)
                {
                    Block b = new Block(x, y - i, "LEFT", getValue(heatLossMap, x, y - i), block);
                    unvisited.add(b);
                    //System.out.println(b);
                    if (b.x == bottomRight[0] && b.y == bottomRight[1])
                    {
                        System.out.println("#### " + b);
                        sum = countHeatLossTotal(b);
                        return true;
                    }
                }
            }

            for (int i = 0; i < 3; i++)
            {
                if (y + i < heatLossMap.get(0).length)
                {
                    Block b = new Block(x, y + i, "RIGHT", getValue(heatLossMap, x, y + i), block);
                    unvisited.add(b);
                    //System.out.println(b);
                    if (b.x == bottomRight[0] && b.y == bottomRight[1])
                    {
                        System.out.println("#### " + b);
                        sum = countHeatLossTotal(b);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static ArrayList<int[]> getHeatLossMap()
    {
        String filePath = "src/day17/input";
        String input = null;
        try
        {
            input = new String(Files.readAllBytes(Paths.get(filePath)));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        String[] lines = input.split("\n");
        ArrayList<int[]> heatLossXYs = new ArrayList<>();

        for (String line : lines)
        {
            int[] numbersArray = new int[line.length()];

            for (int i = 0; i < line.length(); i++)
            {
                numbersArray[i] = Character.getNumericValue(line.charAt(i));
            }
            heatLossXYs.add(numbersArray);
        }

        // Print the heatLossMap
        for (int[] array : heatLossXYs)
        {
            System.out.println(Arrays.toString(array));
        }

        return heatLossXYs;
    }

    public static int countHeatLossTotal(Block block)
    {
        int heatLossTotal = 0;
        while (true)
        {
            Block previous = block.previous;
            heatLossTotal += previous.heatLoss;
            block = previous;
            if (block.x == 0 && block.y == 0)
            {
                return heatLossTotal;
            }
        }
    }
}
