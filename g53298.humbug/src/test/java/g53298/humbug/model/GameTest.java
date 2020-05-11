package g53298.humbug.model;

import static g53298.humbug.model.LevelStatus.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author israelmeiresonne
 */
public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    /**
     * Test of startLevel method, of class Game.
     */
    @Test
    public void testGame() {
        System.out.println("constructor Game");
        Game instance = game;
        assertTrue(instance.getLevelStatus() == NOT_STARTED);
    }

    /**
     * Test of startLevel method, of class Game.
     */
    @Test
    public void testStartLevel() {
        System.out.println("start a level");
        int level = 1;
        Game instance = new Game();
        instance.startLevel(level);
        assertTrue(instance.getLevelStatus() == IN_PROGRESS);
    }
}
