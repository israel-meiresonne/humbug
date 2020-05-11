package g53298.humbug.model;

import static g53298.humbug.view.text.TerminalColor.*;

/**
 * This class specifies the behavior of a spider
 *
 * @author israelmeiresonne
 */
public class Spider extends Animal {

    /**
     * Constructor for a Spider
     */
    public Spider() {
        super();
    }

    /**
     * Constructor for a Spider
     *
     * @param positionOnBoard the animal's starting position
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
        boolean isArrived = false;
        while (!isArrived) {
            isArrived = moveOneCrawling(board, direction, animals);
            if (isArrived) {
                landing(board);
            }
        }
        return this.getPositionOnBoard();
    }

    @Override
    public String toString() {
        return BLACK + "*";
    }
}
