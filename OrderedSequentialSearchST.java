// Ordered linked list symbol-table with ordered API

import edu.princeton.cs.algs4.Queue;

public class OrderedSequentialSearchST<Key extends Comparable<Key>, Value> {
    private class Node {
        private Key key;
        private Value value;
        private Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node first;
    private int n;

    // ordered symbol table API:

    public OrderedSequentialSearchST() {
    }


    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (val == null) {
            delete(key);
            return;
        }

        if (isEmpty()) {
            this.first = new Node(key, val, null);
            this.n++;
            return;
        }
        if (key.compareTo(first.key) == 0) {
            this.first.value = val;
        }
        else if (key.compareTo(first.key) < 0) {
            this.first = new Node(key, val, first);
            this.n++;
        }
        else {
            for (Node x = first; x != null; x = x.next) {
                if (x.next == null) {
                    x.next = new Node(key, val, null);
                    this.n++;
                    return;
                }
                else if (key.compareTo(x.next.key) == 0) {
                    x.next.value = val;
                    return;
                }
                else if (key.compareTo(x.next.key) < 0) {
                    x.next = new Node(key, val, x.next);
                    this.n++;
                    return;
                }
            }
        }
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        for (Node x = first; x != null; x = x.next) {
            if (key.compareTo(x.key) == 0) {
                return x.value;
            }
        }
        return null;
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            throw new IllegalArgumentException("is empty");
        }
        if (key.compareTo(this.first.key) == 0) {
            first = first.next;
            this.n--;
            return;
        }
        for (Node x = this.first; x != null; x = x.next) {
            if (x.next == null) {
                throw new IllegalArgumentException("key not found");
            }
            else if (key.compareTo(x.next.key) == 0) {
                x.next = x.next.next;
                this.n--;
                return;
            }
        }
    }

    public boolean isEmpty() {
        return this.n == 0;
    }

    public boolean contains(Key key) {
        for (Node x = this.first; x != null; x = x.next) {
            if (key.compareTo(x.key) == 0) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return this.n;
    }

    public Key min() {
        if (isEmpty()) {
            throw new IllegalArgumentException("is empty");
        }
        return this.first.key;
    }

    public Key max() {
        if (isEmpty()) {
            throw new IllegalArgumentException("is empty");
        }
        for (Node x = this.first; x != null; x = x.next) {
            if (x.next == null) {
                return x.key;
            }
        }
        return null;
    }

    public Key floor(Key key) {
        if (isEmpty()) {
            throw new IllegalArgumentException("is empty");
        }

        if (key.compareTo(first.key) < 0) {
            return null;
        }
        for (Node x = first; x != null; x = x.next) {
            if (x.next == null) {
                return x.key;
            }
            else {
                if (key.compareTo(x.next.key) == 0) {
                    return x.next.key;
                }
                else if (key.compareTo(x.next.key) < 0) {
                    return x.key;
                }
            }
        }
        return null;
    }

    public Key ceiling(Key key) {
        if (isEmpty()) {
            throw new IllegalArgumentException("is empty");
        }
        if (key.compareTo(first.key) < 0) {
            return first.key;
        }
        for (Node x = first; x != null; x = x.next) {
            if (x.next == null) {
                return null;
            }
            else {
                if (key.compareTo(x.key) == 0) {
                    return x.key;
                }
                else if (key.compareTo(x.key) < 0) {
                    return x.next.key;
                }
            }
        }
        return null;
    }

    public int rank(Key key) {
        int count = 0;
        for (Node x = first; x != null; x = x.next) {
            if (key.compareTo(x.key) < 0) {
                count++;
            }
            else {
                break;
            }
        }
        return count;
    }

    public Key select(int k) {
        if (k < 0 || k >= n) {
            throw new IllegalArgumentException();
        }
        int count = 0;
        for (Node x = first; x != null; x = x.next) {
            if (count == k) {
                return x.key;
            }
            count++;
        }
        return null;
    }

    public void deleteMin() {
        if (!isEmpty()) {
            delete(min());
        }
    }

    public void deleteMax() {
        if (!isEmpty()) {
            delete(max());
        }
    }

    public int size(Key lo, Key hi) {
        return rank(hi) - rank(lo) + 1;
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<Key>();
        for (Node x = first; x != null; x = x.next) {
            q.enqueue(x.key);
        }
        return q;
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<Key>();
        for (Node x = first; x != null; x = x.next) {
            if (x.key.compareTo(lo) >= 0 && x.key.compareTo(hi) <= 0)
                q.enqueue(x.key);
        }
        return q;
    }


    public static void main(String[] args) {

    }
}
