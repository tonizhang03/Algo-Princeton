// eager Prim Minimum spanning tree
// edge weighted undirected graph

import edu.princeton.cs.algs4.IndexMinPQ;

public class PrimMST {
    private Edge[] edgeTo; // shortest edge from tree vertex
    private double[] disTo; // distTo[w] = edgeTo[w].weight()
    private boolean[] marked; // if the vertex is on the tree
    private IndexMinPQ<Double> pq; // eligible crossing edge, not making cycle

    public PrimMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        disTo = new double[G.V()];
        marked = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) {
            disTo[v] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<Double>(
                G.V()); // store disTo[v] as the key of v, will update if dis to tree changed

        disTo[0] = 0.0;
        pq.insert(0, 0.0); // vertex 0 with weight 0.0

        while (!pq.isEmpty()) {
            visit(G, pq.delMin()); // add closest vertex to tree
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        // add vertex v to tree, update queues
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (marked[w]) {
                continue;
            }
            if (e.weight() < disTo[w]) {
                // edge e is the new best connection from tree to w
                edgeTo[w] = e;
                disTo[w] = e.weight();
                if (pq.contains(w)) {
                    pq.changeKey(w,
                                 disTo[w]);  // new disTo[w] is the updated dis from w to tree, it's stored in pq as the key of w, now it needs update too
                }
                else {
                    pq.insert(w, disTo[w]); // add w to queue if it's not in
                }
            }
        }
    }

    public static void main(String[] args) {
    }
}
