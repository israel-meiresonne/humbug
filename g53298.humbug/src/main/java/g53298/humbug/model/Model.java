package g53298.humbug.model;

/**
 * This class includes all the functions accessible in the Game class
 *
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
     * Getter for the number of movements remaining
     * @return the number of movements remaining
     */
    int getRemainingMoves();

    /**
     * This method initialize the board with the level given in param
     *
     * @param level the level to load
     */
    void startLevel(int level);
    
    /**
     * Getter for level's status
     * @return level's status
     */
    LevelStatus getLevelStatus();

    /**
     * Move the animal in the position given in param to the direction also
     * given in param
     *
     * @param position the supposed position of a animal
     * @param direction the direction where to move the animal
     */
    void move(Position position, Direction direction);

    /**
     * Check if there is an animal on the position given in param
     *
     * @return true if there is an animal on the position given in param else
     * false
     */
    public boolean isAnimalOn(Position position);
}
