package g53298.humbug.model;

import static g53298.humbug.model.SquareType.*;

/**
 * This class represents a game squares in two dimensions, it content a Square
 * when there is a playable square else still null
 *
 * @author israelmeiresonne
 */
public class Board {

    private Square[][] squares;

    /**
     * Constructor of the Board
     */
    Board() {
    }

    /**
     * Constructor of the Board
     *
     * @param squares two-dimensional array composed of Square
     */
    Board(Square[][] squares) {
        this();
        this.squares = squares;
    }

    /**
     * Getter for the squares
     *
     * @return the squares
     */
    public Square[][] getSquares() {
        return squares;
    }

    /**
     * Initialise the squares of the first level
     *
     * @return the squares of the first level
     */
    public static Board getInitialBoard() {
        Square[][] squares = new Square[][]{
            {new Square(GRASS), new Square(GRASS), null},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        };
        return new Board(squares);
    }

    /**
     * Check if a position given in param is inside the squares
     *
     * @param pos the position to check
     * @return true is the position given is inside the squares else false
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
                && squares[pos.getRow()][pos.getColumn()] != null;
    }

    /**
     * Check the type of a square at the position given in param
     *
     * @param pos the position to check
     * @return the type of the square at the position given in param
     * @throws IllegalArgumentException if the position given in param is out of
     * the squares
     */
    public SquareType getSquareType(Position pos) {
        if (!isInside(pos)) {
            throw new IllegalArgumentException("The position given in parameter"
                    + " is out of the squares");
        }
        return squares[pos.getRow()][pos.getColumn()].getType();
    }

    /**
     * Access to the squares's number of row
     *
     * @return the squares's number of row
     */
    public int getNbRow() {
        return squares.length;
    }

    /**
     * Access to the squares's number of column
     *
     * @return the squares's number of column
     */
    public int getNbColumn() {
        return squares[0].length;
    }

    /**
     * Switch the type of the square at the position given in param from STAR to
     * GRASS
     *
     * @param position the position of the quare to switch to GRASS
     */
    public void switchToGrass(Position position) {
        if ((isInside(position)) && (this.getSquareType(position) == STAR)) {
            squares[position.getRow()][position.getColumn()].setType(GRASS);
        }
    }

    /**
     * Check in the squares if there is a wall in the direction given in param
     *
     * @param pos of a Square in the squares
     * @param direction where to check in the Square if there is a wall
     * @return true if there is a wall else false
     */
    public boolean hasWall(Position pos, Direction direction) {
        if (isInside(pos)) {
            boolean posHasWall
                    = squares[pos.getRow()][pos.getColumn()].hasWall(direction);
            if (posHasWall) {
                return true;
            }

            Position newPos = pos.next(direction);
            if (isInside(newPos)) {
                boolean newPosHasWall
                        = squares[newPos.getRow()][newPos.getColumn()]
                                .hasWall(direction.opposite());
                return newPosHasWall;
            }
        }
        return false;
    }
}
