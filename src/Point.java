/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Arrays;
import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new PointComparator(this);

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public Double slopeTo(Point that) {
        double result = (double) (that.y - this.y) / (that.x - this.x);

        if (result == Double.NEGATIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        } else if (Double.isNaN(result)) {
            return Double.NEGATIVE_INFINITY;
        }
        return result;

    }

    public static void drawSegment(Point... points) {
        Arrays.sort(points);
        for (int i = 0; i < points.length; i++) {
            if (i != 0) System.out.print(" -> ");
            System.out.print(points[i]);

        }
        for (int i = 0; i < points.length - 1; i++) {
            points[i].drawTo(points[i + 1]);
        }
        System.out.println();
    }


    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates

    public int compareTo(Point that) {
        if (this.y < that.y) {
            return -1;
        } else if (this.y == that.y) {
            if (this.x < that.x) return -1;
            else if (this.x == that.x) return 0;
            else return 1;
        }
        return 1;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        testFile();
//        System.out.println(Arrays.toString(readInputFile("input6.txt")));
//        System.out.println(Arrays.toString(readInputFile("input8.txt")));
    }

    private static void testFile() {
        In in;
        try {
            in = new In("InTest.txt");
            while (!in.isEmpty()) {
                String s = in.readLine();
                System.out.println(s);
            }
            System.out.println();
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println();
    }

    static Point[] readInputFile(String name) {
        In in;
        Point[] points = null;
        try {
            in = new In(name);
            int N = Integer.parseInt(in.readLine());

            points = new Point[N];
            for (int i = 0; i < N; i++) {
                String line = in.readLine();
//                System.out.println(line);
                String[] lineArray = line.trim().split("(\\s+)");
//                System.out.println(Arrays.toString(lineArray));
                points[i] = new Point(Integer.parseInt(lineArray[0]), Integer.parseInt(lineArray[1]));
            }
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return points;
    }
}

class PointComparator implements Comparator<Point> {

    Point p;

    PointComparator(Point _o) {
        this.p = _o;
    }

    @Override
    public int compare(Point o1, Point o2) {
        return (p.slopeTo(o1).compareTo(p.slopeTo(o2)));
    }
}
