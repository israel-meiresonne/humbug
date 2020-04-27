package g53298.humbug.model;

/**
 * Direction lists the directions in which the animals will move
 * @author israelmeiresonne
 */
public enum Direction {
    NORTH(-1, 0), SOUTH(1, 0), EAST(0, 1), WEST(0, -1);

    private final int deltaRow;
    private final int deltaColumn;

    /**
     * Constructor of a direction
     *
     * @param deltaRow the variation of the row of the direction
     * @param deltaColumn the variation of the column of the direction
     */
    private Direction(int deltaRow, int deltaColumn) {
        this.deltaRow = deltaRow;
        this.deltaColumn = deltaColumn;
    }

    /**
     * A getter of deltaRow
     *
     * @return the variation of the row of the direction
     */
    public int getDeltaRow() {
        return deltaRow;
    }

    /**
     * A getter of deltaColumn
     *
     * @return the variation of the column of the direction
     */
    public int getDeltaColumn() {
        return deltaColumn;
    }

}
