/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class CheckPair {

    public static int quadratic(int[] a) {
        int n = a.length;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n && j != i; j++) {
                if (a[i] == a[j]) {
                    sum++;
                }
            }
        }
        return sum;
    }

    public static int linearithmic(int[] a) {
        int n = a.length;
        int count = 1;
        int sum = 0;

        Arrays.sort(a);

        for (int i = 0; i < n - 1; i++) {
            if (a[i] == a[i + 1]) {
                count++;
            }
            else {

                sum = sum + (count * (count - 1) / 2);

                count = 1;
            }
        }

        if (count > 1) {
            sum = sum + (count * (count - 1) / 2);
        }

        return sum;
    }

    public static void main(String[] args) throws FileNotFoundException {

        Scanner s = new Scanner(new File("input6.txt"));
        int[] test = new int[s.nextInt()];
        System.out.println(CheckPair.quadratic(test));
        System.out.println(CheckPair.linearithmic(test));
    }
}
