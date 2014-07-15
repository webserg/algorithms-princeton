import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created on 10.07.2014.
 * solver of puzzle.
 */
public class Solver {
    private boolean isSolvable = false;
    private boolean isTwinSolvable = false;
    private List<Board> listSolution = new ArrayList<>();

    private List<Board> listTwinSolution = new ArrayList<>();

    public Solver(Board initial) {
        List<Board> prevBoard = new ArrayList<>();
        List<Board> prevTwinBoard = new ArrayList<>();
        listSolution.add(initial);
        if (initial.isGoal() || initial.dimension() == 0) {
            isSolvable = true;
        } else {
            Board board = initial;
            Board twinBoard = initial.twin();
            int i = 0;
            while (!isSolvable && !isTwinSolvable) {
                if (i > (initial.dimension() + 1) * 100) {
                    break;
                }
                board = runAlgorithmStep(board, prevBoard, false);
                twinBoard = runAlgorithmStep(twinBoard, prevTwinBoard, true);
                i++;
            }
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    private Board runAlgorithmStep(final Board board, List<Board> prevBoards, boolean isTwin) {
        Iterator<Board> initItr = board.neighbors().iterator();
        Board searchBoard = initItr.next();
        while (initItr.hasNext() && prevBoards.contains(searchBoard)) {
            searchBoard = initItr.next();
        }
        if (!isTwin) listSolution.add(searchBoard);
        else listTwinSolution.add(searchBoard);
        if (searchBoard.isGoal()) {
            if (!isTwin) isSolvable = true;
            else isTwinSolvable = true;
            return searchBoard;
        }
        prevBoards.clear();
        prevBoards.add(board);
        return searchBoard;
    }


    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        if (isSolvable) {
//            for (Board b : listSolution) {
//                System.out.println(b.toString());
//            }
            if (listSolution.size() == 0) return 0;
            else return listSolution.size() - 1;
        } else {
//            if (listSolution != null && listSolution.size() > 1)
//                System.out.println(listSolution.get(0).toString());
            return -1;
        }
    }                      // min number of moves to solve initial board; -1 if no solution

    public Iterable<Board> solution() {
        if (!isSolvable) return null;
        else return listSolution;
    }      // sequence of boards in a shortest solution; null if no solution

    public static void main(String[] args) {
        runSolver(args[0]);
    }

    private static int runSolver(String fileName) {
        // create initial board from file
        int res=-1;
        In in = new In(fileName);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                blocks[i][j] = in.readInt();
            }
        Board initial = new Board(blocks);

//        // solve the puzzle
        Solver solver = new Solver(initial);
//
//        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            res = solver.moves();
            StdOut.println("Minimum number of moves = " + res);
            for (Board board : solver.solution())
                StdOut.println(board);
        }
        return res;
    }
}
