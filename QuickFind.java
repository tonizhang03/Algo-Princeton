/* *****************************************************************************
 Toni
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickFind {
    private int[] id;
    private int count;

    public QuickFind(int N) {
        // initialize component id array
        this.count = N;
        this.id = new int[N];
        for (int i = 0; i < N; i++) {
            this.id[i] = i;
        }
    }

    public int count() {
        return this.count;
    }

    public int find(int p) {
        return this.id[p];              // total N array access
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        // do nothing if p and q are already in the same component
        if (this.id[p] == this.id[q]) {
            return;
        }
        else {
            // rename p's component to q's name
            int pId = this.id[p];
            int qId = this.id[q];
            for (int i = 0; i < this.id.length; i++) {
                if (this.id[i] == pId) {
                    this.id[i] = qId;
                    this.count--;                 // don't forget to take care of count
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickFind qf = new QuickFind(n);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            if (qf.connected(p, q)) {
                continue;
            }
            else {
                qf.union(p, q);
            }
            StdOut.println(p + " - " + q);
        }
        StdOut.println(qf.count() + " is the number of components");
    }
}
