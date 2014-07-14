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
        if (initial.isGoal()) {
            isSolvable = true;
//            listSolution.add(initial);
        } else {
            Board board = initial;
            Board twinBoard = initial.twin();
            while (!isSolvable && !isTwinSolvable) {
                board = runAlgorithmStep(board, prevBoard, false);
                twinBoard = runAlgorithmStep(twinBoard, prevTwinBoard, true);
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
        if (searchBoard.isGoal() && !isTwin) {
            isSolvable = true;
            return searchBoard;
        } else if (searchBoard.isGoal()) {
            isTwinSolvable = true;
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
        if (isSolvable) return listSolution.size();
        else return -1;
    }                      // min number of moves to solve initial board; -1 if no solution

    public Iterable<Board> solution() {
        if (listSolution.isEmpty()) return null;
        else return listSolution;
    }      // sequence of boards in a shortest solution; null if no solution

    public static void main(String[] args) {

    }
}
