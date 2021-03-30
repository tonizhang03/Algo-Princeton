// same as EdgeWeightedGraph
// but adjacency lists of FlowEdge instead of Edges

import edu.princeton.cs.algs4.Bag;

public class FlowNetwork {
    private final int V;
    private Bag<FlowEdge>[] adj;

    public FlowNetwork(int V) {
        this.V = V;
        adj = (Bag<FlowEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<FlowEdge>();
        }
    }

    public int V() {
        return V;
    }

    public void addEdge(FlowEdge e) {
        int v = e.from();
        int w = e.to();
        adj[v].add(e); // add forward edge
        adj[w].add(e); // add backward edge
    }

    public Iterable<FlowEdge> adj(int v) {
        return adj[v];
    }

    public static void main(String[] args) {

    }
}
