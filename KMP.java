/* Knuth-Morris-Pratt substring search */

import edu.princeton.cs.algs4.StdOut;

public class KMP {
    private String pat;
    private int[][] dfa;

    public KMP(String pat) {
        // build a dfa from the pattern pat
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        dfa = new int[R][M];
        for (int X = 0, j = 1; j < M; j++) {
            // compute dfa[][j]
            for (int c = 0; c < R; c++) {
                // c is the state, j is the situations will encounter, dfa[c][j] is what state should go to then
                dfa[c][j] = dfa[c][X]; // copy mismatch cases from restart state

            }
            dfa[pat.charAt(j)][j] = j + 1; // set match case, move to the next state
            X = dfa[pat.charAt(j)][X]; // update restart state, if mismatch, restart from X state
        }
    }

    public int search(String txt) {
        // simulate operation of DFA on txt
        int i, j, N = txt.length(), M = pat.length();
        for (i = 0, j = 0; i < N && j < M; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == M) {
            return i - M; // match found, pointer i reached the final state of dfa (the end of pat)
        }
        else {
            return N;  // match not found, return the end of txt
        }
    }

    public static void main(String[] args) {
        String pat = args[0];
        String txt = args[1];
        KMP kmp = new KMP(pat);
        StdOut.println("text: " + txt);
        int offset = kmp.search(txt);
        StdOut.println("pattern: ");
        for (int i = 0; i < offset; i++) {
            StdOut.print(" ");
        }
        StdOut.println(pat);
    }
}
