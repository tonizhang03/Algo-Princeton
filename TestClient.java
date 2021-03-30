import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class TestClient {
    private double average(SequentialSearchST<String, Double> st, String[] input) {
        double total = 0;
        for (String s : input) {
            if (st.get(s) == null) {
                throw new IllegalArgumentException();
            }
            total += st.get(s);
        }
        return total / input.length;
    }

    public static void main(String[] args) {
        SequentialSearchST<String, Double> st;
        st = new SequentialSearchST<String, Double>();

        st.put("A+", 4.33);
        st.put("A", 4.00);
        st.put("A-", 3.67);
        st.put("B+", 3.33);
        st.put("B", 3.00);
        st.put("B-", 2.67);
        st.put("C+", 2.33);
        st.put("C", 2.00);
        st.put("C-", 1.67);
        st.put("D", 1.00);
        st.put("F", 0.00);


        String[] input = StdIn.readAllLines();
        double gpa = new TestClient().average(st, input);
        StdOut.printf("GPA: %.2f", gpa);

    }
}
