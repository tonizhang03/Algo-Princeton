import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class FindKth {
    private static String FindKth(Stack<String> input, int k) {
        int count = 0;
        String s = "";
        while (count < k) {
            s = input.pop();
            count++;
        }
        return s;
    }

    public static void main(String[] args) {

        Stack<String> input = new Stack<String>();
        input.push("a");
        input.push("b");
        input.push("c");
        input.push("d");
        int k = Integer.parseInt(args[0]);
        StdOut.println(FindKth(input, k));

    }
}
