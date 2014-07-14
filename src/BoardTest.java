import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by webserg on 12.07.2014.
 */
public class BoardTest {
    static final int EMPTY = 0;
    @Test
    public void testHamming() {
        int[][] blocks = new int[][]{{8, 1, 3}, {4, EMPTY, 2}, {7, 6, 5}};
        Board board = new Board(blocks);
        Assert.assertEquals(5, board.hamming());
    }

    @Test
    public void testManhattan() {
        int[][] blocks = new int[][]{{8, 1, 3}, {4, EMPTY, 2}, {7, 6, 5}};
        Board board = new Board(blocks);
        Assert.assertEquals(10, board.manhattan());
    }

    @Test
    public void testManhattanZero() {
        int[][] blocks = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, EMPTY}};
        Board board = new Board(blocks);
        Assert.assertEquals(0, board.manhattan());
    }


    @Test
    public void testManhattanMax() {
        int[][] blocks = new int[][]{{EMPTY, 8, 7}, {6, 5, 4}, {3, 2, 1}};
        Board board = new Board(blocks);
        Assert.assertEquals(20, board.manhattan());
    }


    @Test
    public void testManhattan4() {
        int[][] blocks = new int[][]{{EMPTY, 2, 3}, {4, 5, 6}, {7, 8, 1}};
        Board board = new Board(blocks);
        Assert.assertEquals(4, board.manhattan());
    }

    @Test
    public void testManhattan5() {
        int[][] blocks = new int[][]{{1, EMPTY, 3}, {4, 5, 6}, {7, 8, 2}};
        Board board = new Board(blocks);
        Assert.assertEquals(3, board.manhattan());
    }

    @Test
    public void testNeighbors() {
        int[][] blocks = new int[][]{{EMPTY, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        Board board = new Board(blocks);
        Assert.assertEquals(new Board(new int[][]{{1, EMPTY, 3}, {4, 2, 5}, {7, 8, 6}}), board.neighbors().iterator().next());
    }

    @Test
    @Ignore
    public void testTwin() {
        int[][] blocks = new int[][]{{EMPTY, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        Board board = new Board(blocks);
        Assert.assertEquals(board, board.twin());
    }


}
