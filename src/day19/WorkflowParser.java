package day19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorkflowParser
{
    static String readWorkflowsLines(String filePath) throws IOException
    {
        Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path);
        StringBuilder content = new StringBuilder();

        for (String line : lines)
        {
            content.append(line).append("\n");

            // Stop reading if a blank line is encountered
            if (line.trim().isEmpty())
            {
                break;
            }
        }

        return content.toString();
    }

    public static Map<String, List<Rule>> readEachWorkflow(String input)
    {
        Map<String, List<Rule>> workflows = new HashMap<>();
        Pattern pattern = Pattern.compile("(\\w+)\\{(.*?)\\}");
        String[] lines = input.split("\n");
        for (String line : lines)
        {
            Matcher matcher = pattern.matcher(line);

            if (matcher.find())
            {
                String workflowName = matcher.group(1);
                String rulesStr = matcher.group(2);
                List<Rule> rules = splitRules(rulesStr);
                workflows.put(workflowName, rules);
            }
        }

        return workflows;
    }

    private static List<Rule> splitRules(String rulesStr)
    {
        // Split the rules by comma and create Rule objects
        String[] ruleArray = rulesStr.split(",");
        List<Rule> rules = new ArrayList<>();

        for (String ruleStr : ruleArray)
        {
            rules.add(parseRule(ruleStr.trim()));
        }

        return rules;
    }

    private static Rule parseRule(String ruleStr)
    {
        // Define the rule pattern
        Pattern rulePattern = Pattern.compile("([a-zA-Z]+)([<>]=?)(\\d+):([a-zA-Z]+)?");

        Matcher matcher = rulePattern.matcher(ruleStr);

        String nextWorkflow = null;
        if (matcher.matches())
        {
            String partName = matcher.group(1);
            String operation = matcher.group(2);
            int value = Integer.parseInt(matcher.group(3));
            nextWorkflow = matcher.group(4);

            return new Rule(partName, operation, value, nextWorkflow);
        }
        else
        {
            return new Rule(null, null, 0, ruleStr);
        }
    }

    static List<String> readMachinePartsLines(String filePath)
    {
        List<String> machinePartsLines = new ArrayList<>();
        boolean reachedBlankLine = false;

        try
        {
            Path path = Paths.get(filePath);
            List<String> allLines = Files.readAllLines(path);

            for (String line : allLines)
            {
                // Ignore lines before the blank line
                if (!reachedBlankLine && line.trim().isEmpty())
                {
                    reachedBlankLine = true;
                    continue;
                }

                // Read lines after the blank line
                if (reachedBlankLine)
                {
                    machinePartsLines.add(line.trim());
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return machinePartsLines;
    }

    static List<MachineParts> createMachinePartsList(List<String> machinePartsLines)
    {
        List<MachineParts> machinePartsList = new ArrayList<>();

        for (String line : machinePartsLines)
        {
            MachineParts machineParts = createMachinePartsFromLine(line);
            machinePartsList.add(machineParts);
        }

        return machinePartsList;
    }

    private static MachineParts createMachinePartsFromLine(String line)
    {
        Pattern pattern = Pattern.compile("(\\w+)=(\\d+)");
        Matcher matcher = pattern.matcher(line);

        MachineParts machineParts = new MachineParts();

        while (matcher.find())
        {
            String partName = matcher.group(1);
            int value = Integer.parseInt(matcher.group(2));

            switch (partName)
            {
            case "x":
                machineParts.x = value;
                break;
            case "m":
                machineParts.m = value;
                break;
            case "a":
                machineParts.a = value;
                break;
            case "s":
                machineParts.s = value;
                break;
            }
        }
        return machineParts;
    }
}
