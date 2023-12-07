package day2;

public class ColourSet
{
    public int reds;
    public int greens;
    public int blues;

    public int getReds()
    {
        return reds;
    }

    public void setReds(int reds)
    {
        this.reds =  this.reds  +reds;
    }

    public int getGreens()
    {
        return greens;
    }

    public void setGreens(int greens)
    {
        this.greens = this.greens + greens;
    }

    public int getBlues()
    {
        return blues;
    }

    public void setBlues(int blues)
    {
        this.blues = this.blues + blues;
    }

    public boolean isPossible(){

       // 12 red cubes, 13 green cubes, and 14 blue cubes
        if (getReds() > 12)
        {
            return false;
        }
        if (getBlues() > 14)
        {
            return false;
        }
        if (getReds() > 13)
        {
            return false;
        }
        return true;
    }

    @Override public String toString()
    {
        return "{reds=" + reds +
                        ", greens=" + greens +
                        ", blues=" + blues +
                        '}';
    }
}
