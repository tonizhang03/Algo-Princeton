import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
    }

    private Node first;
    private Node last;
    private int n;


    // construct an empty randomized queue
    public RandomizedQueue() {
        this.first = null;
        this.last = null;
        this.n = 0;

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return this.n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        else {
            Node oldLast = this.last;
            this.last = new Node();
            this.last.item = item;
            this.last.next = null;

            if (isEmpty()) {
                this.first = last;
            }
            else {
                oldLast.next = last;
            }
            this.n++;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        else {
            Node prev = new Node();
            prev.item = null;
            Node current = new Node();
            current.item = null;

            current.next = this.first;
            prev.next = current;
            Item item;
            int ran = StdRandom.uniform(this.n);

            // StdOut.println("ran is " + ran);

            if (n == 1 && this.first != null) {
                item = first.item;
                first = first.next;

            }
            else {

                for (int i = 0; i <= ran; i++) {
                    current = current.next;
                    prev = prev.next;
                }
                if (current.equals(this.first)) {    // removing the first node
                    first = first.next;
                }
                else if (current.equals(this.last)) {   // removing the last node
                    last = prev;
                    last.next = null;
                }
                else {                                  // removing a node in the middle
                    prev.next = current.next;
                }
                item = current.item;
            }

            // StdOut.println("prev is " + prev.item + ", current is " + current.item);

            this.n--;

            if (isEmpty()) {
                this.last = null;
            }

            return item;
        }
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        else {
            Node current = new Node();
            current.item = null;
            current.next = this.first;
            int ran = StdRandom.uniform(this.n);

            for (int i = 0; i <= ran; i++) {
                current = current.next;
            }

            return current.item;
        }
    }


    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {

        private int x;
        private final int[] a;

        public RandomIterator() {
            x = 0;
            a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = i;
            }
            StdRandom.shuffle(a);
        }

        private Node indexOf(int index) {
            Node current = new Node();
            current.item = null;
            current.next = first;

            for (int i = 0; i <= index; i++) {
                current = current.next;
            }

            return current;
        }

        public boolean hasNext() {
            return n != 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            else if (x >= n) {
                throw new NoSuchElementException();
            }
            else {
                Node node = indexOf(a[x]);
                x++;
                return node.item;
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // public String toString() {
    //     String string = "";
    //
    //     if (this.first != null && this.last != null) {
    //         string = "first: " + this.first.item + ", last: " + this.last.item + ", queue: ";
    //     }
    //
    //     for (Node current = first; current != null; current = current.next) {
    //         string = string + "->" + current.item.toString();
    //     }
    //     return string;
    // }

    // unit testing (required)
    public static void main(String[] args) {
        // RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        // rq.size();
        // rq.size();
        // rq.enqueue(187);
        // StdOut.println("1st print " + rq.toString());
        // rq.dequeue();
        // StdOut.println("2st print " + rq.toString());
        // rq.enqueue(317);
        // StdOut.println("3st print " + rq.toString());
        // rq.enqueue(458);
        // StdOut.println("4st print " + rq.toString());
        // StdOut.println("dequeue " + rq.dequeue());
        // StdOut.println("5st print " + rq.toString());


        RandomizedQueue<String> test = new RandomizedQueue<String>();
        test.enqueue("a");
        test.enqueue("b");
        test.enqueue("c");
        test.enqueue("d");
        test.enqueue("e");
        test.enqueue("f");


        StdOut.println("sample " + test.sample());
        StdOut.println("dequeue " + test.dequeue());
        StdOut.println("is empty: " + test.isEmpty());
        StdOut.println("size " + test.size());


        Iterator<String> i = test.iterator();
        StdOut.println("has next: " + test.iterator().hasNext());
        StdOut.println("next " + i.next() + i.next() + i.next() + i.next() + i.next());


        test.dequeue();
        test.dequeue();
        test.dequeue();
        test.dequeue();
        test.dequeue();
        StdOut.println("has next: " + test.iterator().hasNext());

    }
}
