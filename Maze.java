import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {

    private Position mazeArray[][];
    private int row, col;
    private Stack mazeStack;
    private Queue mazeQueue;

    // row and column number which represents the neighbours
    private int rowNum[] = { -1, 0, 0, 1 };
    private int colNum[] = { 0, -1, 1, 0 };

    public Maze(String fileName) {

        /**
         * OBJECTIVE The Maze constructor reads the input file, create a 2D array of the
         * appropriate size, and then fill the array using the setMaze method
         */
        try {
            setMaze(fileName);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mazeStack = new Stack();
        mazeQueue = new Queue();

        System.out.println("The initial maze is:");
        printMaze();

    }// Maze constructor

    private void printMaze() {

        /**
         * OBJECTIVE Prints each of the the elements in the mazeArray
         * 
         */

        for (int i = 0; i < mazeArray.length; i++) {
            System.out.println();
            for (int j = 0; j < mazeArray[i].length; j++) {
                System.out.print(mazeArray[i][j].symbol());
            }
            System.out.println();
        }
    }// printMaze

    private void setMaze(String fileName) throws IOException {

        /**
         * OBJECTIVE This method reads the file name using the parameter. It then
         * extracts the information for the row and column number and puts it into the
         * mazeArray to make the maze.
         * 
         */

        FileReader fileRdr;
        fileRdr = new FileReader(fileName);
        BufferedReader inFile = new BufferedReader(fileRdr);

        String line;

        line = inFile.readLine();
        String[] splited = line.split("\\s+");
        this.row = Integer.parseInt(splited[0]);
        this.col = Integer.parseInt(splited[1]);

        mazeArray = new Position[row][col];

        int counterRow = 0;
        int counterCol = 0;

        do {
            line = inFile.readLine();
            if (line != null) {

                String dataString = line;
                String[] symbols = new String[dataString.length()];

                for (int i = 0; i < dataString.length(); i++) {
                    symbols[i] = "" + dataString.charAt(i);
                } // for

                int symbolCounter = 0;
                while (symbolCounter < symbols.length) {

                    SquareType tempSquare = null;

                    if (symbols[symbolCounter].equals(".")) {
                        tempSquare = SquareType.PATH;
                    } else if (symbols[symbolCounter].equals("#")) {
                        tempSquare = SquareType.WALL;
                    } else if (symbols[symbolCounter].equals("S")) {
                        tempSquare = SquareType.START;
                    } else if (symbols[symbolCounter].equals("F")) {
                        tempSquare = SquareType.FINISH;
                    }

                    Position data = new Position(counterRow, counterCol, tempSquare);
                    mazeArray[counterRow][counterCol] = data;

                    counterCol++;
                    if (counterCol == mazeArray[0].length) {
                        counterRow++;
                        counterCol = 0;

                    }
                    symbolCounter++;
                } // while
            } // if
        } while (line != null);

        inFile.close();

    }// setMaze

    public void resetMaze() {

        /**
         * OBJECTIVE This method resets all the positions in the maze to unvisited for
         * it to be used again to try different search
         */

        for (int i = 0; i < mazeArray.length; i++) {
            for (int j = 0; j < mazeArray[i].length; j++) {
                mazeArray[i][j].setIsVisted(false);
            }
        }

    }// resetMaze

    public void resetPreviousMaze() {

        /**
         * OBJECTIVE This method resets all the position's refPrevious in the maze to
         * false for it to be used again to try different search
         */

        for (int i = 0; i < mazeArray.length; i++) {
            for (int j = 0; j < mazeArray[i].length; j++) {
                mazeArray[i][j].setPrevious(null);
            }
        }

    }// resetMaze

    private int[] findStartPosition() {

        /**
         * OBJECTIVE This method find the start position in the array for further
         * process and return the coordinates.
         * 
         */

        int[] startPositon = new int[2];

        for (int i = 0; i < mazeArray.length; i++) {
            for (int j = 0; j < mazeArray[i].length; j++) {
                if (mazeArray[i][j].symbol().equals("S")) {
                    startPositon[0] = i;
                    startPositon[1] = j;
                    break;
                }
            }
        }

        return startPositon;

    }// findStartPosition

    private int[] findFinishPosition() {

        /**
         * OBJECTIVE This method finds the finish position in the array for further
         * process and return the coordinates.
         * 
         */

        int[] finishPosition = new int[2];

        for (int i = 0; i < mazeArray.length; i++) {
            for (int j = 0; j < mazeArray[i].length; j++) {
                if (mazeArray[i][j].symbol().equals("F")) {
                    finishPosition[0] = i;
                    finishPosition[1] = j;
                    break;
                }
            }
        }

        return finishPosition;

    }// findFinishPosition

    public void solveByQueue() {

        /**
         * OBJECTIVE This method uses the data structure queue inorder to find the path
         * from start to finish.
         */

        resetMaze();
        resetPreviousMaze();

        System.out.println("The path found using a queue is:");

        String pathMaze[] = new String[10];

        int startRow = findStartPosition()[0];
        int startCol = findStartPosition()[1];

        int finishRow = findFinishPosition()[0];
        int finishCol = findFinishPosition()[1];

        int currentRow = startRow;
        int currentCol = startCol;

        mazeQueue.enqueue(mazeArray[currentRow][currentCol]);
        mazeArray[currentRow][currentCol].setIsVisted(true);

        boolean finishFound = false;

        while (mazeQueue.isEmpty() == false && finishFound == false) {

            Position currentPosition = mazeQueue.front();
            currentRow = currentPosition.getRow();
            currentCol = currentPosition.getCol();

            if (currentRow == finishRow && currentCol == finishCol) {

                finishFound = true;
            }
            mazeQueue.dequeue();
            for (int i = 0; i < 4; i++) {

                if (isValid(mazeArray, currentRow + rowNum[i], currentCol + colNum[i])
                        && !mazeArray[currentRow + rowNum[i]][currentCol + colNum[i]].isVisted()
                        && !mazeArray[currentRow + rowNum[i]][currentCol + colNum[i]].symbol().equals("#")) {

                    mazeArray[currentRow + rowNum[i]][currentCol + colNum[i]].setIsVisted(true);
                    mazeArray[currentRow + rowNum[i]][currentCol + colNum[i]]
                            .setPrevious(mazeArray[currentRow][currentCol]);
                    mazeQueue.enqueue(mazeArray[currentRow + rowNum[i]][currentCol + colNum[i]]);

                }

            }

        }

        resetMaze();

        Position temp = mazeArray[finishRow][finishCol];
        int counter = 0;
        while (temp != mazeArray[startRow][startCol]) {
            pathMaze[counter] = temp.coordinates();
            temp.setIsVisted(true);
            temp = temp.getPrevious();
            counter++;
        }

        pathMaze[counter++] = mazeArray[startRow][startCol].coordinates();

        printMaze();

        System.out.print("Path from start to finish is: ");
        for (int i = pathMaze.length - 1; i >= 0; i--) {
            if (pathMaze[i] != null) {
                System.out.print(pathMaze[i] + " ");
            }
        }

    }// solveByQueue

    public void solveByStack() {

        /**
         * OBJECTIVE This method uses the data structure stack inorder to find the path
         * from start to finish.
         */

        resetMaze();
        resetPreviousMaze();

        System.out.println("The path found using a stack is:");

        String pathMaze[] = new String[10];

        int startRow = findStartPosition()[0];
        int startCol = findStartPosition()[1];

        int finishRow = findFinishPosition()[0];
        int finishCol = findFinishPosition()[1];

        int currentRow = startRow;
        int currentCol = startCol;

        mazeStack.push(mazeArray[currentRow][currentCol], currentRow, currentCol);
        mazeArray[currentRow][currentCol].setIsVisted(true);

        boolean finishFound = false;

        while (mazeStack.isEmpty() == false && finishFound == false) {

            Node currentPosition = mazeStack.top();
            currentRow = currentPosition.getRow();
            currentCol = currentPosition.getCol();

            if (currentRow == finishRow && currentCol == finishCol) {

                finishFound = true;
            }
            mazeStack.pop();
            for (int i = 0; i < 4; i++) {

                if (isValid(mazeArray, currentRow + rowNum[i], currentCol + colNum[i])
                        && !mazeArray[currentRow + rowNum[i]][currentCol + colNum[i]].isVisted()
                        && !mazeArray[currentRow + rowNum[i]][currentCol + colNum[i]].symbol().equals("#")) {

                    mazeArray[currentRow + rowNum[i]][currentCol + colNum[i]].setIsVisted(true);
                    mazeArray[currentRow + rowNum[i]][currentCol + colNum[i]]
                            .setPrevious(mazeArray[currentRow][currentCol]);
                    mazeStack.push(mazeArray[currentRow + rowNum[i]][currentCol + colNum[i]], currentRow + rowNum[i],
                            currentCol + colNum[i]);

                }

            }

        }

        resetMaze();

        Position temp = mazeArray[finishRow][finishCol];
        int counter = 0;
        while (temp != mazeArray[startRow][startCol]) {
            pathMaze[counter] = temp.coordinates();
            temp.setIsVisted(true);
            temp = temp.getPrevious();
            counter++;
        }

        pathMaze[counter++] = mazeArray[startRow][startCol].coordinates();

        printMaze();

        System.out.print("Path from start to finish is: ");
        for (int i = pathMaze.length - 1; i >= 0; i--) {
            if (pathMaze[i] != null) {
                System.out.print(pathMaze[i] + " ");
            }
        }

    }// solveByStack

    private boolean isValid(Position arrayMaze[][], int row, int col) {

        /**
         * OBJECTIVE This method checks if the positions are valid for checking the
         * path.
         * 
         */

        return (row >= 0) && (row < arrayMaze.length) && (col >= 0) && (col < arrayMaze[row].length);
    }
}
// Maze class
