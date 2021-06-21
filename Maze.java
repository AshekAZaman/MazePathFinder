import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {

    private Position mazeArray[][];
    private int row, col;

    public Maze(String fileName) throws IOException {

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

    }// Maze constructor

}
// Maze class
