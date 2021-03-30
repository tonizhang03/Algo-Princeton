import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.awt.Color;
import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] s;


    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 or more points

        // corner case
        if (points == null) {
            throw new IllegalArgumentException();
        }

        Point[] input = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            input[i] = points[i];
        }

        // corner case
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

        // constructors:
        int n = 0;
        Point p;
        int cap = points.length * points.length;

        Point[][] matrix = new Point[cap][2];

        // first loop: use each point as the pivot and do slope sort
        for (int i = 0; i < points.length; i++) {
            p = points[i];

            // here should apply SlopeOrder from Point class:
            Arrays.sort(input, p.slopeOrder());

            int count;

            // Second loop: for each sorted point, check if the following keys are equal
            for (int j = 1; j < input.length; j = j + count) {

                count = 1;
                int g = 2;
                Point[] group = new Point[input.length];
                group[0] = input[0];
                group[1] = input[j];

                // Third loop: find all equal keys that have the same slope to the pivot
                for (int k = j + 1; k < input.length; k++) {

                    if (input[k].slopeTo(input[0]) == input[j].slopeTo(input[0])) {
                        group[g] = input[k];
                        count++;
                        g++;
                    }
                    else {
                        break;
                    }
                }

                // check if the number of equal keys are more than 4
                if (g > 3) {
                    Point[] sortGroup = new Point[g];

                    for (int t = 0; t < g; t++) {
                        sortGroup[t] = group[t];
                    }

                    // find end points of this segment
                    segmentSort(sortGroup);

                    Point[] endPoints = { sortGroup[0], sortGroup[sortGroup.length - 1] };

                    // check if this group(segment) is duplicated. if not, add it to matrix
                    if (!duplicated(matrix, endPoints)) {
                        matrix[n] = endPoints;
                        n++;
                    }
                }
            }
        }
        // After all the loops, now matrix has saved all the end points pairs, non repeating,
        // make matrix into segments
        if (n == 0) {
            this.s = new LineSegment[0];
        }
        else {
            this.s = new LineSegment[n];
            for (int i = 0; i < n; i++) {
                if (matrix[i] != null && matrix[i][0] != null && matrix[i][1] != null) {
                    this.s[i] = new LineSegment(matrix[i][0], matrix[i][1]);
                }
            }
        }
    }

    // private class CompareSlope implements Comparator<Point> {
    //     private Point p;
    //
    //     public CompareSlope(Point p) {
    //         this.p = p;
    //     }
    //
    //     public int compare(Point a, Point b) {
    //         return Double.compare(a.slopeTo(this.p), b.slopeTo(this.p));
    //     }
    // }

    private boolean duplicated(Point[][] inputMatrix, Point[] points) {
        boolean state = false;
        for (int m = 0; m < inputMatrix.length; m++) {
            if ((points[0].equals(inputMatrix[m][0])) && (points[1].equals(inputMatrix[m][1]))) {
                state = true;
                break;
            }
        }
        return state;
    }

    private void segmentSort(Point[] input) {
        // sort each collinear  group of points

        Arrays.sort(input);
    }

    public int numberOfSegments() {
        LineSegment[] segments = this.s.clone();
        return segments.length;
    }    // the number of line segments

    public LineSegment[] segments() {
        LineSegment[] segments = this.s.clone();
        return segments;
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
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenColor(Color.red);
        StdDraw.setPenRadius(0.03);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
