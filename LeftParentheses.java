import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LeftParentheses {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<String>();
        Queue<String> nums = new Queue<String>();
        String print = "";

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("+")) {
                ops.push(s);
            }
            else if (s.equals("-")) {
                ops.push(s);
            }
            else if (s.equals("*")) {
                ops.push(s);
            }
            else if (s.equals("/")) {
                ops.push(s);
            }
            else if (s.equals(")")) {
                if (!nums.isEmpty()) {

                    String section = "(" + nums.dequeue() + ops.pop() + nums.dequeue() + ")";
                    if (!ops.isEmpty()) {
                        print = print + ops.pop() + section;
                    }
                    else {
                        print = print + section;
                    }
                    section = "";
                }
                else {
                    print = "(" + print + ")";
                }
            }
            else {
                nums.enqueue(s);
            }
        }
        StdOut.println(print);
    }
}
