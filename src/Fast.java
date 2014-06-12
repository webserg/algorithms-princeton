import java.util.Arrays;

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
        for (int j = 0; j < points.length; j++) {
            Point mainPoint = points[j];
            Point[] others = Arrays.copyOfRange(points, 1, points.length);
            Arrays.sort(others, mainPoint.SLOPE_ORDER);
//            for (int i = 0; i < others.length; i++) {
//                System.out.println(p.slopeTo(others[i]));
//            }
            for (int i = 0, k = 1; i < others.length - 1; i++) {
                if (mainPoint.slopeTo(others[i]) == mainPoint.slopeTo(others[i + 1])) {
                    k++;
                } else {

                    if (k >= 3) {
                        i++;
                        Point[] segment = (Arrays.copyOfRange(others, i - k, i));
                        Arrays.sort(segment);
                        System.out.print(mainPoint);
                        for (int y = 0; y < segment.length; y++) {
                            System.out.print(" -> ");
                            System.out.print(points[y]);
                            if (y == 0) {
                                mainPoint.drawTo(segment[y]);
                            } else if (y < segment.length - 1) {
                                segment[y].drawTo(segment[y + 1]);
                            }

                        }

                        System.out.println();
                    }
                    k = 1;

                }
            }
        }
    }
}
