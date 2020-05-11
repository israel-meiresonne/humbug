package g53298.humbug.model;

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
     * @param nMoves number of move allowed
     * @param board the game board of the level
     * @param animals the animal available for the game
     */
    private Level(int nMoves, Board board, Animal[] animals){
        this.nMoves = nMoves;
        this.board = board;
        this.animals = animals;
    }
    
//    public static Level getLevel(int){
//        return level;
//    }
    
}
