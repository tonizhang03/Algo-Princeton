import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class TopDownMergesort {

    private static Comparable[] aux;
    private static int count = 0;

    public TopDownMergesort() {

    }

    public static int sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
        return count;
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);

    }

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


    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Comparable[] a = new Comparable[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        StdRandom.shuffle(a);
        
        StdOut.println(TopDownMergesort.sort(a));

    }
}
