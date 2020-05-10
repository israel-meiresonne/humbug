package g53298.humbug.model;

import static g53298.humbug.model.SquareType.*;

/**
 * This class includes all the functions and attributes shared by all animals
 *
 * @author israelmeiresonne
 */
public abstract class Animal {

    private Position positionOnBoard;
    private boolean onStar;

    /**
     * Constructor of a animal
     *
     * @param positionOnBoard the animal's starting position
     */
    public Animal(Position positionOnBoard) {
        this.positionOnBoard = positionOnBoard;
        onStar = false;
    }

    /**
     * Setter to update the position of the animal
     *
     * @param positionOnBoard
     */
    public void setPositionOnBoard(Position positionOnBoard) {
        this.positionOnBoard = (positionOnBoard == null)
                ? null
                : new Position(positionOnBoard.getRow(),
                        positionOnBoard.getColumn());
    }

    /**
     * Setter to update if the animal is on a star square
     *
     * @param onStar set true if the animal is on a star else false
     */
    public void setOnStar(boolean onStar) {
        this.onStar = onStar;
    }

    /**
     * Getter for the position of the animal
     *
     * @return the position of the animal
     */
    public Position getPositionOnBoard() {
        return positionOnBoard != null
                ? new Position(positionOnBoard.getRow(),
                        positionOnBoard.getColumn())
                : null;
    }

    /**
     * Getter to get if the animal is on a star square
     *
     * @return true if the animal is on a star square else false
     */
    public boolean isOnStar() {
        return onStar;
    }

    /**
     * Move the animal one the board in the direction given in param
     *
     * @param board the game board
     * @param direction the direction to move
     * @param animals animal to move
     * @return the new position if the animal moved, the initial position the
     * animal can't move in the direction given in param or null if the animal
     * is fallen of the board
     */
    public abstract Position move(Board board, Direction direction,
            Animal... animals);

    /**
     * Make crawl the current animal into the direction passed in param
     *
     * @param board the game board
     * @param direction the direction to move
     * @param animals animal to move
     * @return true if the animal is arrived (can't move anymore) else false
     */
    protected boolean moveOneCrawling(Board board, Direction direction,
            Animal... animals) {
        Position currentPos = this.getPositionOnBoard();
        Position newPos = currentPos.next(direction);
        boolean isInside = board.isInside(newPos);
        boolean hasWall = board.hasWall(currentPos, direction);
        boolean isFree = isFree(newPos, animals);
        boolean isArrived = false;

        if (isInside && !hasWall && isFree) {
            this.setPositionOnBoard(newPos);
//            this.setOnStar(board.getSquareType(newPos) == STAR); // deplacer dans les animaux
            isArrived = !this.isFree(newPos.next(direction), animals);
            return isArrived;
        }
        if (hasWall) {
            isArrived = true;  // this line is right there for the understanding
            return isArrived;
        }
        if (!isInside) {
            this.setPositionOnBoard(null);
            isArrived = true;  // this line is right there for the understanding
            return isArrived;
        }
        isArrived = true;  // this line is right there for the understanding
        return isArrived; // default true cause next square isn't free
    }

    /**
     * Make jump the current animal into the direction passed in param
     *
     * @param board the game board
     * @param direction the direction to move
     * @param animals animal to move
     * @return true if the animal is arrived (can't move anymore) else false
     */
    protected boolean moveOneJumping(Board board, Direction direction,
            Animal... animals) {
        Position currentPos = this.getPositionOnBoard();
        Position newPos = currentPos.next(direction);
        boolean isInside = board.isInside(newPos);
        boolean isFree = isFree(newPos, animals);
        boolean isArrived = false;

        if (isInside && isFree) {
            this.setPositionOnBoard(newPos);
            return isArrived;
        }
        if (isInside && !isFree) {
            this.setPositionOnBoard(newPos);
            isArrived = this.moveOneJumping(board, direction, animals);
            return isArrived;
        }
        if (!isInside) {
            this.setPositionOnBoard(null);
            isArrived = true;
            return isArrived;
        }
        return isArrived; // return false cause a jumper is never arrived 
                          // till he touch an free square or fall
    }

    /**
     * Make fly the current animal into the direction passed in param
     *
     * @param board the game board
     * @param direction the direction to move
     * @param animals animal to move
     * @return true if the animal is arrived (can't move anymore) else false
     */
    protected boolean moveOneFlying(Board board, Direction direction,
            Animal... animals) {
        Position currentPos = this.getPositionOnBoard();
        Position newPos = currentPos.next(direction);
        boolean isInside = board.isInside(newPos);
        boolean isFree = isFree(newPos, animals);
        boolean isArrived = false;

        if (isInside && isFree) {
            this.setPositionOnBoard(newPos);
            return isArrived;
        }
        if (isInside && !isFree) {
            this.setPositionOnBoard(newPos);
            isArrived = this.moveOneFlying(board, direction, animals);
            return isArrived;
        }
        if (!isInside) {
            boolean inArray = (newPos.getRow() < board.getNbRow())
                    && (newPos.getColumn() < board.getNbColumn())
                    && (newPos.getRow() >= 0)
                    && (newPos.getColumn() >= 0);
            if (inArray) {
                isArrived = this.moveOneFlying(board, direction, animals);
                return isArrived;
            }
            this.setPositionOnBoard(null);
            isArrived = true;
            return isArrived;
        }
        return isArrived; // return false cause a flyer is never arrived 
                          // till he touch an free square or fall
    }

    /**
     * Update the animal and the board. set true animal's onStar attribut and 
     * switch the type of the square where he's to GRASS If the animal is on a 
     * start else set onStar to false
     * @param board the game board
     */
    protected void landing(Board board) {
        Position currentPos = this.getPositionOnBoard();
        if (currentPos != null) {
            this.setOnStar(board.getSquareType(currentPos) == STAR);
            if (this.isOnStar()) {
                board.switchToGrass(currentPos);
            }
        }
    }

    /**
     * Check if the position given in param content any animal
     *
     * @param position the position to check if is free
     * @param animals all animal of the board
     * @return true if there is any animal on the positio else false
     */
    private boolean isFree(Position position, Animal... animals) {
        boolean isFree = true;
        for (Animal animal : animals) {
            isFree = isFree ? !position.equals(animal.getPositionOnBoard())
                    : isFree;
        }
        return isFree;
    }
}
