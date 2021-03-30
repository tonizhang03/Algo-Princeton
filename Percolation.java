// Toni Zhang 09/14/2020 //

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int num;           // n by n grid
    private final int b;           // the viral bottom
    private final int[] a;         // collection of all sites
    private boolean[] isOp;    // isOpen
    private int p;           // number of open sites
    private final int[] stats;       // for position check
    private final WeightedQuickUnionUF weightedQuickUnion;

    //  --------------------- creates n-by-n grid, with all sites initially blocked ---------------------
    public Percolation(int n) {
        // err check
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.weightedQuickUnion = new WeightedQuickUnionUF(n * n + 2);

        this.num = n;
        this.b = n * n + 1;
        this.a = new int[n * n + 2];
        this.isOp = new boolean[(n * n) + 2];
        this.stats = new int[n * n + 2];

        this.a[0] = 0;
        this.isOp[0] = true;
        this.stats[0] = 0;
        this.a[b] = this.b;
        this.isOp[b] = true;
        this.stats[b] = 0;

        for (int i = 1; i < this.b; i++) {
            this.a[i] = i;
            this.isOp[i] = false;

            if (i <= n) {
                this.stats[i] = 1;  // first row
            }
            else if (i > n * (n - 1)) {
                this.stats[i] = 2;  // last row
            }
            else if (i % n == 1) {
                this.stats[i] = 3;  // first col
            }
            else if (i % n == 0) {
                this.stats[i] = 4;  // last col
            }
            else {
                this.stats[i] = 5;  // all the rest in the middle area
            }
        }

        this.p = 0;

    }

    // --------------------- connect a site to its neighbors ---------------------
    private void connectTop(int[] array, int i) {
        int iTop = i - this.num;

        if (this.stats[i] != 1 && this.isOp[iTop]) {         // not in the top row
            this.weightedQuickUnion.union(array[i], array[iTop]);
        }
    }

    private void connectBottom(int[] array, int i) {
        int iBottom = i + this.num;

        if (this.stats[i] != 2 && this.isOp[iBottom]) {      // not in the bottom row
            this.weightedQuickUnion.union(array[i], array[iBottom]);
        }
    }

    private void connectLeft(int[] array, int i) {
        int iLeft = i - 1;

        if (this.stats[i] != 3 && this.isOp[iLeft]) {        // not in the first col
            this.weightedQuickUnion.union(array[i], array[iLeft]);
        }
    }

    private void connectRight(int[] array, int i) {              // not in the last col
        int iRight = i + 1;

        if (this.stats[i] != 4 && this.isOp[iRight]) {
            this.weightedQuickUnion.union(array[i], array[iRight]);
        }
    }


    // int iTop = i - this.N;  // need check
    // int iBottom = i + this.N;   // need check
    // int iLeft = i - 1;    // need check
    // int iRight = i + 1;   // need check


    // --------------------- opens the site (row, col) ---------------------
    public void open(int row, int col) {
        // err check
        if ((1 > row) || (row > this.num) || (1 > col) || (col > this.num)) {
            throw new IllegalArgumentException();
        }

        // transfer row and col to i
        int i = (row - 1) * this.num + col;

        // check perculates
        if (this.a[0] == this.a[b]) {
            return;
        }
        else {

            // if already open, do nothing
            if (this.isOp[i]) {
                return;
            }

            // if it's not open yet, check stats,mark it as open, connect it with 4 neighbors
            else {
                this.isOp[i] = true;

                if (this.stats[i] == 1) {             // row 1
                    this.weightedQuickUnion.union(this.a[i], this.a[0]);
                    // row 1 connect to top a[0]
                    connectBottom(this.a, i);

                    if (col == 1) {                // corner (1,1)
                        connectRight(this.a, i);
                    }
                    else if (col == this.num) {    // corner(1,n)
                        connectLeft(this.a, i);
                    }
                    else {
                        connectLeft(this.a, i);
                        connectRight(this.a, i);
                    }
                }
                else if (this.stats[i] == 2) {        // last row
                    this.weightedQuickUnion.union(this.a[i], this.a[b]);
                    // last row connect to bottom
                    connectTop(this.a, i);

                    if (col == 1) {                // corner (1,1)
                        connectRight(this.a, i);
                    }
                    else if (col == this.num) {    // corner(1,n)
                        connectLeft(this.a, i);
                    }
                    else {
                        connectLeft(this.a, i);
                        connectRight(this.a, i);
                    }
                }
                else if (this.stats[i] == 3) {      // first col
                    connectTop(this.a, i);
                    connectRight(this.a, i);
                    connectBottom(this.a, i);
                }
                else if (this.stats[i] == 4) {      // last col
                    connectTop(this.a, i);
                    connectLeft(this.a, i);
                    connectBottom(this.a, i);
                }
                else {
                    connectTop(this.a, i);
                    connectLeft(this.a, i);
                    connectRight(this.a, i);
                    connectBottom(this.a, i);
                }

                this.p++;

            }
        }
        // System.out.println("p " + this.p);
    }

    // ---------------------- is the site (row, col) open? ----------------------
    public boolean isOpen(int row, int col) {
        // err check
        if ((1 > row) || (row > this.num) || (1 > col) || (col > this.num)) {
            throw new IllegalArgumentException();
        }

        int i = (row - 1) * this.num + col;

        return this.isOp[i];
    }

    //  ---------------------- is the site (row, col) full? Full open site means it's connected to the top. Empty open site is not connected to the top
    public boolean isFull(int row, int col) {
        // err check
        if ((1 > row) || (row > this.num) || (1 > col) || (col > this.num)) {
            throw new IllegalArgumentException();
        }
        int i = (row - 1) * this.num + col;

        return (this.weightedQuickUnion.find(a[i]) == this.weightedQuickUnion.find(a[0]));
    }

    // // test all open sites
    // public void getOpened() {
    //     String s = "";
    //     for (int i = 0; i < this.b; i++) {
    //         int col = i % this.N;
    //         int row = (i - col) / this.N;
    //         if (isOp[i]) {
    //             s += "[" + row + ":" + col + "]";
    //         }
    //     }
    //     System.out.println(s);
    // }


    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.p;
    }

    // does the system percolate?
    public boolean percolates() {
        return (this.weightedQuickUnion.find(a[0]) == this.weightedQuickUnion.find(a[b]));
    }

    // test client (optional)
    // public static void main(String[] args) {
    // }

}
