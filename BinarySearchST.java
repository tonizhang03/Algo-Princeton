import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

// Ordered array symbol-table with ordered symbol-table API

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int n;

    public BinarySearchST(int capacity) {
        this.keys = (Key[]) new Comparable[capacity];
        this.values = (Value[]) new Object[capacity];
    }

    public int size() {
        return this.n;
    }

    public boolean isEmpty() {
        return this.n == 0;
    }

    private void resize(int s) {
        Key[] tmpKeys = Arrays.copyOf(keys, s);
        Value[] tmpValues = Arrays.copyOf(values, s);

        keys = tmpKeys;
        values = tmpValues;
    }

    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < this.n && keys[i].compareTo(key) == 0) {
            return values[i];
        }
        else {
            return null;
        }
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public int rank(Key key) {
        int lo = 0;
        int hi = this.n - 1;
        while (lo <= hi) {
            int mid = (hi - lo) / 2 + lo;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            }
            else if (cmp > 0) {
                lo = mid + 1;
            }
            else {
                return mid;
            }
        }
        return lo;
    }

    public void put(Key key, Value val) {
        if (key == null || val == null) {
            throw new IllegalArgumentException();
        }
        int i = rank(key);
        if (i < this.n && keys[i].compareTo(key) == 0) {
            values[i] = val;
            return;
        }
        if (this.n == keys.length) {
            resize(keys.length * 2);
        }
        for (int j = this.n; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = val;
        this.n++;
    }


    public void delete(Key key) {
        if (isEmpty() || !contains(key)) {
            return;
        }
        int r = rank(key);
        for (int i = r; i < n - 1; i++) {
            keys[i] = keys[i + 1];
            values[i] = values[i + 1];
        }
        keys[n - 1] = null;
        values[n - 1] = null;
        this.n--;

        if (this.n <= keys.length / 4) {
            resize(keys.length / 2);
        }
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[n - 1];
    }

    public Key select(int k) {
        return keys[k];
    }

    public Key ceiling(Key key) {
        int r = rank(key);
        if (r == n) {
            return null;
        }
        return keys[r];
    }

    public Key floor(Key key) {
        int r = rank(key);
        if (r == 0) {
            return null;
        }
        return keys[r - 1];
    }

    public void deleteMin() {
        delete(min());
    }

    public void deleteMax() {
        delete(max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<Key>();
        for (int i = rank(lo); i < rank(hi); i++) {
            q.enqueue(keys[i]);
        }
        if (contains(hi)) {
            q.enqueue(keys[rank(hi)]);
        }
        return q;
    }

    public static void main(String[] args) {

    }
}
