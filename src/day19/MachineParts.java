package day19;

import java.util.List;

public class MachineParts
{
    public int x;
    public int m;
    public int a;
    public int s;

    @Override
    public String toString()
    {
        return "MachineParts{" +
                        "x=" + x +
                        ", m=" + m +
                        ", a=" + a +
                        ", s=" + s +
                        '}';
    }

    static int sumOfMachineParts(List<MachineParts> machinePartsList)
    {
        int sum = 0;
        for (MachineParts machineParts : machinePartsList)
        {
            sum += machineParts.x + machineParts.m + machineParts.a + machineParts.s;
        }
        return sum;
    }

}
