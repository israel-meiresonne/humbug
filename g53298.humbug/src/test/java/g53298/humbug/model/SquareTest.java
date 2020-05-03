package g53298.humbug.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author israelmeiresonne
 */
public class SquareTest {

//    private Square instance;

    public SquareTest() {
    }

//    @BeforeEach
//    public void setUp() {
//    }

    /**
     * Test of getType method, of class Square.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        SquareType expResult = SquareType.GRASS;
        Square instance = new Square(SquareType.GRASS);
        SquareType result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of hasWall method, of class Square when there is a wall
     * @param direction direction to test
     */
    @ParameterizedTest
    @EnumSource(Direction.class)
    public void testHasWall_true(Direction direction) {
        System.out.println("Square has wall in direction: " + direction);
        Square instance = new Square(SquareType.GRASS);
        setWallInDir(true, direction, instance);
        assertTrue(instance.hasWall(direction));
    }

    /**
     * Test of hasWall method, of class Square when there is a wall
     * @param direction direction to test
     */
    @ParameterizedTest
    @EnumSource(Direction.class)
    public void testHasWall_false(Direction direction) {
        System.out.println("Square hasn't wall in direction: " + direction);
        Square instance = new Square(SquareType.GRASS);
        setWallInDir(false, direction, instance);
        assertFalse(instance.hasWall(direction));
    }

    /**
     * Test if the Square class's constructor initialize all its wall to false
     *
     * @param direction direction to test if wall is there
     */
    @ParameterizedTest
    @EnumSource(Direction.class)
    public void testSquare_initWallFalse(Direction direction) {
        System.out.println("Square set wall to false: " + direction);
        Square instance = new Square(SquareType.GRASS);
        assertFalse(instance.hasWall(direction));
    }
    

    /**
     * Set wall in the direction asked
     * @param hasWall value to set wall
     * @param direction the direction where is the wall to set
     * @param square the set square to set
     */
    private void setWallInDir(boolean hasWall, Direction direction, 
            Square square){
        switch (direction) {
            case NORTH:
                square.setNorthWall(hasWall);
                break;

            case SOUTH:
                square.setSouthWall(hasWall);
                break;

            case EAST:
                square.setEastWall(hasWall);
                break;

            case WEST:
                square.setWestWall(hasWall);
                break;
        }
    }

}
