// DFS depth-first search
// marks all the vertices connected to a given source

import edu.princeton.cs.algs4.Stack;

public class DepthFirstSearch {
    private boolean[] marked; // has dfs been called for this vertex
    private int count;
    private int[] edgeTo; // last vertex on known path to this vertex
    private final int s; // source

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
        edgeTo = new int[G.V()];
        this.s = s;
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
                edgeTo[w] = v;
            }
        }
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {  // a path from s to v, not all paths, not the shortest
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    public static void main(String[] args) {

    }
}
