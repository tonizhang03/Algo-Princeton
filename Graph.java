import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

// Graph
// adjacency-list data structure using Bag

public class Graph {
    private final int V; // number of vertices
    private int E;  // number of edges;
    private Bag<Integer>[] adj;  // adjacency lists

    public Graph(int V) {
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];  // create array of lists
        for (int v = 0; v < V; v++) { // initialize all lists
            adj[v] = new Bag<Integer>(); // to empty
        }
    }

    public Graph(In in) {
        this(in.readInt()); // read V and construct this graph
        int E = in.readInt(); // read E
        for (int i = 0; i < E; i++) {
            // add an edge
            int v = in.readInt(); // read a vertex
            int w = in.readInt();  // read anther vertex
            addEdge(v, w); // and add edge connecting them
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);  // add w to v's list
        adj[w].add(v);  // add v to w's list
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public static void main(String[] args) {

    }
}
