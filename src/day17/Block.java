package day17;

import java.util.Objects;

public class Block
{
    public int x;
    public int y;
    public String enterDirection;
    public int heatLoss;
    public Block previous;

    public boolean visited;

    public Block(int x, int y, String enterDirection, int heatLoss, Block previous)
    {
        this.x = x;
        this.y = y;
        this.enterDirection = enterDirection;
        this.heatLoss = heatLoss;
        this.previous = previous;
        this.visited=false;
    }

    public Block(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override public String toString()
    {
        return "Block{" +
                        "x=" + x +
                        ", y=" + y +
                        ", enterDirection='" + enterDirection + '\'' +
                        ", heatLoss=" + heatLoss +
                        ", visited=" + visited +
                        '}';
    }

}
