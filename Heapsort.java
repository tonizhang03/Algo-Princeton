public class Heapsort {

    private Heapsort() {

    }

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int k = n / 2; k >= 1; k--) {
            sink(a, k, n);
        }
        while (n > 1) {
            exch(a, 1, n--);
            sink(a, 1, n);
        }
    }

    private static void sink(Comparable[] pq, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(pq, j, j + 1)) {
                j++;
            }
            if (!less(pq, k, j)) {
                break;
            }
            exch(pq, k, j);
            k = j;
        }
    }

    private static boolean less(Comparable[] pq, int k, int j) {
        return pq[k].compareTo(pq[j]) < 0;
    }

    private static void exch(Object[] pq, int k, int j) {
        Object t = pq[k];
        pq[k] = pq[j];
        pq[j] = t;
    }

    public static void main(String[] args) {

    }
}
