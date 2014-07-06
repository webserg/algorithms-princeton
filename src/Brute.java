import java.util.Arrays;

public class Brute {
    public static void main(String[] args) {

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(StdDraw.getPenRadius());
        Point[] points = readInputFile(args[0]);
        for (int i = 0; i < points.length; i++) {
            points[i].draw();
        }
        findAndDrawCollinear(points);
        StdDraw.show();
    }

    private static Point[] readInputFile(String name) {
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

    private static void drawSegment(Point... points) {
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
                            drawSegment(p, q, r, s);
                        }

                    }
                }
            }
        }
    }
}
