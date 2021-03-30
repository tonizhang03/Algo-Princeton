// netflow, maxflow mincut
// flow edge data type for residual network
// based on edge-weighted DirectedEdge, add a flow instance variable and 2 methods to implement the residual flow network

public class FlowEdge {
    private final int v;
    private final int w;
    private final double capacity;
    private double flow; // mutable variable

    public FlowEdge(int v, int w, double capacity) {
        this.v = v;
        this.w = w;
        this.capacity = capacity;
        this.flow = 0.0;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public double capacity() {
        return capacity;
    }

    public double flow() {
        return flow;
    }

    public int other(int vertex) {
        if (vertex == v) {
            return w;
        }
        else if (vertex == w) {
            return v;
        }
        else {
            throw new RuntimeException();
        }

    }

    public double residualCapabilityTo(int vertex) { // direction is going to vertex, from v -> w
        if (vertex == v) { // backward edge: going to v
            return flow;
        }
        else if (vertex == w) { // forward edge: going to w
            return capacity - flow;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public void addResidualFlowTo(int vertex, double delta) {
        if (vertex == v) { // backward edge: going to v
            flow -= delta; // decrease flow
        }
        else if (vertex == w) { // forward edge: going to w
            flow += delta; // increase flow
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public String toString() {
        return String.format("%d->%d %.2f %.2f", v, w, capacity, flow);
    }

    public static void main(String[] args) {

    }
}
