import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;

public class BruteCollinearPoints {
    private LineSegment[] result;

    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        // constructor
        // corner case check
        if (points == null) {
            throw new IllegalArgumentException();
        }

        Point[] input = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            input[i] = points[i];
        }
        int n = 0;
        LineSegment[] a = new LineSegment[input.length];

        for (int i = 0; i < input.length; i++) {
            if (input[i] == null) {
                throw new IllegalArgumentException();
            }
            else {
                for (int j = i + 1; j < input.length; j++) {
                    if (input[j] == null || input[j].compareTo(input[i]) == 0) {
                        throw new IllegalArgumentException();
                    }
                }
            }
        }

        for (int p = 0; p < input.length; p++) {
            for (int q = p + 1; q < input.length; q++) {
                for (int r = q + 1; r < input.length; r++) {
                    for (int s = r + 1; s < input.length; s++) {

                        double s1 = input[q].slopeTo(input[p]);
                        double s2 = input[r].slopeTo(input[p]);
                        double s3 = input[s].slopeTo(input[p]);

                        if (s1 == s2 && s2 == s3) {
                            Point[] afterSort = fourSort(input[p], input[q], input[r],
                                                         input[s]);
                            Point lo = afterSort[0];
                            Point hi = afterSort[3];

                            a[n] = new LineSegment(lo, hi);
                            n++;
                        }
                    }
                }
            }
        }
        if (n == 0) {
            result = new LineSegment[0];
        }
        else {
            result = new LineSegment[n];
            for (int i = 0; i < n; i++) {
                if (a[i] == null) {
                    a[i] = a[i + 1];
                }
                else {
                    result[i] = a[i];
                }
            }
        }
    }

    private Point[] fourSort(Point p1, Point p2, Point p3, Point p4) {
        Point[] temp = new Point[4];
        temp[0] = p1;
        temp[1] = p2;
        temp[2] = p3;
        temp[3] = p4;
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                if (temp[j].compareTo(temp[i]) < 0) {
                    Point t = temp[i];
                    temp[i] = temp[j];
                    temp[j] = t;
                }
            }
        }
        return temp;
    }

    public int numberOfSegments() {
        LineSegment[] r = this.result.clone();
        return r.length;
    }     // the number of line segments

    public LineSegment[] segments() {
        LineSegment[] r = this.result.clone();
        return r;
    }            // the line segments

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 30000);
        StdDraw.setYscale(0, 30000);
        StdDraw.setPenColor(Color.red);
        StdDraw.setPenRadius(0.03);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            System.out.println(segment.toString());
            segment.draw();
        }
        StdDraw.show();
    }
}

