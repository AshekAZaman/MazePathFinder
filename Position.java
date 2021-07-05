
public class Position {

    /**
     * ABOUT The Position class represents one location in the maze (i.e. a
     * particular row and column, where the leftmost top position in the maze is row
     * 0, column 0). It stores information about that position, including whether it
     * is a path or a wall, and whether it is the start/origin or
     * finish/destination.
     */

    private int row, col;
    private SquareType typeOfSquare;
    private boolean isVisted;
    private Position refPrevious;

    public Position(int row, int col, SquareType typeOfSquare) {
        this.row = row;
        this.col = col;
        this.typeOfSquare = typeOfSquare;
        this.isVisted = false;
        this.refPrevious = null;

    }// Position constructor

    public Position getPrevious() {

        // NOTE Getter for refPrevious

        return refPrevious;
    }

    public void setPrevious(Position refPrevious) {

        // NOTE Setter for refPrevious

        this.refPrevious = refPrevious;
    }

    public void setIsVisted(boolean isVisted) {

        // NOTE Setter for isVisted

        this.isVisted = isVisted;

    }

    public String coordinates() {

        /**
         * OBJECTIVE This method returns String representations of the position n, to
         * use when displaying the maze. It returns the coordinates.
         */

        return "(" + this.row + ", " + this.col + ")";
    }

    public String symbol() {

        /**
         * OBJECTIVE This method returns String representations of the position n, to
         * use when displaying the maze. It returns the appropriate symbol.
         */

        String symbol = new String();

        if (this.typeOfSquare == SquareType.PATH && this.isVisted == false) {
            symbol = ".";
        } else if (this.typeOfSquare == SquareType.WALL) {
            symbol = "#";
        } else if (this.typeOfSquare == SquareType.START) {
            symbol = "S";
        } else if (this.typeOfSquare == SquareType.FINISH) {
            symbol = "F";
        }

        if (this.isVisted == true && (this.typeOfSquare != SquareType.START) && (this.typeOfSquare != SquareType.FINISH)
                && (this.typeOfSquare != SquareType.WALL)) {
            symbol = "*";
        }

        return symbol;
    }

    public boolean isVisted() {
        return this.isVisted;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}// Position class