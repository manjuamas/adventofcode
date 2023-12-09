package day8;

public class Node
{
    String node;
    String left;
    String right;

    public Node(String node, String left, String right)
    {
        this.node = node;
        this.left = left;
        this.right = right;
    }

    public Node(String input)
    {
        String[] parts = input.split("=");

        if (parts.length == 2)
        {
            this.node = parts[0].trim();
            String[] children = parts[1].split(",");

            if (children.length == 2)
            {
                this.left = children[0].trim().replace("(", "");
                right = children[1].trim().replace(")", "");
            }
        }
    }

    public String getNode()
    {
        return node;
    }

    public String getLeft()
    {
        return left;
    }

    public String getRight()
    {
        return right;
    }

    @Override public String toString()
    {
        return "Node{" +
                        "node='" + node + '\'' +
                        ", left='" + left + '\'' +
                        ", right='" + right + '\'' +
                        '}';
    }
}
