import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class BottomUpMergesort {
    private static Comparable[] aux;
    private static int count = 0;

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;


        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
            count = count + 2;
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            }
            else if (j > hi) {
                a[k] = aux[i++];
            }
            else if ((aux[j]).compareTo(aux[i]) < 0) {
                a[k] = aux[j++];
                count = count + 2;
            }
            else {
                a[k] = aux[i++];
                count = count + 2;
            }
            count = count + 2;
        }
    }

    public static int sort(Comparable[] a) {
        int n = a.length;
        aux = new Comparable[n];

        for (int sz = 1; sz < n; sz = sz * 2) {
            for (int lo = 0; lo < n - sz; lo = lo + sz * 2) {
                merge(a, lo, lo + sz - 1, Math.min(lo + sz * 2 - 1, n - 1));
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Comparable[] a = new Comparable[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        StdRandom.shuffle(a);

        StdOut.println(BottomUpMergesort.sort(a));
    }
}
