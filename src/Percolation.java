/**
 * Created by webserg on 07.06.2014.
 * To model a percolation system
 */
public class Percolation {
    private static final int OPEN = 1;
    private static final int CLOSE = 0;

    private int[][] grid;
    private WeightedQuickUnionUF uf;
    private final int N;
    private int top;

    private int bottom;

    /**
     * create N-by-N grid, with all sites blocked
     *
     * @param n number
     */
    public Percolation(int n) {
        argCheck(n);
        N = n;
        grid = new int[N][N];
        uf = new WeightedQuickUnionUF(N * N + 2);
        top = N * N;
        bottom = N * N + 1;
        for (int i = 0; i < N; i++) {
            uf.union(top, seed(0, i));
        }
        for (int i = 0; i < N; i++) {
            uf.union(bottom, seed(N - 1, i));
        }
    }

    /**
     * open site (row i, column j) if it is not already
     *
     * @param i number
     * @param j number
     */
    public void open(int i, int j) {
        i -= 1;
        j -= 1;
        idxCheck(i, j);
        if (grid[i][j] == CLOSE) {
            grid[i][j] = OPEN;
            int cur = seed(i, j);
            if (i - 1 >= 0 && _isOpen(i - 1, j)) {
                uf.union(cur, seed(i - 1, j));
            }
            if (i + 1 < N && _isOpen(i + 1, j)) {
                uf.union(cur, seed(i + 1, j));
            }
            if (j - 1 >= 0 && _isOpen(i, j - 1)) {
                uf.union(cur, seed(i, j - 1));
            }
            if (j + 1 < N && _isOpen(i, j + 1)) {
                uf.union(cur, seed(i, j + 1));
            }
        }
    }

    private int seed(int i, int j) {
        return N * i + j;
    }

    /**
     * is site (row i, column j) open?
     *
     * @param i number
     * @param j number
     * @return boolean
     */
    public boolean isOpen(int i, int j) {
        i -= 1;
        j -= 1;
        return _isOpen(i, j);
    }

    /**
     * is site (row i, column j) open?
     *
     * @param i number
     * @param j number
     * @return boolean
     */
    private boolean _isOpen(int i, int j) {
        idxCheck(i, j);
        return grid[i][j] == OPEN;
    }

    /**
     * // is site (row i, column j) full?
     *
     * @param i row
     * @param j column
     * @return boolean
     */
    public boolean isFull(int i, int j) {
        i -= 1;
        j -= 1;
        idxCheck(i, j);
        return uf.connected(top, bottom) && uf.connected(top, seed(i, j));
    }

    /* Validate the indexes, throws an exception if not valid */
    private void idxCheck(int i, int j) {
        if (i < 0 || i > N || j < 0 || j > N)
            throw new IndexOutOfBoundsException("Position (" + i + ", " + j + ") out of bounds");
    }

    private void argCheck(int n) {
        if (n <= 0)
            throw new IllegalArgumentException(" N <= 0 ");
    }

    public boolean percolates() {
        return uf.connected(top, bottom);
    }
}

