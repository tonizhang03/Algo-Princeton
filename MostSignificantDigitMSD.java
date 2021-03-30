import edu.princeton.cs.algs4.Insertion;

// Most-significant-digit-first string sort
// for various length strings
// recursive, use key-indexed counting
// time , space , stable
public class MostSignificantDigitMSD {
    private static int R = 256; // radix
    private static final int M = 15; // cutoff for small subarrays
    private static String[] aux;

    private static int charAt(String s, int d) {
        if (d < s.length()) {
            return s.charAt(d);
        }
        else {
            return -1;
        }
    }

    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, aux, 0, N - 1, 0);
    }

    private static void sort(String[] a, String[] aux, int lo, int hi, int d) {
        if (hi <= lo) {
            return;
        }
        // cutoff to Insertion sort for small subarrays
        if (hi <= lo + M) {
            Insertion.sort(a, lo, hi, d);
            return;
        }
        // key-indexed counting
        int[] count = new int[R + 2];
        // frequency count
        for (int i = lo; i <= hi; i++) {
            count[charAt(a[i], d) + 2]++;
        }
        // transform count to indices
        for (int r = 0; r < R + 1; r++) {
            count[r + 1] += count[r];
        }
        // distribute to aux
        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }
        // copy back
        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i - lo];
        }
        // recursive call sort subarrays
        for (int r = 0; r < R; r++) {
            sort(a, aux, lo + count[r], lo + count[r + 1] - 1, d + 1);
        }
    }

    public static void main(String[] args) {

    }
}
