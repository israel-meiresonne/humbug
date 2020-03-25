package g53298.humbug.model;

/**
 *
 * @author israelmeiresonne
 */
public class Game {
    private Board board;
    private Animal[] animals;
    
    /**
     * Getter for the board
     * @return original the board
     */
    Board getBoard(){
        return board;
    }

    /**
     * Getter for animals
     * @return original animals
     */
    Animal[] getAnimals(){
        return animals;
    }

    /**
     * This method initialize the board with the first level
     * @param level 
     */
    void startLevel(int level){
        animals[0] = new Snail(new Position(0,0));
        board = Board.getInitialBoard();
    }

    /**
     * Check if the level is end by checking if all animal is on a star square
     * @return true if the level is end else false
     */
    boolean levelIsOver(){
        if(animals == null){
            throw new IllegalStateException("There are no animals in this"
                    + " level!");
        }
        boolean isOver = true;
        int nbAnimal = animals.length;
        int i = 0;
        while(isOver && (i < nbAnimal)){
            isOver = animals[i].isOnStar();
            i++;
        }
        return isOver;
    }


    /**
     * Move the animal in the position given in param to the direction 
     * also given in param
     * @param position the supposed position of a animal
     * @param direction the direction where to move the animal
     */
    void move(Position position, Direction direction){
        if(animals == null){
            throw new IllegalStateException("There are no animals in this"
                    + " level!");
        }
        if(board == null){
            throw new IllegalStateException("The board is empty!");
        }
        boolean found = false;
        int nbAnim = animals.length;
        int i = 0;
        while((i < nbAnim) && !found){
            found = animals[i].getPositionOnBoard().equals(position);
            if(found){
                animals[i].move(board, direction, animals);
            }
            i++;
        }
    }
}
