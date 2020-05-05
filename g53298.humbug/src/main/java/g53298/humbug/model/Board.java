package g53298.humbug.model;

import static g53298.humbug.model.SquareType.GRASS;
import static g53298.humbug.model.SquareType.STAR;

/**
 * This class represents a game board in two dimensions, it content a Square
 * when there is a playable square else still null
 *
 * @author israelmeiresonne
 */
public class Board {

    private final Square[][] board;

    /**
     * Constructor of the Board
     *
     * @param board two-dimensional array composed of Square
     */
    Board(Square[][] board) {
        this.board = board;
    }

    /**
     * Initialise the board of the first level
     *
     * @return the board of the first level
     */
    public static Board getInitialBoard() {
        Square[][] board = new Square[][]{
            {new Square(GRASS), new Square(GRASS), null},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        };
        return new Board(board);
    }

    /**
     * Check if a position given in param is inside the board
     *
     * @param pos the position to check
     * @return true is the position given is inside the board else false
     * @throws IllegalArgumentException if the position given in param is null
     */
    public boolean isInside(Position pos) {
        if (pos == null) {
            throw new IllegalArgumentException("The position given in parameter "
                    + "can't be null");
        }
        return (pos.getRow() < getNbRow())
                && (pos.getColumn() < getNbColumn())
                && (pos.getRow() >= 0)
                && (pos.getColumn() >= 0)
                && board[pos.getRow()][pos.getColumn()] != null;
    }

    /**
     * Check the type of a square at the position given in param
     *
     * @param pos the position to check
     * @return the type of the square at the position given in param
     * @throws IllegalArgumentException if the position given in param is out of
     * the board
     */
    public SquareType getSquareType(Position pos) {
        if (!isInside(pos)) {
            throw new IllegalArgumentException("The position given in parameter"
                    + " is out of the board");
        }
        return board[pos.getRow()][pos.getColumn()].getType();
    }

    /**
     * Access to the board's number of row
     *
     * @return the board's number of row
     */
    public int getNbRow() {
        return board.length;
    }

    /**
     * Access to the board's number of column
     *
     * @return the board's number of column
     */
    public int getNbColumn() {
        return board[0].length;
    }

    /**
     * Switch the type of the square at the position given in param from STAR to
     * GRASS
     *
     * @param position the position of the quare to switch to GRASS
     */
    public void switchToGrass(Position position) {
        if ((isInside(position)) && (this.getSquareType(position) == STAR)) {
            board[position.getRow()][position.getColumn()].setType(GRASS);
        }
    }

    /**
     * Check in the board if there is a wall in the direction given in param
     *
     * @param pos of a Square in the board
     * @param direction where to check in the Square if there is a wall
     * @return true if there is a wall else false
     */
    public boolean hasWall(Position pos, Direction direction) {
        if (isInside(pos)) {
            boolean posHasWall
                    = board[pos.getRow()][pos.getColumn()].hasWall(direction);
            if (posHasWall) {
                return true;
            }

            Position newPos = pos.next(direction);
            if (isInside(newPos)) {
                boolean newPosHasWall
                        = board[newPos.getRow()][newPos.getColumn()]
                                .hasWall(direction.opposite());
                return newPosHasWall;
            }
        }
        return false;
    }
}
