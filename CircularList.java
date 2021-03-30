import java.util.Iterator;

public class CircularList<Item> implements Iterable<Item> {

    private class Node {
        Item item;
        Node next;
    }

    private Node last;
    private int N;

    public boolean isEmpty() {
        return this.last == null;
    }

    public int size() {
        return this.N;
    }

    public void enqueue(Item item) {
        if (isEmpty()) {
            this.last = new Node();
            this.last.item = item;
            this.last.next = last;
            this.N++;
        }
        else if (this.N == 1) {
            Node oldLast = this.last;
            this.last = new Node();
            this.last.item = item;
            this.last.next = oldLast;
            oldLast.next = last;
            N++;
        }
        else {
            Node oldLast = this.last;
            this.last = new Node();
            this.last.item = item;
            this.last.next = oldLast.next;
            oldLast.next = last;
            N++;
        }
    }

    public Item dequeue() {
        if (!isEmpty()) {
            Item item = this.last.next.item;
            this.last.next = this.last.next.next;
            this.N--;
            return item;
        }
        else {
            throw new IllegalArgumentException("empty");
        }
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = last;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
