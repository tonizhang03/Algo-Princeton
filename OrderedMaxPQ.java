// Ordered array max priority queue

public class OrderedMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int n;

    public OrderedMaxPQ(int cap) {
        this.pq = (Key[]) new Comparable[cap];
    }

    public boolean isEmpty() {
        return this.n == 0;
    }

    public void insert(Key key) {
        pq[n] = key;
        this.n++;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && less(j, j - 1); j--) {
                exch(j, j - 1);
            }
        }
    }

    public Key delMax() {
        Key max = pq[n - 1];
        pq[n - 1] = null;
        n--;
        return max;
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
