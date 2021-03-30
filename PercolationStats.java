// Toni Zhang 09/14/2020 //

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double[] r;    // ratio of p/(n^2)
    private final int t;
    private final double c95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        // err
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        this.t = trials;
        this.r = new double[trials];
        for (int i = 0; i < trials; i++) {

            Percolation percolation = new Percolation(n);

            while (!percolation.percolates()) {

                int a = StdRandom.uniform(1, n + 1);
                int b = StdRandom.uniform(1, n + 1);
                // System.out.println(a + ", " + b);
                percolation.open(a, b);
                // percolation.getOpened();
            }

            double p = percolation.numberOfOpenSites();
            // System.out.println("new p" + p);
            this.r[i] = p / (n * n);
            // System.out.println("this.r[i] " + this.r[i]);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.r);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.r);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - c95 * this.stddev() / Math.sqrt(this.t);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + c95 * this.stddev() / Math.sqrt(this.t);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats test = new PercolationStats(n, t);
        StdOut.println("mean = " + test.mean());
        StdOut.println("stddev = " + test.stddev());
        StdOut.println(
                "95% confidence interval = [" + test.confidenceLo() + ", " + test.confidenceHi()
                        + "]");

    }

}
