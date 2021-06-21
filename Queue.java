public class Queue {

    /**
     * ABOUT This is the queye class built using a array
     */

    private int front, back;
    private Position queue[];
    private int size;

    public Queue() {
        size = 100;
        front = back = 0;
        queue = new Position[size];
    }// Queue constructor

    public Queue(int size) {
        this.size = size;
        front = back = 0;
        queue = new Position[size];
    }// Queue constructor

    public void enqueue(Position data) {
        /**
         * OBJECTIVE Inserts a data at the very back of the queue
         */
        if (size != back) {
            queue[back] = data;
            back++;
        }
    }// enqueue

    public void dequeue() {

        /**
         * OBJECTIVE If queue is not empty shift all the elements from index 2 till back
         * to the right by one
         */
        if (front != back) {

            for (int i = 0; i < back - 1; i++) {
                queue[i] = queue[i + 1];
            }

            if (back < size)
                queue[back] = null;

            back--;
        }
    }// dequeue

    public boolean isEmpty() {
        /**
         * OBJECTIVE Returns true if queue is empty
         */
        return front == back;
    }

    public Position front() {
        /**
         * OBJECTIVE Returns the first element in the queue
         */
        return queue[front];
    }// front

    public void print() {

        /**
         * OBJECTIVE Prints all the elements in the queue
         */
        if (front != back) {
            int i;
            for (i = front; i < back; i++) {
                System.out.println(" " + queue[i] + " ");
            }
        }
    }// print

}// Queue class
