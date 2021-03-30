import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Exercise1312 {
    private static Stack<String> copy(Stack<String> input) {
        Stack<String> c = new Stack<String>();
        Iterator<String> i = input.iterator();
        while (i.hasNext()) {
            c.push(i.next());
        }
        return c;
    }

    public static void main(String[] args) {
        Stack<String> test = new Stack<>();
        test.push("first");
        test.push("second");
        test.push("third");

        Stack<String> result = copy(test);
        for (String s : result) {
            StdOut.println(s);
        }
    }
}
