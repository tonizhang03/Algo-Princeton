public class WeightedQuickUnion {
    private int[] id;
    private int count;
    private int[] size;   // size of the tree

    public WeightedQuickUnion(int N) {
        // initialize component id array
        this.count = N;
        this.id = new int[N];
        for (int i = 0; i < N; i++) {
            this.id[i] = i;
        }
        this.size = new int[N];
        for (int i = 0; i < N; i++) {
            this.size[i] = 1;
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
        else if (this.size[i] < this.size[j]) {          // check size
            this.id[i] = j;                              // small merge to large
            this.size[j] += this.size[i];
        }
        else {
            this.id[j] = i;
            this.size[i] += this.size[j];
        }
        this.count--;     // take care of count
    }

    public static void main(String[] args) {

    }
}

