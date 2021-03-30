import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

import java.util.Iterator;

// for assignment 5
// a mutable data type that represents a set of points in the unit square
public class PointSET {
    private final SET<Point2D> set;

    public PointSET() {
        set = new SET<Point2D>();
    }                                // construct an empty set of points

    public boolean isEmpty() {
        return set.isEmpty();
    }                    // is the set empty?

    public int size() {
        return set.size();
    }                      // number of points in the set

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (!contains(p)) {
            set.add(p);
        }
    }         // add the point to the set (if it is not already in the set)

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return set.contains(p);
    }         // does the set contain point p?

    public void draw() {
        for (Iterator<Point2D> it = set.iterator(); it.hasNext(); ) {
            it.next().draw();
        }
    }                       // draw all points to standard draw

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();

        Queue<Point2D> range = new Queue<Point2D>();

        for (Iterator<Point2D> it = set.iterator(); it.hasNext(); ) {
            Point2D point = it.next();
            if (rect.contains(point)) {
                range.enqueue(point);
            }
        }
        return range;

    }        // all points that are inside the rectangle (or on the boundary)

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (isEmpty()) {
            return null;
        }
        Point2D neareset = null;

        for (Iterator<Point2D> it = set.iterator(); it.hasNext(); ) {
            Point2D point = it.next();
            if (neareset == null) {
                neareset = point;
            }
            else if (point.distanceSquaredTo(p) < neareset.distanceSquaredTo(p)) {
                neareset = point;
            }
        }
        return neareset;
    }       // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args) {
        // PointSET test = new PointSET();
        //
        // int n = Integer.parseInt(args[0]);
        // for (int i = 0; i < n; i++) {
        //     double x = StdRandom.uniform(0.0, 1.0);
        //     double y = StdRandom.uniform(0.0, 1.0);
        //     StdOut.printf("%8.6f %8.6f\n", x, y);
        //     Point2D p = new Point2D(x, y);
        //     test.insert(p);
        // }
        // StdDraw.setPenRadius(0.02);
        // test.draw();
        // RectHV rect = new RectHV(0.3, 0.3, 0.7, 0.7);
        // rect.draw();
        //
        // StdDraw.setPenRadius(0.03);
        // StdDraw.setPenColor(StdDraw.RED);
        // for (Point2D p : test.range(rect))
        //     p.draw();
    }
    // unit testing of the methods (optional)
}
