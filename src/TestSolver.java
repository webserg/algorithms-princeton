import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by on 10.07.2014.
 */
public class TestSolver {

    @Test
    public void testNeighbors() {
        int[][] blocks = new int[][]{{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        Board board = new Board(blocks);
        Solver solver = new Solver(board);
        Assert.assertEquals(4, solver.moves());
    }

    @Test
    public void testNeighbors0() {
        int[][] blocks = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board board = new Board(blocks);
        Solver solver = new Solver(board);
        Assert.assertEquals(0, solver.moves());
    }

    @Test
    public void testNeighborsUnsolvable() {
        int[][] blocks = new int[][]{{1, 2, 3}, {4, 5, 6}, {8, 7, 0}};
        Board board = new Board(blocks);
        Solver solver = new Solver(board);
        Assert.assertEquals(-1, solver.moves());
    }

    @Test
    public void mainTest() {
        // create initial board from file
        In in = new In("puzzle04.txt");
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        final int EMPTY = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                blocks[i][j] = in.readInt();
                if (blocks[i][j] == 0) blocks[i][j] = EMPTY;
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
