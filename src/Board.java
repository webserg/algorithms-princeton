import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by webserg on 10.07.2014.
 * . Organize your program by creating an immutable data type Board with the following API:
 */
public class Board {
    private final int N;
    private final int EMPTY;
    private final int[][] blocks;
    private final int manhattan;

    public Board(final int[][] blocks) {
        N = blocks.length;
        EMPTY = 0;
        this.blocks = copyBoard(blocks);
        manhattan = wrongPositionByManhattan();
    }

    // (where blocks[i][j] = block in row i, column j)
    public int dimension() {
        return N;
    }                // board dimension N

    private int seed(int i, int j) {
        return N * i + j;
    }

    public int hamming() {

        int wrongPosition = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                int cur = seed(i, j);
                if (cur + 1 != blocks[i][j] && blocks[i][j] != EMPTY) {
                    wrongPosition++;
                }
            }
        return wrongPosition;
    }

    public int manhattan() {
        return manhattan;
    }

    private int wrongPositionByManhattan() {
        int wrongPosition = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                int cur = seed(i, j);
                if (cur + 1 != blocks[i][j] && blocks[i][j] != EMPTY) {
                    int val = blocks[i][j] - 1;
                    int jRight = Math.abs(val % N);
                    int iRight = Math.abs(val - jRight) / N;
                    int pos = Math.abs(i - iRight) + Math.abs(j - jRight);
                    wrongPosition += pos;
                }
            }
        return wrongPosition;
    }

    public boolean isGoal() {
        return manhattan == 0;
    }


    // a board obtained by exchanging two adjacent blocks in the same row
    public Board twin() {
        int[][] newBlocks = copyBoard(blocks);
        int i, j;
        int jj;
        do {
            i = StdRandom.uniform(dimension());
            j = StdRandom.uniform(dimension());
            if (j + 1 == N) jj = j - 1;
            else jj = j + 1;
        } while (blocks[i][j] == EMPTY || blocks[i][jj] == EMPTY);

        newBlocks[i][jj] = blocks[i][j];
        newBlocks[i][j] = blocks[i][jj];
        return new Board(newBlocks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board that = (Board) o;
        int[][] b = that.blocks;
        if (this.dimension() != that.dimension()) return false;
        if (this.manhattan != that.manhattan()) return false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.blocks[i][j] != b[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private class BoardComparator implements Comparator<Board> {
        @Override
        public int compare(Board o1, Board o2) {
            if (o1.manhattan() < o2.manhattan()) return -1;
            else if (o1.manhattan() > o2.manhattan()) return 1;
            else return 0;
        }
    }


    private class BoardQ implements Iterable<Board> {

        private MinPQ<Board> neighbors = new MinPQ<>(4, new BoardComparator());

        BoardQ(Board searchNode) {
            int n = searchNode.N;
            int iEmpty = 0, jEmpty = 0;
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    if (searchNode.blocks[i][j] == EMPTY) {
                        iEmpty = i;
                        jEmpty = j;
                        break;
                    }
                }
            if (iEmpty - 1 >= 0) {
                addNeighbor(searchNode, iEmpty, jEmpty, iEmpty - 1, jEmpty);
            }
            if (iEmpty + 1 < n) {
                addNeighbor(searchNode, iEmpty, jEmpty, iEmpty + 1, jEmpty);
            }
            if (jEmpty - 1 >= 0) {
                addNeighbor(searchNode, iEmpty, jEmpty, iEmpty, jEmpty - 1);
            }
            if (jEmpty + 1 < n) {
                addNeighbor(searchNode, iEmpty, jEmpty, iEmpty, jEmpty + 1);
            }
        }

        private void addNeighbor(Board searchNode, int iZero, int jZero, int i, int j) {
            int[][] b = copyBoard(searchNode.blocks);
            b[i][j] = EMPTY;
            b[iZero][jZero] = searchNode.blocks[i][j];
            Board board = new Board(b);
            if (!Board.this.equals(board))
                neighbors.insert(board);
        }

        @Override
        public Iterator<Board> iterator() {
            return neighbors.iterator();
        }
    }

    public Iterable<Board> neighbors() {
        return new BoardQ(this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(N);
        stringBuilder.append("\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.blocks[i][j] < 10)
                    stringBuilder.append("  ");
                else stringBuilder.append(" ");
                stringBuilder.append(this.blocks[i][j]);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private int[][] copyBoard(int[][] init) {
        int[][] copy = new int[dimension()][dimension()];
        for (int i = 0; i < dimension(); i++) {
            System.arraycopy(init[i], 0, copy[i], 0, dimension());
        }
        return copy;
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                blocks[i][j] = in.readInt();
            }
        Board initial = new Board(blocks);

//        // solve the puzzle
        Solver solver = new Solver(initial);
//
//        // print solution to standard output
        if (!solver.isSolvable()) {
            StdOut.println("No solution possible");
//            StdOut.println("Minimum number of moves = " + solver.moves());
        } else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
