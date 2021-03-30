import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class ResizingArrayQueueOfStrings {
    private String[] items;
    private int n = 0;

    public ResizingArrayQueueOfStrings(int length) {
        this.items = new String[length];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return items.length;
    }

    private void resize(int max) {
        String[] temp = new String[max];
        for (int i = 0; i < this.n; i++) {
            temp[i] = this.items[i];

        }
        this.items = temp;
    }

    public void enqueue(String s) {
        if (this.n == this.items.length) {
            resize(this.items.length * 2);
        }

        this.items[this.n] = s;
        this.n++;


    }

    public String dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("empty");
        }

        String s = this.items[0];
        for (int i = 0; i < n - 1; i++) {
            items[i] = items[i + 1];
        }
        this.items[n - 1] = null;
        this.n--;

        if (this.n > 0 && this.n == this.items.length / 4) {
            resize(items.length / 2);
        }
        return s;
    }

    public String toString() {
        return Arrays.toString(items);
    }

    public static void main(String[] args) {
        ResizingArrayQueueOfStrings test = new ResizingArrayQueueOfStrings(3);
        test.enqueue("a");
        test.enqueue("b");
        test.enqueue("c");

        StdOut.println("size is " + test.size());
        StdOut.println(
                "1st dequeue  " + test.dequeue() + ", new size is " + test.size() + ", test is "
                        + test.toString());
        StdOut.println(
                "2nd dequeue  " + test.dequeue() + ", new size is " + test.size() + ", test is "
                        + test.toString());
        test.enqueue("d");
        StdOut.println(test.toString());
        test.enqueue("e");
        StdOut.println(test.toString());
        test.enqueue("f");
        StdOut.println(test.toString());
        test.enqueue("g");
        StdOut.println(test.toString());
        StdOut.println("size is " + test.size());
        StdOut.println("3rd dequeue  " + test.dequeue() + ", new size is " + test.size());
        StdOut.println("4th dequeue  " + test.dequeue() + ", new size is " + test.size());
        StdOut.println("5th dequeue  " + test.dequeue() + ", new size is " + test.size());
        StdOut.println("6th dequeue  " + test.dequeue() + ", new size is " + test.size());
        StdOut.println(test.toString());
    }
}
