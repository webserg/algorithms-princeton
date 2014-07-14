import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by webserg on 10.07.2014.
 * . Organize your program by creating an immutable data type Board with the following API:
 */
public class Board {
    private final int N;
    private static final int EMPTY = 9;
    private final int[][] blocks;

    public Board(final int[][] blocks) {
        N = blocks.length;
        this.blocks = Arrays.copyOf(blocks, blocks.length);
    }           // construct a board from an N-by-N array of blocks

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
                if (cur + 1 != blocks[i][j] && blocks[i][j] != N * N) {//empty block is uncounted
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
                if (cur + 1 != blocks[i][j] && blocks[i][j] != N * N) {
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
        int[][] newBlocks = Arrays.copyOf(blocks, blocks.length);
        int i = StdRandom.uniform(N);
        int j = StdRandom.uniform(N);
        int ii = 0;
        if (i + 1 == N) ii -= 1;
        else ii += 1;

        newBlocks[ii][j] = blocks[i][j];
        newBlocks[i][j] = blocks[ii][j];
        return new Board(newBlocks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Arrays.equals(this.blocks, ((Board) o).blocks);
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
            int[][] b = Arrays.copyOf(searchNode.blocks, searchNode.blocks.length);
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
                stringBuilder.append(this.blocks[i][j] + "\\t");
            }
            stringBuilder.append("\\n");
        }
        return stringBuilder.toString();
    }
}
