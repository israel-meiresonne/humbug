package g53298.humbug.model;

/**
 * This class represents a position to find your way on the board
 *
 * @author israelmeiresonne
 */
public class Position {

    private final int row;
    private final int column;

    /**
     * Constructor of a position
     *
     * @param row is the row of the position
     * @param column is the column of the position
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * give the row of a Position
     *
     * @return the row of a Position
     */
    public int getRow() {
        return row;
    }

    /**
     * give the column of a Position
     *
     * @return the column of a Position
     */
    public int getColumn() {
        return column;
    }

    /**
     * Gives the position next to the current position in the direction
     * indicated by the value passed in parameter
     *
     * @param direction one of the four cardinal points (NORTH, SOUTH, EAST,
     * WEST)
     * @return the position in the indicated direction
     */
    public Position next(Direction direction) {
        int nextRow = this.row + direction.getDeltaRow();
        int nextColumn = this.column + direction.getDeltaColumn();

        return new Position(nextRow, nextColumn);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.row;
        hash = 83 * hash + this.column;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.column != other.column) {
            return false;
        }
        return true;
    }

}
