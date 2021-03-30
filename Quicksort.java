import edu.princeton.cs.algs4.StdRandom;

public class Quicksort {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (a[++i].compareTo(v) < 0) {
                if (i == hi) break;
            }
            while (v.compareTo(a[--j]) < 0) {
                if (j == lo) break;
            }
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void sort3way(Comparable[] a) {
        StdRandom.shuffle(a);
        sort3way(a, 0, a.length - 1);
    }

    private static void sort3way(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int lt = lo;
        int i = lo + 1;
        int gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int compare = a[i].compareTo(v);
            if (compare < 0) {
                exch(a, lt, i);
                lt++;
                i++;
            }
            else if (compare > 0) {
                exch(a, i, gt);
                gt--;
            }
            else {
                i++;
            }
        }
        sort3way(a, lo, lt - 1);
        sort3way(a, gt + 1, hi);
    }

    public static void main(String[] args) {

    }
}
