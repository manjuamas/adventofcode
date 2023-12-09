package day8;

import util.ReadFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapNetwork
{
    public static void main(String[] args)
    {
        String filePath = "src/day8/input";
        List<String> lines = ReadFile.readFileLines(filePath);
        String instructionsString = "";
        List<Node> nodes = new ArrayList<>();

        for (String line : lines)
        {
            if (line.contains("="))
            {
                nodes.add(new Node(line));
            }
            else
            {
                instructionsString += line;
            }
        }

        String[] instructions = instructionsString.split("");
        System.out.println(instructionsString);
        Map<String, List<String>> network = createNetwork(nodes);

        String nextStep = "";
        int count = 0;
        boolean reachedZZZ = false;

        if (!nodes.isEmpty())
        {
            nextStep = nodes.get(0).getNode();
        }
        while (!reachedZZZ)
        {
            System.out.println("again!");
            for (int i = 0; i < instructions.length; i++)
            {
                if (nextStep.equals("ZZZ"))
                {
                    reachedZZZ = true;
                    break;
                }
                if (instructions[i].equals("R"))
                {
                    nextStep = network.get(nextStep).get(1);
                }
                if (instructions[i].equals("L"))
                {
                    nextStep = network.get(nextStep).get(0);
                }
                count += 1;
                //System.out.println("nextStep - " + nextStep);
            }
        }
        System.out.println("Answer- " + count);
    }

    public static Map<String, List<String>> createNetwork(List<Node> nodes)
    {
        Map<String, List<String>> network = new HashMap<>();

        for (Node node : nodes)
        {
            network.put(node.node, Arrays.asList(node.getLeft(), node.getRight()));
        }
        return network;
    }

}
