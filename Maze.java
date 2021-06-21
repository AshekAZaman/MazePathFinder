import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {

    private Position mazeArray[][];
    private int row, col;

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

        System.out.println("The initial maze is:");
        printMaze();

    }// Maze constructor

    private void printMaze() {
        for (int i = 0; i < mazeArray.length; i++) {
            System.out.println();
            for (int j = 0; j < mazeArray[i].length; j++) {
                System.out.print(mazeArray[i][j].symbol());
            }
            System.out.println();
        }
    }

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

}
// Maze class
