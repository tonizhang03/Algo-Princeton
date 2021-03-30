// lazy Prim Minimal spanning tree
// finding MST in edge-weighted undirected graph
// Start with vertex 0 and greedily grow tree T. Add to T the min weight edge with exactly one endpoint in T. Repeat until V - 1 edges.
// greedy

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

public class LazyPrimMST {
    private boolean[] marked; // vertices on MST
    private Queue<Edge> mst;  // MST edges
    private MinPQ<Edge> pq;  // crossing edges (including cycles, will check)

    public LazyPrimMST(EdgeWeightedGraph G) {
        pq = new MinPQ<Edge>();
        marked = new boolean[G.V()];
        mst = new Queue<Edge>();

        visit(G, 0); // see below

        while (!pq.isEmpty()) {
            Edge e = pq.delMin(); // get the lowest weight candidate
            int v = e.either();  // and get its vertices
            int w = e.other(v);

            if (marked[v] && marked[w]) { // check cycle
                continue;
            }

            mst.enqueue(e); // add this min edge to MST

            // add either v or w to tree
            if (!marked[v]) {
                visit(G, v);
            }
            if (!marked[w]) {
                visit(G, w);
            }
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        // mark v and add to pq all edges from v to unmarked vertices
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            if (!marked[e.other(v)]) {
                pq.insert(e);
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public static void main(String[] args) {

    }
}
