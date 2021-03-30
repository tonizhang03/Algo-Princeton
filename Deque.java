import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class DoubleNode {
        Item item;
        DoubleNode next;
        DoubleNode prev;
    }

    private DoubleNode first;
    private DoubleNode last;
    private int n;


    // construct an empty deque
    public Deque() {
        this.first = null;
        this.last = null;
        this.n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        else if (isEmpty()) {
            this.first = new DoubleNode();
            this.first.item = item;
            this.first.prev = null;
            this.first.next = null;
            this.last = this.first;
            this.n++;
        }
        else {
            DoubleNode oldFirst = this.first;
            this.first = new DoubleNode();
            this.first.item = item;
            this.first.next = oldFirst;
            this.first.prev = null;
            oldFirst.prev = this.first;
            this.n++;
        }

    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        else if (isEmpty()) {
            this.last = new DoubleNode();
            this.last.item = item;
            this.first = this.last;
            this.n++;
        }
        else {
            DoubleNode oldLast = this.last;
            this.last = new DoubleNode();
            this.last.item = item;
            this.last.prev = oldLast;
            this.last.next = null;
            oldLast.next = this.last;
            this.n++;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        else {
            Item item = this.first.item;

            this.first = this.first.next;
            this.n--;
            if (isEmpty()) {
                this.last = null;
            }
            return item;
        }
    }

    // remove and return the item from the back
    public Item removeLast() {

        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        else {
            Item item = this.last.item;
            this.last = this.last.prev;

            this.n--;
            
            if (this.last != null) {
                this.last.next = null;
            }
            if (isEmpty()) {
                this.first = null;
            }

            return item;
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private DoubleNode current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            else {
                Item item = current.item;

                current = current.next;

                return item;
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // public String toString() {
    //     String string = "first: " + this.first.item + ", last: " + this.last.item + " ";
    //     for (DoubleNode current = first; current != null; current = current.next) {
    //         string = string + "->" + current.item.toString();
    //     }
    //     return string;
    // }


    // unit testing (required)
    public static void main(String[] args) {
        // Deque<Integer> deque = new Deque<Integer>();
        // deque.addFirst(1);
        // StdOut.println(deque.toString());
        // deque.addFirst(2);
        // StdOut.println(deque.toString());
        // deque.removeFirst();
        // StdOut.println(deque.toString());
        // deque.addFirst(4);
        // StdOut.println(deque.toString());
        // deque.addLast(5);
        // StdOut.println(deque.toString());
        // StdOut.println(deque.toString());
        // deque.removeFirst();
        // StdOut.println(deque.toString());
        // deque.addLast(7);
        // StdOut.println(deque.toString());
        // deque.addLast(8);
        // StdOut.println(deque.toString());
        // deque.removeFirst();
        // StdOut.println(deque.toString());
        // deque.removeFirst();
        // StdOut.println(deque.toString());
        // deque.removeLast();
        // StdOut.println(deque.toString());

        Deque<String> test = new Deque<String>();
        test.addFirst("a");
        test.addFirst("b");
        test.addLast("z");
        test.addLast("y");
        StdOut.println(test.isEmpty());
        StdOut.println(test.size());
        StdOut.println(test.removeFirst());
        StdOut.println(test.removeLast());
        StdOut.println(test.iterator().hasNext());
        StdOut.println(test.iterator().next());
        test.iterator().remove();
    }
}
