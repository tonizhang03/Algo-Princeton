import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// Critical path method for parallel precedence-constrained job scheduling
// finding longest paths tree in edge-weighted digraph using topological sort
public class CPM {

    public static void main(String[] args) {
        int N = StdIn.readInt();
        StdIn.readLine();
        EdgeWeightedDigraph G;
        G = new EdgeWeightedDigraph(2 * N + 2);

        int s = 2 * N;
        int t = 2 * N + 1;
        for (int i = 0; i < N; i++) {
            String[] a = StdIn.readLine().split("\\s+");
            double duration = Double.parseDouble(a[0]);
            // Three edges for each job.
            // – begin to end (weighted by duration)
            // – source to begin (0 weight)
            // – end to sink (0 weight)
            G.addEdge(new DirectedEdge(i, i + N, duration));
            G.addEdge(new DirectedEdge(s, i, 0.0));
            G.addEdge(new DirectedEdge(i + N, t, 0.0));
            for (int j = 1; j < a.length; j++) {
                int successor = Integer.parseInt(a[j]);
                G.addEdge(new DirectedEdge(i + N, successor, 0.0));
            }
        }
        AcyclicSP lp = new AcyclicSP(G, s);

        StdOut.println("Start times:");
        for (int i = 0; i < N; i++) {
            StdOut.printf("%4d: %5.1f\n", lp.distTo(i));
        }
        StdOut.printf("Finish time: %5.1f\n", lp.distTo(t));
    }
}
