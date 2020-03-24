package g53298.humbug.model;

import static g53298.humbug.model.SquareType.*;

/**
 *
 * @author israelmeiresonne
 */
public class Spider extends Animal {

    /**
     * Constructor for a Snails
     *
     * @param positionOnBoard
     */
    public Spider(Position positionOnBoard) {
        super(positionOnBoard);
    }

    /**
     * Move the animal one the board in the direction given in param
     *
     * @param board the game board
     * @param direction the direction to move
     * @param animals animal to move
     * @return the new position if the animal moved, the initial position the
     * animal can't move in the direction given in param or null if the animal
     * is fallen of the board
     */
    @Override
    public Position move(Board board, Direction direction,
            Animal... animals) {
        Position currentPos = null;
        Position newPos = null;
        boolean isArrived = false;

        while (!isArrived) {
            currentPos = this.getPositionOnBoard();
            newPos = currentPos.next(direction);
            boolean isInside = board.isInside(newPos);
            boolean isFree = isFree(newPos, animals);
            
            if (isInside && isFree) {
                this.setPositionOnBoard(newPos);
                isArrived = !isFree(newPos.next(direction), animals);
                if (isArrived) {
                    board.switchToGrass(newPos);
                    this.setOnStar(isFree);
                }
            } else if (!isInside) {
                this.setPositionOnBoard(null);
                isArrived = true;
            } else {
                isArrived = true;
            }
        }
        return this.getPositionOnBoard();
    }

    /**
     * Check if the position given in param content any animal
     *
     * @param position the position to check if is free
     * @param animals all animal of the board
     * @return true if there is any animal on the positio else false
     */
    private boolean isFree(Position position, Animal... animals) {
        boolean isFree = true;
        for (Animal animal : animals) {
            isFree = isFree ? !position.equals(animal.getPositionOnBoard())
                    : isFree;
        }
        return isFree;
    }
}