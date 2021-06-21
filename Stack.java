public class Stack {

    /**
     * ABOUT This is the stack class built using a linkedlist
     */
    private Node top;
    private int size;

    public Stack() {
        this.top = null;
        this.size = 0;
    }// Stack constructor

    public void push(Position data) {

        /**
         * OBJECTIVE Inserts a data at the very top of the stack and moves the rest of
         * the data by one position
         */
        Node temp = new Node();
        temp.setData(data);
        temp.setNext(top);
        top = temp;
        size++;
    }// push

    public void pop() {

        /**
         * OBJECTIVE Removes the very first element of the stack
         */
        top = (top).getNext();
    }// pop

    public boolean isEmpty() {

        /**
         * OBJECTIVE Returns true if the stack is empty
         */
        return top == null;
    }// isEmpty

    public Node top() {
        /**
         * OBJECTIVE Returns the top
         */
        return top;
    }// top

    public int getSize() {

        // NOTE return the size of the stack
        return size;
    }

    public void print() {

        /**
         * OBJECTIVE Prints the elements of the stack
         */

        Node temp = top;
        while (temp != null) {
            System.out.println(" " + temp.getData() + " ");
            temp = temp.getNext();
        }
    }// print

}// Stack class
