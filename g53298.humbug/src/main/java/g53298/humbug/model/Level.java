package g53298.humbug.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

/**
 * Provides a level for the game
 *
 * @author israelmeiresonne
 */
public class Level {

    private Board board;
    private Animal[] animals;
    private int nMoves;

    /**
     * Constructor for Level
     */
    private Level() {
    }

    /**
     * Constructor for Level
     *
     * @param nMoves number of move allowed
     * @param board the game board of the level
     * @param animals the animal available for the game
     */
    private Level(int nMoves, Board board, Animal[] animals) {
        this();
        this.nMoves = nMoves;
        this.board = board;
        this.animals = animals;
    }

    /**
     * Getter for the board
     *
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Getter for animals
     *
     * @return animals
     */
    public Animal[] getAnimals() {
        return animals;
    }

    /**
     * Getter for the number of move allowed
     *
     * @return the number of move allowed
     */
    public int getnMoves() {
        return nMoves;
    }

    /**
     * Build a Level with a json file
     *
     * @param n the number of the level
     * @return a Level
     */
    static Level readLevel(int n) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(System.getProperty("user.dir") + getLevelDir(n));

            Level level = mapper.readValue(file, Level.class);
            return level;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    /**
     * Check if the file for the level given in param exist
     * @param n the number of the level
     * @return true if file for the level given in param exist else false
     */
    public static boolean levelExist(int n) {
        File file = new File(System.getProperty("user.dir") + getLevelDir(n));
        return file.exists();
    }

    /**
     * To get the directory where level are stored
     *
     * @param n the number of the level
     * @return the directory where level are stored
     */
    private static String getLevelDir(int n) {
        return ("/src/main/resources/level/level-" + n + ".json");
    }

}
