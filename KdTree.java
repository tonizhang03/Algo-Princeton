import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

// for assignment 5
// a mutable data type that uses a 2d-tree
// A 2d-tree is a generalization of a BST to two-dimensional keys.
// The idea is to build a BST with points in the nodes, using the x- and y-coordinates of the points as keys in strictly alternating sequence.

public class KdTree {
    private Node root;

    private static class Node {
        private final Point2D point;
        private final double key;
        // private final double value;
        private Node left, right;
        private int level;
        private int size;

        public Node(Point2D point, double key, int size, int level) {
            this.point = point;
            this.key = key;
            // this.value = value;
            this.size = size;
            this.level = level;
        }

        // public int compareTo(Node that) {
        //     if (that == null || this.getClass() != that.getClass())
        //         throw new IllegalArgumentException();
        //
        //     int cmp;
        //
        //     if (this.key - that.key < 0) {
        //         cmp = -1;
        //     }
        //     else if (this.key - that.key == 0 && this.value - that.value == 0) {
        //         cmp = 0;
        //     }
        //     else {
        //         cmp = 1;
        //     }
        //     return cmp;
        // }

        // public boolean equals(Object that) {
        //     if (this == that) {
        //         return true;
        //     }
        //     if (that == null || this.getClass() != that.getClass()) {
        //         return false;
        //     }
        //     Node t = (Node) that;
        //     return this.point.equals(t.point);
        // }
    }

    public KdTree() {
        root = null;
    }                               // construct an empty set of points

    public boolean isEmpty() {
        return root == null;
    }                    // is the set empty?

    public int size() {
        return size(root);
    }                  // number of points in the set

    private int size(Node node) {
        if (node == null)
            return 0;
        else
            return node.size;
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        root = insert(root, p, +0);   // root at level 0
    }           // add the point to the set (if it is not already in the set)

    private Node insert(Node node, Point2D p, int level) {
        // level is the level of node, not it's parent's level
        if (p == null) {
            throw new IllegalArgumentException();
        }

        double newKey = p.x(); // even level by default

        if (level % 2 != 0) { // odd level, key = p.y
            newKey = p.y();
        }


        if (node == null) {
            return new Node(p, newKey, 1, level);
        }


        if (newKey < node.key) {
            node.left = insert(node.left, p, level + 1);
        }
        else if (newKey >= node.key && !node.point.equals(p)) {
            node.right = insert(node.right, p, level + 1);
        }


        node.size = size(node.left) + size(node.right) + 1;
        node.level = level;
        return node;
    }

    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        return contains(root, p);
    }      // does the set contain point p?

    private boolean contains(Node node, Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();

        if (node == null) {
            return false;
        }
        boolean contains = false;

        double newKey = p.x(); // even level by default

        if (node.level % 2 != 0) { // odd level, key = p.y
            newKey = p.y();
        }

        if (newKey >= node.key && node.point.equals(p)) {
            contains = true;
        }
        else if (newKey < node.key) {
            return contains(node.left, p);
        }
        else if (newKey >= node.key) {
            return contains(node.right, p);
        }

        return contains;
    }

    public void draw() {
        if (isEmpty())
            return;
        draw(root);
    }                // draw all points to standard draw

    private void draw(Node node) {
        if (node == null) {
            return;
        }
        node.point.draw();
        // StdOut.println("point" + node.point.toString());
        draw(node.left);
        draw(node.right);
    }

    public Iterable<Point2D> range(RectHV rect) {
        // all points that are inside the rectangle (or on the boundary)
        if (rect == null)
            throw new IllegalArgumentException();

        Queue<Point2D> queue = new Queue<Point2D>();
        range(root, rect, queue);  // root at even level 0
        return queue;
    }

    private void range(Node node, RectHV rect, Queue<Point2D> queue) {
        if (node == null) {
            return;
        }
        if (rect.contains(node.point)) {
            queue.enqueue(node.point);
        }
        // even level by default
        double lo = rect.xmin();
        double hi = rect.xmax();
        double splitLine = node.point.x();

        if (node.level % 2 != 0) {  // odd level
            lo = rect.ymin();
            hi = rect.ymax();
            splitLine = node.point.y();
        }

        if (splitLine > hi) {
            // even level, check x and fall left, odd level check y and fall down
            // go left
            range(node.left, rect, queue);
        }
        else if (splitLine < lo) {
            // odd level, check x and fall right, odd level check y and fall up
            // go right
            range(node.right, rect, queue);
        }
        else {
            // split, go both subtrees
            range(node.left, rect, queue);
            range(node.right, rect, queue);
        }
    }

    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();

        double distance = root.point.distanceSquaredTo(p);

        return nearest(root, p, distance, root);
    }           // a nearest neighbor in the set to point p; null if the set is empty

    private Point2D nearest(Node node, Point2D p,
                            double minDistance, Node nearestNode) {
        if (node == null) {
            return nearestNode.point;
        }

        double keyOfP = p.x(); // even level by default
        //  boolean needCheck = true; // does it need to check the other subtree?
        // double borderDistance = Math.abs(node.point.x() - keyOfP);

        if (node.level % 2 != 0) {  // odd level
            keyOfP = p.y();
            // borderDistance = Math.abs(node.point.y() - keyOfP);
        }

        if (node.point.distanceSquaredTo(p) < minDistance) { // this point is the new nearest point
            nearestNode = node;
            minDistance = node.point.distanceSquaredTo(p);
        }

        // check if p falls left/bottom, go left
        if (keyOfP < node.key) {

            // if (node.left != null && node.left.left != null && node.left.right != null) {
            //     double candidate = Math.min(node.left.left.point.distanceSquaredTo(p),
            //                                 node.left.right.point.distanceSquaredTo(p));
            //     if (candidate < borderDistance) {
            //         needCheck = false;
            //     }
            // }

            // if (needCheck) {
            //     return nearest(node.right, p, minDistance, nearestNode);
            // }

            return nearest(node.left, p, minDistance, nearestNode);

        }
        // check if p falls right/top, go right
        else if (keyOfP > node.key) {


            // if (node.right != null && node.right.left != null && node.right.right != null) {
            //     double candidate = Math.min(node.right.left.point.distanceSquaredTo(p),
            //                                 node.right.right.point.distanceSquaredTo(p));
            //     if (candidate < borderDistance) {
            //         needCheck = false;
            //     }
            // }

            // if (needCheck) {
            //     return nearest(node.left, p, minDistance, nearestNode);
            // }
            return nearest(node.right, p, minDistance, nearestNode);

        }

        return nearestNode.point;
    }

    // public static void main(String[] args) {
    //
    //     // initialize the data structures from file
    //     String filename = args[0];
    //     In in = new In(filename);
    //
    //     KdTree kdtree = new KdTree();
    //     while (!in.isEmpty()) {
    //         double x = in.readDouble();
    //         double y = in.readDouble();
    //         Point2D p = new Point2D(x, y);
    //         kdtree.insert(p);
    //     }
    //
    //     // draw the points
    //     StdDraw.clear();
    //     StdDraw.setPenColor(StdDraw.BLACK);
    //     StdDraw.setPenRadius(0.01);
    //     kdtree.draw();
    //     StdDraw.show();
    //
    //     Point2D test = new Point2D(0.5, 0.5);
    //
    //
    //     StdDraw.setPenRadius(0.03);
    //     StdDraw.setPenColor(StdDraw.RED);
    //     test.draw();
    //
    //     kdtree.nearest(test).draw();
    //     StdOut.println(kdtree.nearest(test).toString());
    // }
}
