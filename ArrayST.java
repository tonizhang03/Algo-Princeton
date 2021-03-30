// Unordered array symbol-table with basic API
// Exercise 3.1.2

import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

public class ArrayST<Key, Value> {
    private Key[] keys;
    private Value[] values;
    private int n;

    public ArrayST(int cap) {
        this.keys = (Key[]) new Object[cap];
        this.values = (Value[]) new Object[cap];
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (val == null) {
            delete(key);
        }
        for (int i = 0; i < n; i++) {
            if (keys[i].equals(key)) {
                values[i] = val;
                return;
            }
        }
        if (this.n == keys.length) {
            resize(keys.length * 2);
        }
        keys[n] = key;
        values[n] = val;
        this.n++;
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < n; i++) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < n; i++) {
            if (keys[i].equals(key)) {
                exchange(keys, i, n - 1);
                exchange(values, i, n - 1);
                keys[n - 1] = null;
                values[n - 1] = null;
                this.n--;
                return;
            }
        }
        if (this.n <= keys.length / 4) {
            resize(keys.length / 2);
        }
    }

    private void exchange(Object[] array, int i, int j) {
        Object tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public boolean isEmpty() {
        return this.n == 0;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public int size() {
        return this.n;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();

        for (int i = 0; i < n; i++) {
            queue.enqueue(keys[i]);
        }
        return queue;
    }

    private void resize(int s) {
        Key[] tmpKeys = Arrays.copyOf(keys, s);
        Value[] tmpValues = Arrays.copyOf(values, s);

        keys = tmpKeys;
        values = tmpValues;
    }

    public static void main(String[] args) {

    }
}
