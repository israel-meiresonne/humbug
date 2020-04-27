package g53298.humbug.view.text;

import g53298.humbug.model.Animal;
import g53298.humbug.model.Board;
import g53298.humbug.model.Direction;
import g53298.humbug.model.Position;
import static g53298.humbug.model.SquareType.GRASS;
import static g53298.humbug.model.SquareType.STAR;
import static g53298.humbug.view.text.TerminalColor.*;
import java.util.Scanner;

/**
 * Displays the game board and its useful information for interacting with
 *
 * @author israelmeiresonne
 */
public class View implements InterfaceView {

    private final Scanner in;
    private final String GRASS_SYMBOL;
    private final String STAR_SYMBOL;
    private final String NULL_SQUARE_SYMBOL;
    private final String SEPARATOR;

    public View() {
        in = new Scanner(System.in);
        GRASS_SYMBOL = GREEN + "▩";
        STAR_SYMBOL = YELLOW + "☆";
        NULL_SQUARE_SYMBOL = " ";
        SEPARATOR = BLACK + "|";
    }

    /**
     * Print the content of the board
     *
     * @param board the board to print
     * @param animals animal to display
     */
    public void displayBoard(Board board, Animal... animals) {
        String[][] boardStr = placeSquareSymbols(board);
        boardStr = placeAnimals(boardStr, animals);
        printBoardStr(boardStr);
    }

    /**
     * Create and fill a two-dim array with the symbol corresponding to the
     * Square contained in the same position of the Board passed in param
     *
     * @param board the board containing the Square to get symbol
     * @return a two-dim array filed of symbol corresponding to a SquareType
     */
    private String[][] placeSquareSymbols(Board board) {
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
        return boardStr;
    }

    /**
     * Place symbol of each animal in a two-dim array on the corresponding
     * position
     *
     * @param boardStr a two-dim array
     * @param animals animal to get symbol and place on the two-dim array
     * @return a two-dim array that contain animal symbol on the position
     * indicated by animals
     */
    private String[][] placeAnimals(String[][] boardStr, Animal... animals) {
        for (Animal animal : animals) {
            Position pos = animal.getPositionOnBoard();
            if ((pos != null) && (!animal.isOnStar())) {
                boardStr[pos.getRow()][pos.getColumn()] = animal.toString();
            }
        }
        return boardStr;
    }

    /**
     * Print two-dim array filled of symbol corresponding to Animal symbol or 
     * Square symbol
     * @param boardStr array filled of symbol corresponding to Animal symbol or 
     * Square symbol
     */
    private void printBoardStr(String[][] boardStr) {
        int nbRow = boardStr.length;
        int nbCol = boardStr[0].length;
        for (int row = 0; row < nbRow; row++) {
            if (row == 0) {
                System.out.print(NULL_SQUARE_SYMBOL);
                for (int colHead = 0; colHead < nbCol; colHead++) {
                    System.out.print(NULL_SQUARE_SYMBOL);
                    System.out.print(colHead);
                    System.out.print(NULL_SQUARE_SYMBOL);
                }
                System.out.println();
            }
            System.out.print(row);
            for (int col = 0; col < nbCol; col++) {

                if (boardStr[row][col] != NULL_SQUARE_SYMBOL) {
                    System.out.print(SEPARATOR);
                    System.out.print(boardStr[row][col]);
                    System.out.print(SEPARATOR);
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
            System.out.println("Entrer une position: <ligne> <colonne>");
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
//        Spider sp = new Spider(new Position(0,1));
//        Snail sn = new Snail(new Position(1,1));
//        View view = new View();
//        view.displayBoard(board, sp, sn);
//        view.askDirection();
//        view.askPosition();
//    }
}
