import java.util.Arrays;

public class Brute {
    public static void main(String[] args) {
//        testFile();
//        System.out.println(Arrays.toString(readInputFile("input6.txt")));
//        System.out.println(Arrays.toString(readInputFile("input8.txt")));
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(StdDraw.getPenRadius());
        Point[] points = readInputFile("input8.txt");
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
                    if (p.slopeTo(q) == p.slopeTo(r))
                        for (int l = k + 1; l < points.length; l++) {
                            final Point s = points[l];
                            if (p.slopeTo(r) == p.slopeTo(s)) {
                                System.out.println(p + " -> " + q + " -> " + r + " -> " + s);
                                p.drawTo(q);
                                q.drawTo(r);
                                r.drawTo(s);
                            }

                        }
                }
            }
        }
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
}
