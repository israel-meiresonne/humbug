package g53298.humbug.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author israelmeiresonne
 */
public class LevelTest {

    /**
     * Test of readLevel method, of class Level.
     */
    @Test
    public void testReadLevel() {
        System.out.println("build a Level");
        int n = 1;
        Level level = Level.readLevel(n);
        assertTrue(level != null);
    }

}
