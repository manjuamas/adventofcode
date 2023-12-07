package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile
{
    public   static  List<String> readFileLines(String filePath)
    {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                lines.add(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return lines;
    }
}
