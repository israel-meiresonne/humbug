package g53298.humbug.model;

import static g53298.humbug.model.Direction.*;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static g53298.humbug.model.SquareType.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Pierre Bettens (pbt) <pbettens@he2b.be>
 */
public class BoardTest {

    private Board board;

    public BoardTest() {

    }

    @BeforeEach
    public void setUp() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), null},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        });
    }

    /**
     * Test of isInside method, of class Board.
     */
    @Test
    public void testIsInside_general_true() {
        System.out.println("isInside general");
        Position position = new Position(0, 0);
        boolean expResult = true;
        boolean result = board.isInside(position);
        assertEquals(expResult, result);
    }

    /**
     * Test of isInside method, of class Board.
     */
    @Test
    public void testIsInside_false_null() {
        System.out.println("isInside false null");
        Position position = new Position(1, 0);
        boolean expResult = false;
        boolean result = board.isInside(position);
        assertEquals(expResult, result);
    }

    /**
     * Test of isInside method, of class Board.
     */
    @Test
    public void testIsInside_false_outbound_negative() {
        System.out.println("isInside false out of bound");
        Position position = new Position(-1, 0);
        boolean expResult = false;
        boolean result = board.isInside(position);
        assertEquals(expResult, result);
    }

    /**
     * Test of isInside method, of class Board.
     */
    @Test
    public void testIsInside_false_outbound_positive_row() {
        System.out.println("isInside false out of bound");
        Position position = new Position(10, 1);
        boolean expResult = false;
        boolean result = board.isInside(position);
        assertEquals(expResult, result);
    }

    /**
     * Test of isInside method, of class Board.
     */
    @Test
    public void testIsInside_false_outbound_positive_column() {
        System.out.println("isInside false out of bound");
        Position position = new Position(2, 23);
        boolean expResult = false;
        boolean result = board.isInside(position);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetSquareType_exist() {
        System.out.println("get square type exist");
        SquareType expResult = SquareType.GRASS;
        SquareType result = board.getSquareType(new Position(0, 0));
        assertEquals(expResult, result);
    }

    @Test
    public void testGetSquareType_exist_star() {
        System.out.println("get square type exist");
        SquareType expResult = SquareType.STAR;
        SquareType result = board.getSquareType(new Position(2, 2));
        assertEquals(expResult, result);
    }

    @Test
    public void testGetSquareType_null() {
        System.out.println("get case type illegal argument");
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    board.getSquareType(new Position(1, 0));
                });

    }
    
    
    /**
     * Test of hasWall method, of class Board.
     */
    @Test
    public void testHasWall_position_outside_false() {
        System.out.println("square is outside");
        Square sqEastWall = new Square(GRASS);
        sqEastWall.setEastWall(true);

        Square[][] sqs = new Square[][]{
            {null, new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), sqEastWall, new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), new Square(STAR)}
        };
        Board myBoard = new Board(sqs);
        Position pos = new Position(0, 0);
        assertFalse(myBoard.hasWall(pos, EAST));
    }

    /**
     * Test of hasWall method, of class Board.
     */
    @Test
    public void testHasWall_position_inside_true() {
        System.out.println("square is inside and has wall");
        Square sqEastWall = new Square(GRASS);
        sqEastWall.setEastWall(true);

        Square[][] sqs = new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), sqEastWall, new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), new Square(STAR)}
        };
        Board myBoard = new Board(sqs);
        Position pos = new Position(1, 1);
        assertTrue(myBoard.hasWall(pos, EAST));
    }

    /**
     * Test of hasWall method, of class Board.
     */
    @Test
    public void testHasWall_position_inside_false() {
        System.out.println("square is inside and hasn't wall");
        Square sqEastWall = new Square(GRASS);
        Square[][] sqs = new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), sqEastWall, new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), new Square(STAR)}
        };
        Board myBoard = new Board(sqs);
        Position pos = new Position(1, 1);
        assertFalse(myBoard.hasWall(pos, EAST));
    }
 
    /**
     * Test of hasWall method, of class Board.
     */
    @Test
    public void testHasWall_position_inside_false_newPos_inside_true() {
        System.out.println("square is inside and hasn't wall, "
                + "next square inside has wall");
        Square sqEastWall = new Square(GRASS);
        Square nextSqEastWall = new Square(GRASS);
        nextSqEastWall.setWestWall(true);
        Square[][] sqs = new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), sqEastWall, nextSqEastWall, new Square(GRASS)},
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), new Square(STAR)}
        };
        Board myBoard = new Board(sqs);
        Position pos = new Position(1, 1);
        assertTrue(myBoard.hasWall(pos, EAST));
    }
    
    /**
     * Test of hasWall method, of class Board.
     */
    @Test
    public void testHasWall_position_inside_false_newPos_outside_false() {
        System.out.println("square is inside and hasn't wall, "
                + "next square outside");
        Square sqEastWall = new Square(GRASS);
        Square nextSqEastWall = new Square(GRASS);
        nextSqEastWall.setWestWall(true);
        Square[][] sqs = new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), new Square(STAR)}
        };
        Board myBoard = new Board(sqs);
        Position pos = new Position(0, 3);
        assertFalse(myBoard.hasWall(pos, EAST));
    }

}
