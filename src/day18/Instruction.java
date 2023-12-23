package day18;

public class Instruction
{
    public String direction;
    public int noOfMeters;
    public String colourCode;

    @Override public String toString()
    {
        return "Instruction{" +
                        "direction='" + direction + '\'' +
                        ", noOfMeters=" + noOfMeters +
                        ", colourCode='" + colourCode + '\'' +
                        '}';
    }
}
