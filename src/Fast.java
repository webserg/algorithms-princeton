import java.util.*;

public class Fast {
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
        List<Point> list = Arrays.asList(points);
        for (int i = 0; i < list.size(); i++) {
            List<Point> other = new ArrayList<>(list);
            other.remove(i);
            Point mainpoint = list.get(i);
            Collections.sort(other, mainpoint.SLOPE_ORDER);
            ListIterator<Point> iter = other.listIterator();
            double slope = mainpoint.slopeTo(iter.next());
            List<Point> segment = new ArrayList<>();
            while (iter.hasNext()) {
                Point p = iter.next();
                if (slope == mainpoint.slopeTo(p)) {
                    segment.add(p);

                } else {
                    slope = mainpoint.slopeTo(p);
                    if (segment.size() > 2) {
                        segment.add(mainpoint);
                        drawSegment(segment);
                    }
                    segment.clear();
                }
            }
        }

    }

    public static void drawSegment(List<Point> segment) {
        Collections.sort(segment);
        for (int i = 0; i < segment.size(); i++) {
            if (i != 0) System.out.print(" -> ");
            System.out.print(segment.get(i));

        }
        for (int i = 0; i < segment.size() - 1; i++) {
            segment.get(i).drawTo(segment.get(i + 1));
        }
        System.out.println();
    }

}
