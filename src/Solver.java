import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by webserg on 10.07.2014.
 */
public class Solver {
    private final Board initial;
    private boolean isSolvable = false;
    private boolean isTwinSolvable = false;
    private List<Board> listSolution = new ArrayList<>();
    private List<Board> prevBoard = new ArrayList<>();
    private List<Board> prevTwinBoard = new ArrayList<>();
//    private List<Board> listTwinSolution = new ArrayList<>();

    public Solver(Board initial) {
        this.initial = initial;
        if (initial.isGoal()) {
            isSolvable = true;
            listSolution.add(initial);
        } else {
            Board board = this.initial;
            Board twinBoard = this.initial.twin();
            while (!isSolvable && !isTwinSolvable) {
                board = runAlgorithmStep(board, prevBoard, false);
                twinBoard = runAlgorithmStep(twinBoard, prevTwinBoard, true);
            }
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    private Board runAlgorithmStep(final Board board, List<Board> prevBoards, boolean isTwin) {
        Iterator<Board> initItr = board.neighbors().iterator();
        Board prev = board;
        Board searchBoard = initItr.next();
        while (initItr.hasNext() && prevBoard.contains(searchBoard)) {
            searchBoard = initItr.next();
        }
        if (!isTwin) listSolution.add(searchBoard);
        if (searchBoard.isGoal() && !isTwin) {
            isSolvable = true;
            return searchBoard;
        } else if (searchBoard.isGoal()) {
            isTwinSolvable = true;
            return searchBoard;
        }
        prevBoards.add(prev);
        return  searchBoard;
    }


    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        return isSolvable ? listSolution.size() : -1;
    }                      // min number of moves to solve initial board; -1 if no solution

    public Iterable<Board> solution() {
        return listSolution.isEmpty() ? null : listSolution;
    }      // sequence of boards in a shortest solution; null if no solution

    public static void main(String[] args) {

    }
}
