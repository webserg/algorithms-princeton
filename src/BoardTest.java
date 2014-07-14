import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by webserg on 12.07.2014.
 */
public class BoardTest {
    @Test
    public void testHamming() {
        int[][] blocks = new int[][]{{8, 1, 3}, {4, 9, 2}, {7, 6, 5}};
        Board board = new Board(blocks);
        Assert.assertEquals(5, board.hamming());
    }

    @Test
    public void testManhattan() {
        int[][] blocks = new int[][]{{8, 1, 3}, {4, 9, 2}, {7, 6, 5}};
        Board board = new Board(blocks);
        Assert.assertEquals(10, board.manhattan());
    }

    @Test
    public void testManhattanZero() {
        int[][] blocks = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Board board = new Board(blocks);
        Assert.assertEquals(0, board.manhattan());
    }


    @Test
    public void testManhattanMax() {
        int[][] blocks = new int[][]{{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};
        Board board = new Board(blocks);
        Assert.assertEquals(20, board.manhattan());
    }


    @Test
    public void testManhattan4() {
        int[][] blocks = new int[][]{{9, 2, 3}, {4, 5, 6}, {7, 8, 1}};
        Board board = new Board(blocks);
        Assert.assertEquals(4, board.manhattan());
    }

    @Test
    public void testManhattan5() {
        int[][] blocks = new int[][]{{1, 9, 3}, {4, 5, 6}, {7, 8, 2}};
        Board board = new Board(blocks);
        Assert.assertEquals(3, board.manhattan());
    }
}
