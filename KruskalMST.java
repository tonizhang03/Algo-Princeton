// Kruskal's MST
// finding Minimal spanning tree in a edge-weighted undirected graph
// greedy algorithm
// Consider edges in ascending order of weight. Add next edge to tree T unless doing so would create a cycle
// use MinPQ, Queue, and Union-find

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

public class KruskalMST {
    private Queue<Edge> mst = new Queue<Edge>(); // MST is a queue of edges

    public KruskalMST(EdgeWeightedGraph G) {
        mst = new Queue<Edge>();
        // build PQ of all edges in ascending order
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge e : G.edges()) {
            pq.insert(e);
        }

        UF uf = new UF(G.V()); // union-find check connection of vertices

        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin(); // get min weight edge left in queue
            int v = e.either(); // and get its two vertices
            int w = e.other(v);

            if (uf.connected(v, w)) {  // ignore cycle
                continue;
            }
            uf.union(v, w); // connect v and w for further check
            mst.enqueue(e); // add this min weight edge to MST 
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }
    
    public static void main(String[] args) {

    }
}
