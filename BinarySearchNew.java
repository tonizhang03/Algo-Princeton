import java.util.Arrays;

public class BinarySearchNew {

    public static int indexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        Arrays.sort(a);
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static int smallest(int[] a, int key) {
        int mid = BinarySearchNew.indexOf(a, key);

        if (mid == -1) {
            throw new IllegalArgumentException();
        }
        else {
            while (mid > 0) {
                if (a[mid] == a[mid - 1]) {
                    mid--;
                }
                else {
                    return mid;
                }
            }
            return mid;
        }
    }

    public static int largest(int[] a, int key) {
        int mid = BinarySearchNew.indexOf(a, key);
        if (mid == -1) {
            throw new IllegalArgumentException();
        }
        else {
            while (mid < a.length) {
                if (a[mid] == a[mid + 1]) {
                    mid++;
                }
                else {
                    return mid;
                }
            }
            return mid;
        }
    }

    public static int howMany(int[] a, int key) {
        return (BinarySearchNew.largest(a, key) - BinarySearchNew.smallest(a, key) + 1);
    }

    public static void main(String[] args) {
        int[] test = new int[] { 2, 2, 8, 9, 1, 7, 7, 7, 7 };
        System.out.println(BinarySearchNew.howMany(test, 5));

    }
}
