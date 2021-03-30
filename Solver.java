import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

// an immutable data type that implement A* search to solve n-by-n slider puzzles
public class Solver {
    private SearchNode current;
    // private int n;       // move
    private boolean solvable;

    private class SearchNode implements Comparable<SearchNode> {
        private final int moves;
        // priority =  estimated min. number of moves + Hamming or manhattan
        private final Board board; // current array, the value of the node
        private final SearchNode prev;  // neighbors, children

        public SearchNode(int moves, Board board, SearchNode prev) {
            this.moves = moves;
            this.board = board;
            this.prev = prev;
        }

        public int compareTo(SearchNode that) {
            int priorityThis = this.moves + this.board.manhattan();
            int priorityThat = that.moves + that.board.manhattan();
            return priorityThis - priorityThat;
        }

        public boolean equals(Object that) {
            if (this == that) {
                return true;
            }
            if (that == null || this.getClass() != that.getClass()) {
                return false;
            }
            SearchNode t = (SearchNode) that;
            return this.board.equals(t.board);
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }

        this.current = new SearchNode(0, initial, null);
        // this.n = 0;
        this.solvable = false;
        SearchNode twin = new SearchNode(0, initial.twin(), null);

        MinPQ<SearchNode> queue = new MinPQ<SearchNode>();
        queue.insert(this.current);
        MinPQ<SearchNode> twinQueue = new MinPQ<SearchNode>();
        twinQueue.insert(twin);

        while (queue.min() != null || twinQueue.min() != null) {

            this.current = queue.min();
            twin = twinQueue.min();
            if (this.current.board.isGoal()) {
                solvable = true;
                break;
            }
            if (twin.board.isGoal()) {
                solvable = false;
                break;
            }

            Board min = this.current.board;
            Board twinBoard = twin.board;

            // Insert neighbors
            for (Board neighbor : min.neighbors()) {
                if (this.current.prev == null || !neighbor.equals(this.current.prev.board)) {
                    queue.insert(
                            new SearchNode(this.current.moves + 1, neighbor, this.current));
                }
            }

            for (Board neighbor : twinBoard.neighbors()) {
                if (twin.prev == null || !neighbor.equals(twin.prev.board)) {
                    twinQueue.insert(
                            new SearchNode(twin.moves + 1, neighbor, twin));
                }
            }

            queue.delMin();
            twinQueue.delMin();
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    // priority = estimated min. number of moves
    public int moves() {
        if (!isSolvable()) {
            return -1;
        }
        return this.current.moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        Stack<Board> solution = new Stack<Board>();
        if (!isSolvable()) {
            solution = null;
        }
        else {
            for (SearchNode node = this.current; node != null; node = node.prev) {
                solution.push(node.board);

            }
        }

        return solution;
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}
