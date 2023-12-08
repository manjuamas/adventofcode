package day7;

public class GameBid
{
    String word;
    int bid;

    public GameBid(String word, int bid)
    {
        this.word = word;
        this.bid = bid;
    }

    public GameBid(String line)
    {
        String[] parts = line.split("\\s+");

        if (parts.length == 2)
        {
            this.word = parts[0];
            this.bid = Integer.parseInt(parts[1]);
        }
        else
        {
            System.err.println("Invalid input: " + line);
        }
    }

    public String getWord()
    {
        return word;
    }

    public int getBid()
    {
        return bid;
    }

    public void setWord(String word)
    {
        this.word = word;
    }

    @Override public String toString()
    {
        return "GameBid{" +
                        "word='" + word + '\'' +
                        ", bid=" + bid +
                        '}';
    }
}
