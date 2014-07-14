import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by webserg on 10.07.2014.
 */
public class Solver {
    private final Board initial;
    private boolean isSolvable = false;
    private List<Board> listSolution = new ArrayList<>();
//    private List<Board> listTwinSolution = new ArrayList<>();

    public Solver(Board initial) {
        this.initial = initial;
        if (initial.isGoal()) {
            isSolvable = true;
            listSolution.add(initial);
        } else {
            runAlgorithm();
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    private void runAlgorithm() {
        Board twin = initial.twin();
        Iterator<Board> initItr = initial.neighbors().iterator();
        Iterator<Board> twinItr = twin.neighbors().iterator();
        Board prev = initial;
        Board prevTwin = twin;
        while (initItr.hasNext() && twinItr.hasNext()) {

            Board initSearchBoard = initItr.next();
            Board twinSearchBoard = twinItr.next();
            listSolution.add(initSearchBoard);
//            listTwinSolution.add(twinSearchBoard);
            if (initSearchBoard.isGoal()) {
                isSolvable = true;
                return;
            } else if (twinSearchBoard.isGoal()) {
                isSolvable = false;
                return;
            }
            initItr = initSearchBoard.neighbors().iterator();
            removePrev(initItr, prev);
            prev = initSearchBoard;
            twinItr = twinSearchBoard.neighbors().iterator();
            removePrev(twinItr, prevTwin);
            prevTwin = twinSearchBoard;
        }
    }

    private void removePrev(Iterator<Board> initItr, Board prev) {
        while (initItr.hasNext()) {
            Board cur = initItr.next();
            if (cur.equals(prev)) {
                initItr.remove();
            }
        }
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
