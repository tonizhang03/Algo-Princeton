// weighted directed edge data type for edge-weighted digraphs
// similar and simpler than Edge data type for undirected graphs

public class DirectedEdge extends edu.princeton.cs.algs4.DirectedEdge {
    private final int v;
    private final int w;
    private final double weight;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return this.weight;
    }

    public int from() {
        return this.v;
    }

    public int to() {
        return this.w;
    }

    public String toString() {
        return String.format("%d->%d %.2f", v, w, weight);
    }

    public static void main(String[] args) {

    }
}
