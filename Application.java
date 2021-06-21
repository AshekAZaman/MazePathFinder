import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        System.out.println("Please enter the input file name (.txt only):\n");

        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();

        System.out.println("\nProcessing " + userInput + "...\n");

        Maze inputMaze = new Maze(userInput);

        scanner.close();

        System.out.println("\nProcessing terminated normally.");

    }// main
}// Application class
