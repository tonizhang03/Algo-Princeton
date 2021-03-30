import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
    private class Node {
        T item;
        Node next;
    }

    private Node first;
    private int n;

    public void push(T item) {

        Node oldFirst = this.first;
        this.first = new Node();
        this.first.item = item;
        this.first.next = oldFirst;
        this.n++;
    }

    public T pop() {      // remove a Node at the top/beginning:
        T item = this.first.item;

        this.first = this.first.next;
        this.n--;

        return item;     // the value of the removed Node
    }

    public void removeLast() {
        if (n <= 0) {
            throw new IllegalArgumentException("empty");
        }
        else if (n == 1) {
            first = null;
        }
        else {
            Node x = first;
            for (int i = 0; i < n - 2; i++) {
                x = x.next;
            }
            x.next = null;
            n--;
        }
    }

    public void delete(int k) {
        if (n <= 0) {
            throw new IllegalArgumentException("empty");
        }
        else if (n == 1) {
            first = null;
        }
        else {
            Node x = first;
            for (int i = 0; i < k - 1; i++) {
                x = x.next;
            }
            x.next = x.next.next;
            n--;
        }
    }

    public boolean find(String key) {
        if (n <= 0) {
            return false;
        }
        else {
            boolean find = false;
            for (Node x = first; x != null; x = x.next) {
                if (x.item.equals(key)) {
                    find = true;
                    break;
                }
            }
            return find;
        }
    }

    public void removeAfter(Node node) {
        if (n > 1 && node != null && node.next != null) {
            Node temp = node.next.next;
            node.next = temp;
            n--;
        }
    }

    public void insertAfter(Node a, Node b) {
        if (a != null && b != null) {
            b.next = a.next;
            a.next = b;
            n++;
        }
    }

    public void remove(String key) {
        if (n == 0) {
            throw new IllegalArgumentException("empty");
        }
        else {
            if (first.item.equals(key)) {
                first = null;
            }
            else {
                for (Node x = first; x != null; x = x.next) {
                    if (x.next.item.equals(key)) {
                        removeAfter(x);
                    }
                }
            }
        }
    }

    public int max() {
        int max;
        if (n == 0) {
            max = 0;
        }
        else {
            max = (Integer) first.item;
            for (Node x = first.next; x != null; x = x.next) {
                max = Math.max((Integer) x.item, max);
            }
        }
        return max;
    }

    private int compareMax(Node node, int max) {
        if (node == null) {
            return max;
        }
        else {
            max = Math.max((Integer) node.item, max);
        }
        return compareMax(node.next, max);
    }

    public int maxR() {
        if (n == 0) {
            return 0;
        }
        else {
            return compareMax(first.next, (Integer) first.item);
        }
    }

    public Iterator<T> iterator() {
        return new ListIterator(); // we promise to provide a nested class "ListIterator"
    }

    // provide a nested class ListIterator, or use default, that must implements the hasNext() and next() methods
    private class ListIterator implements Iterator<T> {
        private Node current = first;

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
