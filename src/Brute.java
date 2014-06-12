import java.util.Arrays;

public class Brute {
    public static void main(String[] args) {

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(StdDraw.getPenRadius());
        Point[] points = Point.readInputFile("input8.txt");
        for (int i = 0; i < points.length; i++) {
            points[i].draw();
        }
        findAndDrawCollinear(points);
        StdDraw.show();
    }

    private static void findAndDrawCollinear(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            final Point p = points[i];
            for (int j = i + 1; j < points.length; j++) {
                final Point q = points[j];
                for (int k = j + 1; k < points.length; k++) {
                    final Point r = points[k];
                    for (int l = k + 1; l < points.length; l++) {
                        final Point s = points[l];
                        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(r) == p.slopeTo(s)) {
                            Point.drawSegment(p, q, r, s);
                        }

                    }
                }
            }
        }
    }
}
