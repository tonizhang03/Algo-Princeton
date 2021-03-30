/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class VisualCounter {
    private int maxOp;
    private double maxAbs;
    private int count = 0;

    public VisualCounter(int N, double max) {
        maxOp = N;
        maxAbs = max;
    }

    public void increment() {
        if (count <= maxOp) {
            count++;
        }
    }

    public void decrement() {
        if (Math.abs(count) <= maxOp)
            count--;
    }

    public int tally() {
        return Math.abs(count);

    }

    public void plot() {
        System.out.println(count);
    }
}


// Develop a class VisualCounter that allows both increment and decrement operations.
// Take two arguments N and max in the constructor,
// where N specifies the maximum number of operations
// and max specifies the maximum absolute value for the counter.
// As a side effect,create a plot showing the value of the counter each time
// its tally changes.
