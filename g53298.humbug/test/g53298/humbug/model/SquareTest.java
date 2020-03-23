package g53298.humbug.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author israelmeiresonne
 */
public class SquareTest {
    
    public SquareTest() {
    }

    /**
     * Test of getType method, of class Square.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Square instance = new Square(SquareType.GRASS);
        SquareType expResult = SquareType.GRASS;
        SquareType result = instance.getType();
        assertEquals(expResult, result);
    }
    
}
