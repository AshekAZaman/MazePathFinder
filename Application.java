import java.util.Scanner;

public class Application {

    /**
     * ABOUT This class runs the main method where the maze is recorded from the
     * input file and solved using stack and queue.
     * 
     */
    public static void main(String[] args) {

        System.out.println("Please enter the input file name (.txt only):\n");

        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();

        System.out.println("\nProcessing " + userInput + "...\n");

        Maze inputMaze = new Maze(userInput);

        scanner.close();

        inputMaze.solveByStack();
        System.out.println("");
        inputMaze.solveByQueue();

        System.out.println("\nProcessing terminated normally.");

    }// main
}// Application class
