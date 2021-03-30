/* Brute force substring search: check for pattern starting at each text position */

public class SubstringSearch {
    // original
    public static int search(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        for (int i = 0; i <= N - M; i++) {
            // i is the pointer of text
            // j is the pointer of pattern
            int j; // reset j at 0 for every time i increased
            for (j = 0; j < M; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    // mismatch
                    break; // and increase i
                }
            }
            if (j == M) {
                // match
                return i; // starting from the i th character in text, match the pattern
            }
        }
        return N; // not found match in text
    }

    // alternate
    public static int searchAlt(String pat, String txt) {
        int i, N = txt.length();
        int j, M = pat.length();
        for (i = 0, j = 0; i < N && j < M; i++) {
            // check par pointer j with text pointer i. if match, move both pointers
            if (txt.charAt(i) == pat.charAt(j)) {
                j++;
            }
            // if mismatch, backup i to i-j
            else {
                i -= j;
                j = 0;
            }
        }
        if (j == M) {
            return i - M;
        }
        else {
            return N;
        }
    }

    public static void main(String[] args) {

    }
}
