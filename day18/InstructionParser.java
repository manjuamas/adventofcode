package day18;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InstructionParser
{
    public static List<Instruction> readInstructionsFromFile(String filePath)
    {
        List<Instruction> instructions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                Instruction instruction = parseInstruction(line);
                instructions.add(instruction);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return instructions;
    }

    public static Instruction parseInstruction(String line)
    {
        String[] parts = line.split("\\s+");

        Instruction instruction = new Instruction();
        instruction.direction = parts[0];
        instruction.noOfMeters = Integer.parseInt(parts[1]);
        instruction.colourCode = parts[2].substring(1, parts[2].length() - 1);

        return instruction;
    }

    public static List<Point> adjustMap(List<Point> originalMap)
    {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        // Find the minimum x and y coordinates
        for (Point point : originalMap)
        {
            minX = Math.min(minX, point.x);
            minY = Math.min(minY, point.y);
        }

        // Adjust the map by subtracting the minimum coordinates
        List<Point> adjustedMap = new ArrayList<>();
        for (Point point : originalMap)
        {
            int adjustedX = point.x - minX;
            int adjustedY = point.y - minY;
            adjustedMap.add(new Point(adjustedX, adjustedY));
        }

        return adjustedMap;
    }

}

