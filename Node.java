
/**
 * ABOUT This is the node class for Stack
 */
public class Node {
    private Position data;
    private Node next;
    private int row, col;

    public Node() {
        data = null;
        next = null;
        row = col = -1;
    }// Node constructor

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int rowNum) {
        this.row = rowNum;
    }

    public void setCol(int colNum) {
        this.col = colNum;
    }

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
