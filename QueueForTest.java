import java.util.Iterator;

public class QueueForTest<Item>
        implements Iterable<Item> {

    private class Node {
        Item item;
        Node next;
    }

    // create variables:
    private Node first;  // top of queue
    private Node last;   // end of queue
    private int N;       // number of items

    // isEmpty
    public boolean isEmpty() {
        return this.first == null;
    }

    // size
    public int size() {
        return this.N;
    }

    // push - different from Stack
    public void enqueue(Item item) {   // insert a Node at the end:
        // save a link to the last node, create a new node for the end, link new node to the end of the list
        Node oldLast = this.last;
        this.last = new Node();
        this.last.item = item;
        this.last.next = null;
        if (isEmpty()) {              // check if the stack is empty, so that first != null when last is that new Node
            this.first = last;
        }
        else {
            oldLast.next = last;
        }
        N++;
    }

    // pop - same as Stack
    public Item dequeue() {      // remove a Node at the top/beginning:
        Item item = this.first.item;
        this.first = this.first.next;
        this.N--;
        if (isEmpty()) {        // when first = null, check last
            this.last = null;
        }
        return item;     // the value of the removed Node
    }

    // provide an iterator() method:
    public Iterator<Item> iterator() {
        return new ListIterator(); // we promise to provide a nested class "ListIterator"
    }

    // provide a nested class ListIterator, or use default, that must implements the hasNext() and next() methods
    private class ListIterator implements Iterator<Item> {
        private Node current = first;

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
