import sun.invoke.empty.Empty;

/**
 * Created by webserg on 10.07.2014.
 */
public class TestSolver {
    public static void main(String[] args) {
        // create initial board from file
        In in = new In("puzzle-unsolvable3x3.txt");
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
