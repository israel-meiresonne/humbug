/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g53298.humbug.model;

import static g53298.humbug.model.SquareType.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author israelmeiresonne
 */
public class GrasshopperTest {
    private Board board;
    private Animal[] animals;

    @BeforeEach
    public void setUp() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), null},
            {null, new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(GRASS), new Square(STAR)}
        });
        animals = new Animal[]{
            new Grasshopper(new Position(0, 0)),
            new Grasshopper(new Position(1, 2))
        };
    }

    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMove() {
        System.out.println("move_general");
        Grasshopper instance = (Grasshopper) animals[0];
        Position expResult = new Position(0, 1);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMove_next_notfree() {
        System.out.println("move next case not free");
        Grasshopper instance = (Grasshopper) animals[0];
        animals[1].setPositionOnBoard(new Position(0, 1));
        Position expResult = new Position(0, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMove_next_onstar() {
        System.out.println("move next on star");
        Grasshopper instance = (Grasshopper) animals[1];
        instance.setPositionOnBoard(new Position(1, 3));
        Position expResult = new Position(2, 3);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
        assertTrue(instance.isOnStar());
        assertEquals(GRASS, board.getSquareType(result));
    }

    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMove_next_notinside_2() {
        System.out.println("move next case null");
        Grasshopper instance = (Grasshopper) animals[0];
        instance.setPositionOnBoard(new Position(1, 1));
        Position expResult = null; // move and fall
        Position result = instance.move(board, Direction.WEST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Spider.
     */
    @Test
    public void testMove_jumpUpWall() {
        System.out.println("jump up wall");
        Square sqEastWall = new Square(GRASS);
        sqEastWall.setEastWall(true);
        Square sqWestWall = new Square(GRASS);
        sqWestWall.setWestWall(true);
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), sqEastWall, sqWestWall, new Square(GRASS)},
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), new Square(STAR)}
        });
        animals = new Animal[]{
            new Grasshopper(new Position(0, 0)),
            new Grasshopper(new Position(2, 1))
        };
        Grasshopper instance = (Grasshopper) animals[1];
        Position expResult = new Position(2, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

}
