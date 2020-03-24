package g53298.humbug.view.text;

import g53298.humbug.model.Board;
import g53298.humbug.model.Direction;
import g53298.humbug.model.Position;
import static g53298.humbug.model.SquareType.GRASS;
import static g53298.humbug.model.SquareType.STAR;
import java.util.Scanner;

/**
 *
 * @author israelmeiresonne
 */
public class View implements InterfaceView {

    private Scanner in;
    private final String GRASS_SYMBOL;
    private final String STAR_SYMBOL;
    private final String NULL_SQUARE_SYMBOL;

    public View() {
        in = new Scanner(System.in);;
        GRASS_SYMBOL = "▩";
        STAR_SYMBOL = "☆";
        NULL_SQUARE_SYMBOL = " ";
    }

    /**
     * Print the content of the board
     *
     * @param board the board to print
     */
    public void displayBoard(Board board) {
        int nbRow = board.getNbRow();
        int nbCol = board.getNbColumn();
        String[][] boardStr = new String[nbRow][nbCol];

        for (int row = 0; row < nbRow; row++) {
            for (int col = 0; col < nbCol; col++) {
                Position pos = new Position(row, col);
                if (board.isInside(pos)) {
                    switch (board.getSquareType(pos)) {
                        case GRASS:
                            boardStr[row][col] = GRASS_SYMBOL;
                            break;
                        case STAR:
                            boardStr[row][col] = STAR_SYMBOL;
                            break;
                    }
                } else {
                    boardStr[row][col] = NULL_SQUARE_SYMBOL;
                }
            }
        }

        for (int row = 0; row < nbRow; row++) {
            for (int col = 0; col < nbCol; col++) {
                if (boardStr[row][col] != NULL_SQUARE_SYMBOL) {
                    System.out.print("|");
                    System.out.print(boardStr[row][col]);
                    System.out.print("|");
                } else {
                    System.out.print(NULL_SQUARE_SYMBOL);
                    System.out.print(NULL_SQUARE_SYMBOL);
                    System.out.print(NULL_SQUARE_SYMBOL);
                }
            }
            System.out.println();
        }

    }

    /**
     * displays error message passed in argument
     *
     * @param message a error message to display
     */
    public void displayError(String message) {
        System.out.println(message);
    }

    /**
     * Ask a position to the user, create and return that position
     *
     * @return position entered by the user
     */
    public Position askPosition() {
        boolean isCorrect = false;
        Position pos = null;
        while (!isCorrect) {
            System.out.println("Entrer une position: <ligne> <colonne");
            String cmd = in.nextLine();
            String[] cmdList = cmd.split(" ");
            if (cmdList.length == 2
                    && cmdList[0].matches("[0-9]+")
                    && cmdList[1].matches("[0-9]+")) {
                int row = Integer.parseInt(cmdList[0]);
                int column = Integer.parseInt(cmdList[1]);
                pos = new Position(row, column);
                isCorrect = true;
            } else {
                displayError("La position est incorrect!");
            }
        }
        return pos;
    }

    /**
     * Ask a Direction to the user, create and return that Direction
     *
     * @return Direction entered by the user
     */
    public Direction askDirection() {
        boolean isCorrect = false;
        Direction dir = null;
        while (!isCorrect) {
            System.out.println("Entrer une direction(n, s, e or o): "
                    + "<direction>");
            String cmd = in.nextLine().toLowerCase();
            switch (cmd) {
                case "n":
                    dir = Direction.NORTH;
                    isCorrect = true;
                    break;
                case "s":
                    dir = Direction.SOUTH;
                    isCorrect = true;
                    break;
                case "e":
                    dir = Direction.EAST;
                    isCorrect = true;
                    break;
                case "o":
                    dir = Direction.WEST;
                    isCorrect = true;
                    break;
                default:
                    displayError("La direction est incorrect!");
            }
        }
        return dir;
    }

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        Square[][] bd = new Square[][]{
//            {new Square(GRASS), new Square(GRASS), null},
//            {null, new Square(GRASS), new Square(GRASS)},
//            {null, null, new Square(STAR)}
//        };
//        Board board = new Board(bd);
//        View view = new View();
//        view.displayBoard(board);
//        view.askDirection();
//        view.askPosition();
//    }
}
