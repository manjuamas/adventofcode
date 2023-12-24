package day19;

public class Rule
{
    public String partName;
    public String operation;
    public int value;
    public String nextWorkflow;

    public Rule(String partName, String operation, int value, String nextWorkflow)
    {
        this.partName = partName;
        this.operation = operation;
        this.value = value;
        this.nextWorkflow = nextWorkflow;
    }

    @Override
    public String toString()
    {
        return "Rule{" +
                        "partName='" + partName + '\'' +
                        ", operation='" + operation + '\'' +
                        ", value=" + value +
                        ", nextWorkflow='" + nextWorkflow + '\'' +
                        '}';
    }
}
