package day17;

import java.util.ArrayList;

public class CoordinateMapper
{
    public static int getValue(ArrayList<int[]> heatLossXYs, int x, int y)
    {
        // Check if x and y are within valid bounds
        if (x >= 0 && x < heatLossXYs.size() && y >= 0 && y < heatLossXYs.get(0).length)
        {
            return heatLossXYs.get(x)[y];
        }
        else
        {
            // Handle the out-of-bounds
            return -1;
        }
    }

    public static int[] getRightBottomCoordinates(ArrayList<int[]> heatLossXYs)
    {
        int rows = heatLossXYs.size();

        if (rows == 0)
        {
            // Handle empty list
            return new int[] { -1, -1 };
        }

        int cols = heatLossXYs.get(0).length;

        if (cols == 0)
        {
            // Handle empty list
            return new int[] { -1, -1 };
        }

        int rightBottomX = rows - 1;
        int rightBottomY = cols - 1;

        System.out.println(rightBottomX);
        System.out.println(rightBottomY);

        return new int[] { rightBottomX, rightBottomY };
    }
}
