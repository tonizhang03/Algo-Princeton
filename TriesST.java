// tries symbol-table for string search

import edu.princeton.cs.algs4.Queue;

public class TriesST<Value> {
    private int N;
    private static int R = 256; // radix
    private Node root;

    private static class Node {
        private Object val; // use Object instead of Value since no generic array creation in Java
        private Node[] next = new Node[R]; // each Node has an array of links and a value
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return (Value) x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            x.val = val; // assign value to the last char of the string
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d + 1); // recursive call
        return x;
    }

    public int size() {
        // eager implementation: maintain an instance variable N
        return this.N;
    }

    // ---------
    public Iterable<String> keys() {
        Queue<String> queue = new Queue<String>();
        collect(root, "", queue);
        return queue;
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> queue = new Queue<String>();
        Node x = get(root, prefix, 0); // find subtrie for all keys beginning with prefix
        collect(x, prefix, queue); // collect keys in that subtrie
        return queue;
    }

    private void collect(Node x, String prefix, Queue<String> q) {
        // for keys() and keysWithPrefix()
        if (x == null) {
            return;
        }
        if (x.val != null) {
            q.enqueue(prefix);
        }
        for (char c = 0; c < R; c++) {
            collect(x.next[c], prefix + c, q);
        }
    }

    // --------
    public Iterable<String> keysThatMatch(String pat) {
        // wildcard search
        Queue<String> q = new Queue<String>();
        collect(root, "", pat, q);
        return q;
    }

    public void collect(Node x, String pre, String pat, Queue<String> q) {
        // for keysThatMatch()
        int d = pre.length();
        if (x == null) {
            return;
        }
        if (d == pat.length() && x.val != null) {
            q.enqueue(pre);
        }
        if (d == pat.length()) {
            return;
        }
        char next = pat.charAt(d);
        for (char c = 0; c < R; c++) {
            if (next == '.' || next == c) {
                collect(x.next[c], pre + c, pat, q);
            }
        }
    }

    // ---------
    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0); // s is query
        return s.substring(0, length);
    }

    private int search(Node x, String s, int d, int length) {
        // for longestPrefixOf()
        if (x == null) {
            return length;
        }
        if (x.val != null) {
            length = d;
        }
        if (d == s.length()) {
            return length;
        }
        char c = s.charAt(d);
        return search(x.next[c], s, d + 1, length);
    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            x.val = null;
        }
        else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }
        if (x.val != null) {
            return x;
        }
        for (char c = 0; c < R; c++) {
            if (x.next[c] != null) {
                return x;
            }
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
