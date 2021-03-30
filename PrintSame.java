import java.util.Arrays;

public class PrintSame {

    public static void Print(int[] a, int[] b) {
        int i = 0;
        int j = 0;
        Integer match = null;
        Arrays.sort(a);
        Arrays.sort(b);

        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                i++;
            }
            else if (a[i] > b[j]) {
                j++;
            }
            else {
                if ((match == null) || (match != a[i])) {
                    System.out.println(a[i]);
                    match = a[i];
                }
                else {
                    i++;
                    j++;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] test1 = new int[] { 1, 2, 4, 5, 5, 6, 8, 9 };
        int[] test2 = new int[] { 0, 2, 2, 3, 4, 6, 3, 8 };
        PrintSame.Print(test1, test2);
    }
}
