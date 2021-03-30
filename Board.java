import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

// an immutable data type that models an n-by-n board with sliding tiles
public class Board {

    private final int n;
    private int[][] board;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        if (tiles == null || tiles.length != tiles[0].length) {
            throw new IllegalArgumentException("board size must be n by n");
        }
        if (tiles.length < 2 || tiles.length >= 128) {
            throw new IllegalArgumentException("n out of bound");
        }

        this.n = tiles.length;
        this.board = new int[n][n];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                this.board[row][col] = tiles[row][col];
            }
        }
    }

    // string representation of this board
    public String toString() {
        String s = this.n + "\n";
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                s = s + board[row][col] + " ";
            }
            s = s + "\n";
        }
        return s;
    }

    // board dimension n
    public int dimension() {
        return this.n;
    }

    // // number of tiles out of place
    public int hamming() {
        int count = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int goal = row * n + col + 1;
                if (row == n - 1 && col == n - 1) {
                    goal = 0;
                }
                if (board[row][col] != 0 && board[row][col] != goal) {
                    count++;
                }
            }
        }
        return count;
    }

    //
    // // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int sum = 0;
        for (int rowG = 0; rowG < n; rowG++) {
            for (int colG = 0; colG < n; colG++) {
                int goal = rowG * n + colG + 1;
                if (goal != n * n) {
                    for (int rowB = 0; rowB < n; rowB++) {
                        for (int colB = 0; colB < n; colB++) {
                            if (board[rowB][colB] == goal) {
                                sum = sum + Math.abs(rowG - rowB) + Math.abs(colG - colB);
                            }
                        }
                    }
                }

            }
        }
        return sum;
    }

    // // is this board the goal board?
    public boolean isGoal() {
        return this.hamming() == 0;
    }

    // // // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }
        if (y == null) {
            return false;
        }
        if (y.getClass() != this.getClass()) {
            return false;
        }
        Board that = (Board) y;
        if (this.n != that.n) {
            return false;
        }
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (this.board[row][col] != that.board[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    // // // all neighboring boards
    public Iterable<Board> neighbors() {
        Queue<Board> queue = new Queue<Board>();
        // enqueue all the neighbors
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] == 0) {
                    if (upNeighbor(row, col) != null) {
                        queue.enqueue(upNeighbor(row, col));
                    }
                    if (downNeighbor(row, col) != null) {
                        queue.enqueue(downNeighbor(row, col));
                    }
                    if (leftNeighbor(row, col) != null) {
                        queue.enqueue(leftNeighbor(row, col));
                    }
                    if (rightNeighbor(row, col) != null) {
                        queue.enqueue(rightNeighbor(row, col));
                    }
                    break;
                }
            }
        }
        return queue;
    }


    private Board upNeighbor(int row, int col) {
        Board neighbor = new Board(this.board);
        if (row == 0) {
            neighbor = null;
        }
        else {
            int tmp = neighbor.board[row][col];
            neighbor.board[row][col] = neighbor.board[row - 1][col];
            neighbor.board[row - 1][col] = tmp;
        }
        return neighbor;
    }

    private Board downNeighbor(int row, int col) {
        Board neighbor = new Board(this.board);
        if (row == n - 1) {
            neighbor = null;
        }
        else {
            int tmp = neighbor.board[row][col];
            neighbor.board[row][col] = neighbor.board[row + 1][col];
            neighbor.board[row + 1][col] = tmp;
        }
        return neighbor;
    }

    private Board leftNeighbor(int row, int col) {
        Board neighbor = new Board(this.board);
        if (col == 0) {
            neighbor = null;
        }
        else {
            int tmp = neighbor.board[row][col];
            neighbor.board[row][col] = neighbor.board[row][col - 1];
            neighbor.board[row][col - 1] = tmp;
        }
        return neighbor;
    }

    private Board rightNeighbor(int row, int col) {
        Board neighbor = new Board(this.board);
        if (col == n - 1) {
            neighbor = null;
        }
        else {
            int tmp = neighbor.board[row][col];
            neighbor.board[row][col] = neighbor.board[row][col + 1];
            neighbor.board[row][col + 1] = tmp;
        }
        return neighbor;
    }

    // // // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        Board twin = new Board(this.board);

        if (twin.board[0][0] != 0 && twin.board[0][1] != 0) {
            int tmp = twin.board[0][0];
            twin.board[0][0] = twin.board[0][1];
            twin.board[0][1] = tmp;
        }
        else {
            int tmp = twin.board[1][0];
            twin.board[1][0] = twin.board[1][1];
            twin.board[1][1] = tmp;
        }

        return twin;
    }


    // unit testing (not graded)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        StdOut.print(initial.twin().toString());
        // for (Board neighbor : initial.neighbors()) {
        //     StdOut.println(neighbor.toString());
        // }
        //     StdOut.print(
        //             initial.toString() + " hamming: " + initial.hamming() + " manhattan: " + initial
        //                     .manhattan());
        //     StdOut.print(
        //             initial.upNeighbor(1, 1).toString() + " hamming: " + initial.hamming()
        //                     + " manhattan: " + initial
        //                     .manhattan());
        //     StdOut.print(
        //             initial.toString() + " hamming: " + initial.hamming() + " manhattan: " + initial
        //                     .manhattan());
    }

}
