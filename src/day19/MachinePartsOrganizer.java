package day19;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static day19.MachineParts.sumOfMachineParts;
import static day19.WorkflowParser.createMachinePartsList;
import static day19.WorkflowParser.readEachWorkflow;
import static day19.WorkflowParser.readMachinePartsLines;
import static day19.WorkflowParser.readWorkflowsLines;

public class MachinePartsOrganizer
{
    static List<MachineParts> acceptedList = new ArrayList<>();
    static List<MachineParts> rejectList = new ArrayList<>();

    public static void main(String[] args)
    {
        String filePath = "src/day19/input";
        String input;
        try
        {
            input = readWorkflowsLines(filePath);
        }
        catch (IOException e)
        {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }
        Map<String, List<Rule>> workflows = readEachWorkflow(input);

        System.out.println(workflows);

        List<String> machinePartsLines = readMachinePartsLines(filePath);
        List<MachineParts> machinePartsList = createMachinePartsList(machinePartsLines);
        System.out.println(machinePartsList);
        organizeMachineParts(workflows, machinePartsList);
        System.out.println("Accepted parts gives the sum total: "+ sumOfMachineParts(acceptedList));

    }

    public static void organizeMachineParts(Map<String, List<Rule>> workflows, List<MachineParts> machinePartsLis)
    {
        for (MachineParts machineParts : machinePartsLis)
        {
            String nextWorkflow = "in";
            outerLoop:
            while (true)
            {
                List<Rule> rules = workflows.get(nextWorkflow);
                if (nextWorkflow.equals("R"))
                {
                    rejectList.add(machineParts);
                    break outerLoop;
                }
                if (nextWorkflow.equals("A"))
                {
                    acceptedList.add(machineParts);
                    break outerLoop;
                }
                for (Rule rule : rules)
                {
                    if (ruleApplies(rule, "x", machineParts.x) != null)
                    {
                        nextWorkflow = rule.nextWorkflow;
                        break;
                    }
                    else if (ruleApplies(rule, "m", machineParts.m) != null)
                    {
                        nextWorkflow = rule.nextWorkflow;
                        break;
                    }
                    else if (ruleApplies(rule, "a", machineParts.a) != null)
                    {
                        nextWorkflow = rule.nextWorkflow;
                        break;
                    }
                    else if (ruleApplies(rule, "s", machineParts.s) != null)
                    {
                        nextWorkflow = rule.nextWorkflow;
                        break;
                    }
                }
            }
        }
    }

    private static String ruleApplies(Rule rule, String partName, int value)
    {
        if (rule.partName == null)
        {
            return rule.nextWorkflow;
        }
        if (rule.partName.equals(partName))
        {
            if (rule.operation.equals("<") && value < rule.value)
            {
                return rule.nextWorkflow;
            }
            else if (rule.operation.equals(">") && value > rule.value)
            {
                return rule.nextWorkflow;
            }
        }
        return null;
    }

}
