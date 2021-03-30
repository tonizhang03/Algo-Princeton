// Least-significant digit first string sort
// for fixed length strings
// use key-indexed counting
// time 2WN, space N+R, stable
public class LeastSignificantDigitLSD {
    public static void sort(String[] a, int w) {
        int R = 256; // Radix
        int N = a.length;
        String[] aux = new String[N];
        // do key-indexed counting for each digit from right to left
        for (int d = w - 1; d >= 0; d--) {
            // compute frequency count
            int[] count = new int[R + 1];
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(d) + 1]++;
            }
            // transform counts to indices
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }
            // distribute
            for (int i = 0; i < N; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            // copy back
            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }
        }
    }

    public static void main(String[] args) {

    }
}
