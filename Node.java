
/**
 * ABOUT This is the node class for Stack
 */
public class Node {
    private Position data;
    private Node next;

    public Node() {
        data = null;
        next = null;
    }// Node constructor

    public Position getData() {
        return data;
    }// data

    public Node getNext() {
        return next;
    }// getNext

    public void setData(Position newData) {
        this.data = newData;

    }// setData

    public void setNext(Node newNext) {
        this.next = newNext;
    }// setNext

}// Node
