import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

// Ford-Fulkerson shortest-augmenting path maxflow algorithm
// edges are FlowEdge, graph is FlowNetwork
// shortest path means the number if edges on the path, not the flow or capacity
// can find augmenting path in the residual network via breadth-first search BFS
// compute residual network of original network, then Augmenting path in original network is equivalent to directed path in residual network.
public class FordFulkerson {
    private boolean[] marked; // true if s->v path in residual network
    private FlowEdge[] edgeTo; // last edge on s->v path
    private double value;  // current value of maxflow

    public FordFulkerson(FlowNetwork G, int s, int t) {
        // find maxflow in flow network G from s to t
        while (hasAugmentingPath(G, s, t)) {
            // while there exists an augmenting path, use it
            // compute bottleneck capacity
            double bottle = Double.POSITIVE_INFINITY;
            for (int v = t; v != s; v = edgeTo[v]
                    .other(v)) { // start from t going back to s, find the smallest capacity, which is the bottleneck of the path
                bottle = Math.min(bottle, edgeTo[v].residualCapabilityTo(
                        v)); // residual capacity means how much more flow can be added
            }
            // augment flow
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                edgeTo[v].addResidualFlowTo(v,
                                            bottle); // update the flow of all edges on that path: add new flow to existing flow
            }
            this.value += bottle; // update total flow
        }
    }

    public double value() {
        return value;
    }

    public boolean inCut(int v) {
        // is v reachable from s in residual network?
        return marked[v];
    }

    private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
        // Breadth-first search, queue based
        marked = new boolean[G.V()];
        edgeTo = new FlowEdge[G.V()];

        Queue<Integer> q = new Queue<Integer>(); // vertices on path
        q.enqueue(s);     // enqueue source
        marked[s] = true; // add source to path

        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (FlowEdge e : G.adj(v)) {
                // for every edge to an unmarked vertex in residual network
                int w = e.other(v);
                // found path from s to w in the residual network
                if (e.residualCapabilityTo(w) > 0 && !marked[w]) {
                    edgeTo[w] = e; // e is the last edge on path to w
                    marked[w] = true; // w is now on path
                    q.enqueue(w); // add w to path
                }
            }
        }

        return marked[t]; // is t reachable from s in residual network, also means that new augmenting path is possible in the original network
    }

    public static void main(String[] args) {
        FlowNetwork G = new FlowNetwork(new In(args[0]));
        int s = 0;
        int t = G.V() - 1;
        FordFulkerson maxflow = new FordFulkerson(G, s, t);
        // print the shortest path
        StdOut.println("Max flow from " + s + " to " + t);
        for (int v = 0; v < G.V(); v++) {
            for (FlowEdge e : G.adj(v)) {
                if (v == e.from() && e.flow() > 0) {
                    StdOut.println(" " + e);
                }
            }
        }
        StdOut.println("Max flow value = " + maxflow.value());
    }
}
