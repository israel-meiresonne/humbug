package g53298.humbug.model;

/**
 *
 * @author israelmeiresonne
 */
public abstract class Animal {
    private Position positionOnBoard;
    private boolean onStar;
    
    /**
     * Constructor of a animal
     * @param positionOnBoard the animal's starting position
     */
    public Animal(Position positionOnBoard){
        this.positionOnBoard = positionOnBoard;
        onStar = false;
    }

    /**
     * Setter to update the position of the animal
     * @param positionOnBoard 
     */
    public void setPositionOnBoard(Position positionOnBoard) {
        this.positionOnBoard = new Position(positionOnBoard.getRow(), 
                positionOnBoard.getColumn());
    }
    
    /**
     * Setter to update if the animal is on a star square
     * @param onStar set true if the animal is on a star else false 
     */
    public void setOnStar(boolean onStar) {
        this.onStar = onStar;
    }
    
    /**
     * Getter for the position of the animal
     * @return the position of the animal
     */
    public Position getPositionOnBoard(){
        return new Position(positionOnBoard.getRow(), 
                positionOnBoard.getColumn());
    }

    /**
     * Getter to get if the animal is on a star square
     * @return true if the animal is on a star square else false
     */
    public boolean isOnStar() {
        return onStar;
    }
    
    /**
     * Move the animal one the board in the direction given in param
     * @param board the game board
     * @param direction the direction to move
     * @param animals animal to move
     * @return -the new position if the animal moved, the initial position 
     * the animal can't move in the direction given in param or null if the 
     * animal is fallen of the board
     */
    public abstract Position move(Board board, Direction direction, 
            Animal... animals);
}
