package g53298.humbug.model;

/**
 * This class includes all the functions accessible in the Game class
 * @author israelmeiresonne
 */
public interface Model {

    /**
     * Getter for the board
     *
     * @return original the board
     */
    Board getBoard();

    /**
     * Getter for animals
     *
     * @return original animals
     */
    Animal[] getAnimals();

    /**
     * This method initialize the board with the level given in param
     * @param level the level to load
     */
    void startLevel(int level);

    /**
     * Check if the level is end by checking if all animal is on a star square
     *
     * @return true if the level is end else false
     */
    boolean levelIsOver();

    /**
     * Move the animal in the position given in param to the direction also
     * given in param
     *
     * @param position the supposed position of a animal
     * @param direction the direction where to move the animal
     */
    void move(Position position, Direction direction);
}
