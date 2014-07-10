import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by webserg on 06.07.2014.
 */
public class PercolationTestSuite {
    @Test
    public void testInput6() {
        Percolation percolation = new Percolation(6);

        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
                percolation.open(i, j);
                percolation.isFull(i, j);
                Assert.assertTrue(percolation.isOpen(i, j));
                boolean res = percolation.percolates();
            }
        }
    }

}
