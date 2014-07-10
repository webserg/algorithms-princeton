/**
 * Created by webserg on 07.06.2014.
 * usage PercolationStats N T
 */
public class PercolationStats {
    private final int N;
    private final int T;
    private double[] thresholdsOverTimes;

    /**
     * perform T independent computational experiments on an N-by-N grid
     *
     * @param n grid
     * @param t T times
     */
    public PercolationStats(int n, int t) {
        T = t;
        N = n;
        thresholdsOverTimes = new double[T];
        double totalSites = N * N;
        for (int i = 0; i < T; i++) {
            int openSites = runSimulation();
            thresholdsOverTimes[i] = openSites / totalSites;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholdsOverTimes);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholdsOverTimes);
    }

    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev()) / Math.sqrt(T);
    }

    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev()) / Math.sqrt(T);
    }

    private int runSimulation() {
        Percolation percolation = new Percolation(N);
        int i, j;
        int openSites = 0;
        while (!percolation.percolates()) {
            i = StdRandom.uniform(1, N+1);
            j = StdRandom.uniform(1, N+1);
            if (!percolation.isOpen(i, j)) {
                percolation.open(i, j);
                openSites++;
            }
        }
        return openSites;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            StdOut.println("Wrong arguments. Usage:");
            StdOut.println("  java PercolationStats N T");
        } else {
            int N = Integer.parseInt(args[0]);
            int T = Integer.parseInt(args[1]);
            PercolationStats percolationStats = new PercolationStats(N, T);
            StdOut.println("mean                    = " + percolationStats.mean());
            StdOut.println("stddev                  = " + percolationStats.stddev());
            StdOut.println("95% confidence interval = " + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi());
        }
    }
}
