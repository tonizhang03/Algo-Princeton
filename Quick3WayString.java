// 3-way string quicksort

public class Quick3WayString {
    public static void sort(String[] a) {
        sort(a, 0, a.length - 1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo) {
            return;
        }
        int lt = lo; // lt is the smaller pointer, start from lo
        int gt = hi; // gt is the greater pointer, start from hi
        int v = charAt(a[lo], d); // use first element as partition
        int i = lo
                + 1; // element to be compared to partition, start from the second element(element after partition)
        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t < v) {
                exch(a, lt++, i++);
            }
            else if (t > v) {
                exch(a, i, gt--);
            }
            else {
                i++;
            }
        }
        // now we have a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]
        sort(a, lo, lt - 1, d); // sort subarray smaller than partition
        if (v >= 0) {
            sort(a, lt, gt, d + 1); // sort subarray equal to partition
        }
        sort(a, gt + 1, hi, d); // sort subarray greater than partition
    }

    private static int charAt(String s, int d) {
        if (d < s.length()) {
            return s.charAt(d);
        }
        else {
            return -1;
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {

    }
}
