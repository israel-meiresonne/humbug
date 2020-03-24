package g53298.humbug.view.text;

import g53298.humbug.model.Board;
import g53298.humbug.model.Direction;
import g53298.humbug.model.Position;

/**
 *
 * @author israelmeiresonne
 */
interface InterfaceView {

    /**
     * Print the content of the board
     *
     * @param board the board to print
     */
    public void displayBoard(Board board);

    /**
     * Ask a position to the user, create and return that position
     *
     * @return position entered by the user
     */
    public Position askPosition();

    /**
     * Ask a Direction to the user, create and return that Direction
     *
     * @return Direction entered by the user
     */
    public Direction askDirection();

    /**
     * displays error message passed in argument
     *
     * @param message a error message to display
     */
    public void displayError(String message);
}