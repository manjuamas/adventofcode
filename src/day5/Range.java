package day5;

public class Range
{
    Long destinationRangeStart;
    Long sourceRangeStart;
    Long rangeLength;

    public Long getDestinationRangeStart()
    {
        return destinationRangeStart;
    }

    public Long getSourceRangeStart()
    {
        return sourceRangeStart;
    }

    public Long getRangeLength()
    {
        return rangeLength;
    }

    @Override public String toString()
    {
        return "Range{" +
                        "destinationRangeStart=" + destinationRangeStart +
                        ", sourceRangeStart=" + sourceRangeStart +
                        ", rangeLength=" + rangeLength +
                        '}';
    }

    public Range(Long destinationRangeStart, Long sourceRangeStart, Long rangeLength)
    {
        this.destinationRangeStart = destinationRangeStart;
        this.sourceRangeStart = sourceRangeStart;
        this.rangeLength = rangeLength;
    }
}
