import edu.princeton.cs.algs4.Queue;

// when there is a collision we  check the next entry in the table (by incrementing the index)
public class LinearProbingHashST<Key, Value> {
    private int N;   // number is key-value pairs in the table
    private int M;  // size of linear-probing table
    private Key[] keys;  // the keys
    private Value[] values;  // the values

    public LinearProbingHashST(int cap) {
        M = cap;
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(Key key, Value value) {
        if (N >= M / 2) resize(2 * M); // double M
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
            keys[i] = key;
            values[i] = value;
            N++;
        }
    }


    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1 % M)) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }

    public void delete(Key key) {
        if (!constains(key)) return;
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % M;
        }
        keys[i] = null;
        values[i] = null;
        i = (i + 1) % M;
        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valToRedo = null;
            N--;
            put(keyToRedo, valToRedo);
            i = (i + 1) % M;
        }
        N--;
        if (N > 0 && N == M / 8) {
            resize(M / 2);
        }
    }

    private boolean constains(Key key) {
        return get(key) != null;
    }

    private void resize(int cap) {
        LinearProbingHashST<Key, Value> t;
        t = new LinearProbingHashST<Key, Value>(cap);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                t.put(keys[i], values[i]);
            }
        }
        keys = t.keys;
        values = t.values;
        M = t.M;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (Object key : keys) {
            if (key != null) {
                queue.enqueue((Key) key);
            }
        }
        return queue;
    }

    public static void main(String[] args) {

    }
}
