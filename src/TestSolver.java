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
        Solver.main(new String[]{"puzzle04.txt"});
    }

    @Test
    public void mainTestPuzzle01() {
        Solver.main(new String[]{"puzzle01.txt"});

    }

    @Test
    public void mainTestPuzzle02() {
        Solver.main(new String[]{"puzzle02.txt"});

    }

    @Test
    public void mainTestPuzzle03() {
        Solver.main(new String[]{"puzzle03.txt"});

    }

    @Test
    public void mainTestPuzzle10() {
        Solver.main(new String[]{"puzzle10.txt"});
    }
}
