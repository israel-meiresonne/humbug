/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g53298.humbug.model;

/**
 * This class represents a position to find your way on the board
 * @author israelmeiresonne
 */
public class Position {
    private final int row;
    private final int column;

    /**
     * Constructor of a position
     *
     * @param row is the row of the position
     * @param column is the column of the position
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * give the row of a Position
     *
     * @return the row of a Position
     */
    public int getRow() {
        return row;
    }

    /**
     * give the column of a Position
     *
     * @return the column of a Position
     */
    public int getColumn() {
        return column;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.row;
        hash = 83 * hash + this.column;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.column != other.column) {
            return false;
        }
        return true;
    }
    
    
}