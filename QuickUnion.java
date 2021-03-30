//Toni//

public class QuickUnion {
    private int[] id;
    private int count;

    public QuickUnion(int N) {
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

    public int findroot(int p) {
        while (p != this.id[p]) {
            p = this.id[p];           // find root.
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return findroot(p) == findroot(q);
    }

    public void union(int p, int q) {       // p's root is find(p), q's rood is find(q)
        int i = findroot(p);
        int j = findroot(q);

        if (i == j) {           // if they have same root, do nothing
            return;
        }
        else {          // to link p'root to q's root, we change p's root's id to q's root
            this.id[i] = j;
        }
        this.count--;     // take care of count
    }

    public static void main(String[] args) {

    }
}
