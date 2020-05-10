/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g53298.humbug.model;

import static g53298.humbug.model.SquareType.GRASS;
import static g53298.humbug.model.SquareType.STAR;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author israelmeiresonne
 */
public class BumbelbeeTest {

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
            new Bumbelbee(new Position(0, 0)),
            new Bumbelbee(new Position(1, 2))
        };
    }

    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove() {
        System.out.println("move_general");
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = new Position(0, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove_endline() {
        System.out.println("move end line and fall");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        });
        Bumbelbee instance = (Bumbelbee) animals[0];
        instance.setPositionOnBoard(new Position(0, 1));
        Position expResult = null;
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove_next_notfree() {
        System.out.println("move next case not free");
        Bumbelbee instance = (Bumbelbee) animals[0];
        animals[1].setPositionOnBoard(new Position(0, 1));
        Position expResult = new Position(0, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove_landingOnAnimal() {
        System.out.println("move and landing on other animal");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(GRASS), new Square(STAR)}
        });
        animals[1] = new Snail(new Position(0, 2));
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = new Position(0, 3);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove_flyUpOneEmptySquare() {
        System.out.println("move next case null by flying");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), null, new Square(GRASS), new Square(STAR)}
        });
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = new Position(2, 0); // landing
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove_flyUpTwoEmptySquare() {
        System.out.println("move next two null case by flying");
        board = new Board(new Square[][]{
            {null, new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), null, new Square(GRASS), new Square(STAR)}
        });
        Bumbelbee instance = (Bumbelbee) animals[0];
        instance.setPositionOnBoard(new Position(2, 0));
        Position expResult = null; // fall
        Position result = instance.move(board, Direction.NORTH, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove_passOnStar() {
        System.out.println("move and pass on star without win");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(STAR), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), null},
            {null, null, new Square(STAR), null}
        });
        animals = new Animal[]{
            new Bumbelbee(new Position(0, 0)),
            new Snail(new Position(1, 3))
        };
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = new Position(0, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
        assertFalse(animals[0].isOnStar());
        assertFalse(board.getSquareType(new Position(0, 1)) == GRASS);
    }

    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove_nextOnStar() {
        System.out.println("move, next on star and win");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(STAR), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), null},
            {null, null, new Square(STAR), null}
        });
        animals = new Animal[]{
            new Bumbelbee(new Position(0, 0)),
            new Snail(new Position(1, 3))
        };
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = new Position(0, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
        assertTrue(animals[0].isOnStar());
        assertEquals(GRASS, board.getSquareType(result));
    }

    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove_jumpUpWall() {
        System.out.println("fly up wall");
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
            new Bumbelbee(new Position(0, 0)),
            new Bumbelbee(new Position(2, 0))
        };
        Bumbelbee instance = (Bumbelbee) animals[1];
        Position expResult = new Position(2, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

}
