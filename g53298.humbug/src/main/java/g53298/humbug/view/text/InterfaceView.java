package g53298.humbug.view.text;

import g53298.humbug.model.Animal;
import g53298.humbug.model.Board;
import g53298.humbug.model.Direction;
import g53298.humbug.model.Position;

/**
 * List all the functions available in the View class
 *
 * @author israelmeiresonne
 */
public interface InterfaceView {

    /**
     * Print the content of the board
     *
     * @param board the board to print
     * @param animals animal to display
     */
    void displayBoard(Board board, Animal... animals);

    /**
     * Ask a position to the user, create and return that position
     *
     * @return position entered by the user
     */
    Position askPosition();

    /**
     * Ask a Direction to the user, create and return that Direction
     *
     * @return Direction entered by the user
     */
    Direction askDirection();

    /**
     * displays error message passed in argument
     *
     * @param message a error message to display
     */
    void displayError(String message);

    /**
     * Display the number of moves stilling
     *
     * @param remainingMoves the number of movements remaining
     */
    public void displayRemainingMoves(int remainingMoves);

    /**
     * Ask a level to the user
     *
     * @param msg the request message
     * @param erMsg the error message displayed when the entered value is
     * incorrect
     * @return a number
     */
    public int askLevel(String msg, String erMsg);
}
