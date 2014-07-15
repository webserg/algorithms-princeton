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

    private static int[][] getBlocks(String fileName) {
        In in = new In(fileName);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                blocks[i][j] = in.readInt();
            }
        return blocks;
    }


    @Test
    public void mainTest() {
        int[][] blocks = getBlocks("puzzle04.txt");
        Board initial = new Board(blocks);
        Solver solver = new Solver(initial);
        Assert.assertEquals(4, solver.moves());
    }

    @Test
    public void mainTestPuzzle01() {
        int[][] blocks = getBlocks("puzzle01.txt");
        Board initial = new Board(blocks);
        Solver solver = new Solver(initial);
        Assert.assertEquals(1, solver.moves());
    }

    @Test
    public void mainTestPuzzle02() {
        int[][] blocks = getBlocks("puzzle02.txt");
        Board initial = new Board(blocks);
        Solver solver = new Solver(initial);
        Assert.assertEquals(2, solver.moves());
    }

    @Test
    public void mainTestPuzzle03() {
        int[][] blocks = getBlocks("puzzle03.txt");
        Board initial = new Board(blocks);
        Solver solver = new Solver(initial);
        Assert.assertEquals(3, solver.moves());
    }

    @Test
    public void mainTestPuzzle10() {
        int[][] blocks = getBlocks("puzzle10.txt");
        Board initial = new Board(blocks);
        Solver solver = new Solver(initial);
        Assert.assertEquals(10, solver.moves());
    }

    @Test
    public void mainTestPuzzle5() {
        int[][] blocks = getBlocks("puzzle05.txt");
        Board initial = new Board(blocks);
        Solver solver = new Solver(initial);
        Assert.assertEquals(5, solver.moves());
    }

    @Test
    @Ignore
    public void mainTestPuzzle31() {
        int[][] blocks = getBlocks("puzzle31.txt");
        Board initial = new Board(blocks);
        Solver solver = new Solver(initial);
        Assert.assertEquals(31, solver.moves());
    }

    @Test
    @Ignore
    public void mainTestPuzzle28() {
        int[][] blocks = getBlocks("puzzle28.txt");
        Board initial = new Board(blocks);
        Solver solver = new Solver(initial);
        Assert.assertEquals(28, solver.moves());
    }

    @Test
    @Ignore
    public void mainTestPuzzle16() {
        int[][] blocks = getBlocks("puzzle16.txt");
        Board initial = new Board(blocks);
        Solver solver = new Solver(initial);
        Assert.assertEquals(16, solver.moves());
    }
}
