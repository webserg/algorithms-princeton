import java.util.Arrays;
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

    public Board(final int[][] blocks) {
        N = blocks.length;
        EMPTY = 0;
        this.blocks = copyBoard(blocks);
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
                if (cur + 1 != blocks[i][j] && blocks[i][j] != EMPTY) {//empty block is uncounted
                    wrongPosition++;
                }
            }
        return wrongPosition;
    }                   // number of blocks out of place

    public int manhattan() {

        int wrongPosition = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                int cur = seed(i, j);
                if (cur + 1 != blocks[i][j] && blocks[i][j] != EMPTY) {
//                    System.out.println(blocks[i][j] + " - must=" + (cur+1));
                    int val = blocks[i][j] - 1;
                    int jRight = Math.abs(val % N);
                    int iRight = Math.abs(val - jRight) / N;
                    int pos = Math.abs(i - iRight) + Math.abs(j - jRight);
//                    System.out.println(blocks[i][j] + " - pos=" + pos);
                    wrongPosition += pos;
                }
            }
        return wrongPosition;
    }                 // sum of Manhattan distances between blocks and goal

    public boolean isGoal() {
        return hamming() == 0;
    }


    // a board obtained by exchanging two adjacent blocks in the same row
    public Board twin() {
        int[][] newBlocks = copyBoard(blocks);
        int i = StdRandom.uniform(N);
        int j = StdRandom.uniform(N);
        int jj = 0;
        if (j + 1 == N) jj -= 1;
        else jj += 1;

        newBlocks[i][jj] = blocks[i][j];
        newBlocks[i][j] = blocks[i][jj];
        return new Board(newBlocks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        int[][] b = ((Board) o).blocks;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.blocks[i][j] != b[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.blocks);
    }

    //
//    public boolean equals(Object y)        // does this board equal y?
//
    class BoardComparator implements Comparator<Board> {
        @Override
        public int compare(Board o1, Board o2) {
            return o1.manhattan() < o2.manhattan() ? -1 : o1.manhattan() > o2.manhattan() ? 1 : 0;
        }
    }


    class BoardQ implements Iterable<Board> {

        MinPQ<Board> neighbors = new MinPQ<>(4, new BoardComparator());

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
//            if(!Board.this.equals(board))
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
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                stringBuilder.append(this.blocks[i][j] + " ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private int[][] copyBoard(int[][] init) {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = init[i][j];
            }
        }
        return copy;
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        final int EMPTY = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                blocks[i][j] = in.readInt();
                if(blocks[i][j] == 0) blocks[i][j] = EMPTY;
            }
        Board initial = new Board(blocks);

//        // solve the puzzle
        Solver solver = new Solver(initial);
//
//        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
