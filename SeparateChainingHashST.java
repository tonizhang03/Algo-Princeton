import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SequentialSearchST;

// for each array indice, build a linked-list for key-value pairs
public class SeparateChainingHashST<Key, Value> {
    private int M;  // hash table size
    private SequentialSearchST<Key, Value>[] st;   // array of ST objects

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int M) {  // create M linked lists
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value value) {
        st[hash(key)].put(key, value);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (SequentialSearchST t : st) {
            for (Object key : t.keys()) {
                queue.enqueue((Key) key);
            }
        }
        return queue;
    }

    public static void main(String[] args) {

    }
}
