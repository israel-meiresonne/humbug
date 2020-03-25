package g53298.humbug.model;

/**
 *
 * @author israelmeiresonne
 */
public class Game implements Model{
    private Board board;
    private Animal[] animals;
    
    /**
     * Getter for the board
     * @return original the board
     */
    public Board getBoard(){
        return board;
    }

    /**
     * Getter for animals
     * @return original animals
     */
    public Animal[] getAnimals(){
        return animals;
    }

    /**
     * This method initialize the board with the first level
     * @param level 
     */
    public void startLevel(int level){
        animals = new Animal[1];
        animals[0] = new Snail(new Position(0,0));
        board = Board.getInitialBoard();
    }

    /**
     * Check if the level is end by checking if all animal is on a star square
     * @return true if the level is end else false
     */
    public boolean levelIsOver(){
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
    public void move(Position position, Direction direction){
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
