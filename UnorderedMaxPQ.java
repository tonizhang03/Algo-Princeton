// Unordered array max priority queue

public class UnorderedMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int n;

    public UnorderedMaxPQ(int cap) {
        this.pq = (Key[]) new Comparable[cap];
    }

    public boolean isEmpty() {
        return this.n == 0;
    }

    public void insert(Key key) {
        pq[n] = key;
        this.n++;
    }

    public Key delMax() {
        int max = 0;
        for (int i = 1; i < n; i++) {
            if (less(max, i)) {
                max = i;
            }
        }
        exch(max, n - 1);
        return pq[--n];
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }

    public static void main(String[] args) {

    }
}
