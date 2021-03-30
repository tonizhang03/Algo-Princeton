import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {
    private class DoubleNode {
        T item;
        DoubleNode next;
        DoubleNode prev;
    }

    private DoubleNode first;
    private DoubleNode last;
    private int n;

    public boolean isEmpty() {
        return this.n == 0;
    }

    public void insertBeginning(T item) {
        if (isEmpty()) {
            this.first = new DoubleNode();
            this.first.item = item;
            this.last = null;
            this.n++;
        }
        else if (this.n == 1) {
            DoubleNode oldFirst = this.first;
            this.first = new DoubleNode();
            this.first.item = item;
            this.first.next = oldFirst;
            oldFirst.prev = this.first;
            this.last = oldFirst;
            this.n++;
        }
        else {
            DoubleNode oldFirst = this.first;
            this.first = new DoubleNode();
            this.first.item = item;
            this.first.next = oldFirst;
            oldFirst.prev = this.first;
            this.n++;
        }
    }

    public void insertEnd(T item) {
        if (isEmpty()) {
            this.last = new DoubleNode();
            this.last.item = item;
            this.first = null;
            this.n++;
        }
        else if (this.n == 1) {
            DoubleNode oldLast = this.last;
            this.last = new DoubleNode();
            this.last.item = item;
            this.last.prev = oldLast;
            oldLast.next = this.last;
            this.first = oldLast;
            this.n++;
        }
        else {
            DoubleNode oldLast = this.last;
            this.last = new DoubleNode();
            this.last.item = item;
            this.last.prev = oldLast;
            oldLast.next = this.last;
            this.n++;
        }
    }

    public T removeBeginning() {
        if (isEmpty()) {
            throw new IllegalArgumentException("empty");
        }
        else {
            T item = this.first.item;

            this.first = this.first.next;
            this.n--;

            return item;
        }

    }

    public T removeEnd() {
        if (isEmpty()) {
            throw new IllegalArgumentException("empty");
        }
        else {
            T item = this.last.item;

            this.last = this.last.prev;
            this.n--;

            return item;
        }
    }

    public void insertBefore(DoubleNode a, DoubleNode b) { // insert b before a
        if (a != null && b != null) {
            b.next = a;
            b.prev = a.prev;
            a.prev.next = b;
            a.prev = b;
            this.n++;
        }
    }

    public void insertAfter(DoubleNode a, DoubleNode b) { // insert b after a
        if (a != null && b != null) {
            b.prev = a;
            b.next = a.next;
            a.next.prev = b;
            a.next = b;
            this.n++;
        }
    }

    public void removeNode(DoubleNode x) {
        if (isEmpty() || x == null) {
            throw new IllegalArgumentException();
        }
        else {
            DoubleNode temp = null;
            x.prev.next = x.next;
            x.next.prev = x.prev;
            x = temp;
        }
    }


    public Iterator<T> iterator() {
        return new ListIterator(); // we promise to provide a nested class "ListIterator"
    }

    // provide a nested class ListIterator, or use default, that must implements the hasNext() and next() methods
    private class ListIterator implements Iterator<T> {
        private DoubleNode current = first;

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            T item = current.item;

            current = current.next;

            return item;
        }
    }
}
