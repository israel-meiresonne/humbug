package g53298.humbug.model;

import static g53298.humbug.model.LevelStatus.*;

/**
 * this class includes all the elements necessary to play a game
 *
 * @author israelmeiresonne
 */
public class Game implements Model {

    private Board board;
    private Animal[] animals;
    private int remainingMoves;
    private LevelStatus levelStatus;

    /**
     * Constructor for the Game class
     */
    public Game() {
        levelStatus = NOT_STARTED;
    }

    /**
     * Getter for the board
     *
     * @return original the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Getter for animals
     *
     * @return original animals
     */
    public Animal[] getAnimals() {
        return animals;
    }

    /**
     * Getter for the number of movements remaining
     *
     * @return the number of movements remaining
     */
    public int getRemainingMoves() {
        return remainingMoves;
    }

    /**
     * Getter for level's status
     *
     * @return level's status
     */
    public LevelStatus getLevelStatus() {
        return levelStatus;
    }

    /**
     * This method initialize the board with the level given in param
     *
     * @param nLevel the level to load
     */
    public void startLevel(int nLevel) {
        Level level = Level.readLevel(nLevel);
        levelStatus = IN_PROGRESS;
        remainingMoves = level.getnMoves();
        animals = level.getAnimals();
        board = level.getBoard();
    }

    /**
     * Move the animal in the position given in param to the direction also
     * given in param
     *
     * @param position the supposed position of a animal
     * @param direction the direction where to move the animal
     */
    public void move(Position position, Direction direction) {
        if (animals == null) {
            throw new IllegalStateException("There are no animals in this"
                    + " level!");
        }
        if (board == null) {
            throw new IllegalStateException("The board is empty!");
        }
        if (levelStatus != IN_PROGRESS) {
            throw new IllegalStateException("The game's status must be"
                    + " 'in progress'!");
        }
        boolean found = false;
        int nbAnim = animals.length;
        int i = 0;
        while ((i < nbAnim) && !found) {
            found = (animals[i].getPositionOnBoard().equals(position)
                    && (!animals[i].isOnStar()));
            if (found) {
                Position newPos = animals[i].move(board, direction, animals);
                if ((newPos != null) && (!newPos.equals(position))) {
                    remainingMoves--;
                }
            }
            i++;
        }
        updateStatus();
    }

    /**
     * Check if there is an animal on the position given in param
     *
     * @param position position to check
     * @return true if there is an animal on the position given in param else
     * false
     */
    public boolean isAnimalOn(Position position) {
        int nbAni = animals.length;
        int i = 0;
        boolean found = false;
        while ((!found) && (i < nbAni)) {
            Position aniPos = animals[i].getPositionOnBoard();
            found = position.equals(aniPos);
            i++;
        }
        return found;
    }

    /**
     * Update the status of the game
     */
    private void updateStatus() {
        if (areFallenAnimal()) {
            levelStatus = FAIL;
        } else if (allOnStar()) {
            levelStatus = WIN;
        } else if (!canMove()) {
            levelStatus = FAIL;
        }
    }

    /**
     * Check if there is fallen animal
     *
     * @return true if there is at less one fallen animal else false
     */
    private boolean areFallenAnimal() {
        int nbAni = animals.length;
        int i = 0;
        boolean found = false;
        while ((!found) && (i < nbAni)) {
            found = animals[i].getPositionOnBoard() == null;
            i++;
        }
        return found;
    }

    /**
     * Check all animals are on star
     *
     * @return true if all animals are on star else false
     */
    private boolean allOnStar() {
        int nbAni = animals.length;
        int i = 0;
        boolean found = true;
        while ((found) && (i < nbAni)) {
            found = animals[i].isOnStar();
            i++;
        }
        return found;
    }

    /**
     * Check if there are still movements to do
     *
     * @return true if there are still movements to do else false
     */
    private boolean canMove() {
        return (remainingMoves > 0);
    }
}
