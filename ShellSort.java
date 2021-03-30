// Toni 2020 //

import java.util.Arrays;

public class ShellSort {
    private double rate;

    public static void sort(Comparable[] a) {
        int n = a.length;
        int h = 1;
        int count = 0;
        while (h < n / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = 0; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j = j - h) {
                    exch(a, j, j - h);
                    count++;
                }
            }
            System.out.println("h = " + h + " and the array is [" + Arrays.toString(a) + "].");
            h = h / 3;
        }
        System.out.println("compares / N = " + count / n);
    }

    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    public static void main(String[] args) {
        Comparable[] test = new Comparable[] {
                5, 3, 6, 9, 4, 2, 7, 4, 1, 0, 8, 4, 6, 8, 7, 1, 0, 3, 6, 4
        };
        ShellSort.sort(test);
    }
}
