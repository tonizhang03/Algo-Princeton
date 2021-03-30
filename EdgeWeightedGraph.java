// Edge-weighted graph (undirected)
// Edge data type see: Edge
// adjacency-lists of Edges instead of integers (compare to Graph)

import edu.princeton.cs.algs4.Bag;

public class EdgeWeightedGraph {
    private final int V;
    private int E;
    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Edge>();
        }
    }

    public int V() {
        // number of vertices
        return V;
    }

    public int E() {
        // number of edges
        return E;
    }

    public void addEdge(Edge e) {
        // add e to this graph
        int v = e.either(), w = e.other();
        adj[v].add(e);
        adj[w].add(e);
        this.E++;
    }

    public Iterable<Edge> adj(int v) {
        // edges incident to v
        return adj[v];
    }

    public Iterable<Edge> edges() {
        // all the graph's edges
        Bag<Edge> b = new Bag<Edge>();
        for (int v = 0; v < V; v++) {
            for (Edge e : adj[v]) {
                if (e.other(v) > v) {
                    b.add(e);
                }
            }
        }
        return b;
    }

    public static void main(String[] args) {

    }
}
