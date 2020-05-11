package g53298.humbug.model;

import static g53298.humbug.view.text.TerminalColor.*;

/**
 * This class specifies the behavior of a Ladybird
 *
 * @author israelmeiresonne
 */
public class Ladybird extends Animal {

    private final int NB_MOVE = 2;

    /**
     * Constructor for a Ladybird
     */
    public Ladybird() {
        super();
    }

    /**
     * Constructor for a Ladybird
     *
     * @param positionOnBoard the animal's starting position
     */
    public Ladybird(Position positionOnBoard) {
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
        for (int i = 1; (!isArrived) && (i <= NB_MOVE); i++) {
            isArrived = moveOneCrawling(board, direction, animals);
            if (isArrived || (i == NB_MOVE)) {
                landing(board);
            }
        }
        return this.getPositionOnBoard();
    }

    @Override
    public String toString() {
        return RED + "Θ";
    }

}
