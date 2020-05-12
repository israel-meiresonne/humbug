package g53298.humbug.model;

/**
 * Square on the board. A square has a type grass or star and it’s all. A square
 * doesn’t know where it is on the board.
 *
 * @author israelmeiresonne
 */
public class Square {

    private SquareType type;
    private boolean northWall;
    private boolean southWall;
    private boolean westWall;
    private boolean eastWall;

    /**
     * Constructor of Square on board.
     */
    public Square() {
        this.northWall = false;
        this.southWall = false;
        this.westWall = false;
        this.eastWall = false;
    }

    /**
     * Constructor of Square on board.
     *
     * @param type Square is grass or star
     */
    public Square(SquareType type) {
        this();
        this.type = type;
    }

    /**
     * Simple getter of type.
     *
     * @return type of Square
     */
    public SquareType getType() {
        return type;
    }

    /**
     * Getter for the North Wall
     */
    public boolean getNorthWall() {
        return northWall;
    }

    /**
     * Getter for the South Wall
     */
    public boolean getSouthWall() {
        return southWall;
    }

    /**
     * Getter for the West Wall
     */
    public boolean getWestWall() {
        return westWall;
    }

    /**
     * Getter for the East Wall
     */
    public boolean getEastWall() {
        return eastWall;
    }

    /**
     * Setter of the type
     *
     * @param type a SquareType
     */
    void setType(SquareType type) {
        this.type = type;
    }

    /**
     * To set the north wall
     *
     * @param hasWall set true to put a wall else false
     */
    void setNorthWall(boolean hasWall) {
        this.northWall = hasWall;
    }

    /**
     * To set the south wall
     *
     * @param hasWall set true to put a wall else false
     */
    void setSouthWall(boolean hasWall) {
        this.southWall = hasWall;
    }

    /**
     * To set the west wall
     *
     * @param hasWall set true to put a wall else false
     */
    void setWestWall(boolean hasWall) {
        this.westWall = hasWall;
    }

    /**
     * To set the east wall
     *
     * @param hasWall set true to put a wall else false
     */
    void setEastWall(boolean hasWall) {
        this.eastWall = hasWall;
    }

    /**
     * Check if there is a wall on the direction given in param
     *
     * @param direction where to check if there is a wall
     * @return true if there is a wall else false
     */
    public boolean hasWall(Direction direction) {
        switch (direction) {
            case NORTH:
                return this.northWall;

            case SOUTH:
                return this.southWall;

            case EAST:
                return this.eastWall;

            case WEST:
                return this.westWall;

            default:
                throw new IllegalArgumentException("The direction given is not "
                        + "supported");
        }
    }
}
