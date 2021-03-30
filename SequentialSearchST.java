import edu.princeton.cs.algs4.Queue;

// Unordered linked list symbol-table with basic API

public class SequentialSearchST<Key extends Comparable<Key>, Value> {
    private Node first;
    private int n = 0;

    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val;
            }
        }
        return null;
    }

    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
        n++;
    }

    public int size() {
        return this.n;
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (size() == 0) {
            return;
        }
        if (key.equals(first.key)) {
            first = first.next;
            n--;
            return;
        }
        for (Node x = first; x != null; x = x.next) {
            if (x.next.key == key && x.next != null) {
                x.next = x.next.next;
            }
        }
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<Key>();
        for (Node x = first; x != null; x = x.next) {
            q.enqueue(x.key);
        }
        return q;
    }


    public static void main(String[] args) {


    }
}
