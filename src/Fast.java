import java.util.*;

public class Fast {
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

    private static void findAndDrawCollinear(Point[] points) {
        List<Point> list = Arrays.asList(points);
        Collections.sort(list);
        List<List<Point>> segments = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List<Point> other = new ArrayList<>(list);
            Point mainpoint = list.get(i);
            other.remove(i);
            Collections.sort(other, mainpoint.SLOPE_ORDER);
            ListIterator<Point> iter = other.listIterator();
            Point p = iter.next();
            Double slope = mainpoint.slopeTo(p);
            List<Point> segment = new ArrayList<>();
            segment.add(p);
            while (iter.hasNext()) {
                p = iter.next();
                if (slope.equals(mainpoint.slopeTo(p))) {
                    segment.add(p);

                } else {
                    if (segment.size() >= 3) {
                        segment.add(mainpoint);
                        drawSegment(segments, segment);
                    }
                    segment.clear();
                    slope = mainpoint.slopeTo(p);
                    segment.add(p);
                }
            }
        }

    }

    private static void drawSegment(List<List<Point>> segments, List<Point> segment) {
        List<Point> s = new ArrayList<>(segment);
        Collections.sort(s);
        int r = 0;
        for (int i = 0; i < segments.size(); i++) {
            List<Point> points = segments.get(i);
            if (s.size() == points.size() && points.containsAll(s)) {
                return;
            }
        }


        segments.add(s);
        for (int i = 0; i < s.size(); i++) {
            if (i != 0) System.out.print(" -> ");
            System.out.print(s.get(i));

        }
        for (int i = 0; i < s.size() - 1; i++) {
            s.get(i).drawTo(s.get(i + 1));
        }
        System.out.println();
    }

}
