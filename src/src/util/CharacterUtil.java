package util;

import java.util.ArrayList;
import java.util.List;

public class CharacterUtil
{
    public static boolean isDigit(char character)
    {
        return Character.isDigit(character);
    }

    public static boolean isSymbol(char character)
    {
        boolean isDigit = Character.isDigit(character);
        if (isDigit)
        {
            return false;
        }
        else if (character == '.')
        {
            return false;
        }
        return true;
    }

    public static List<String> removeBlankLines(List<String> lines)
    {
        List<String> noBlanks = new ArrayList<>();

        for (String line : lines)
        {
            if (!line.isEmpty())
            {
                noBlanks.add(line);
            }
        }
        return noBlanks;
    }
}
