import java.util.Iterator;

public class StackForTest<T> implements Iterable<T> {
    // by doing so, we promise to provide a iterator() method
    // To implement linked lists, first create a nested class to define Node:
    private class Node {
        T item;
        Node next;
    }

    // create variables:
    private Node first;  // top of stack
    private int N;       // number of items

    // isEmpty
    public boolean isEmpty() {
        return this.first == null;
    }

    // size
    public int size() {
        return this.N;
    }

    // To string
    public String toString() {
        Node tmp = this.first;
        String result = "";

        while (tmp != null) {
            result += "->" + tmp.item;

            tmp = tmp.next;
        }

        return result;
    }

    // push
    public void push(T item) {   // insert a Node at the top/beginning:
        // save a link to the list, create a new node for the beginning, set the instance var in the new node
        Node oldFirst = this.first;
        this.first = new Node();
        this.first.item = item;
        this.first.next = oldFirst;
        this.N++;
    }

    // pop
    public T pop(String s) {      // remove a Node at the top/beginning:
        T item = this.first.item;

        this.first = this.first.next;
        this.N--;

        return item;     // the value of the removed Node
    }

    // returns the most recently inserted item on the stack (without popping it)
    public T peek() {
        return this.first.item;
    }

    // provide an iterator() method:
    public Iterator<T> iterator() {
        return new ListIterator(); // we promise to provide a nested class "ListIterator"
    }

    // provide a nested class ListIterator, or use default, that must implements the hasNext() and next() methods
    private class ListIterator implements Iterator<T> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            T item = current.item;

            current = current.next;

            return item;
        }
    }


    // test client


    public static void main(String[] args) {

    }


}
