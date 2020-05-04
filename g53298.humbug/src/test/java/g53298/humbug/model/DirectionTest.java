/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g53298.humbug.model;

import static g53298.humbug.model.Direction.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author israelmeiresonne
 */
public class DirectionTest {

    /**
     * Test of getDeltaRow method, of class Direction.
     */
    @Test
    public void testGetDeltaRow() {
        System.out.println("getDeltaRow");
        Direction instance = NORTH;
        int expResult = -1;
        int result = instance.getDeltaRow();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeltaColumn method, of class Direction.
     */
    @Test
    public void testGetDeltaColumn() {
        System.out.println("getDeltaColumn");
        Direction instance = EAST;
        int expResult = 1;
        int result = instance.getDeltaColumn();
        assertEquals(expResult, result);
    }

    /**
     * Test of opposite method, of class Direction.
     */
    @Test
    public void testOpposite() {
        System.out.println("opposite");
        Direction instance = SOUTH;
        Direction expResult = NORTH;
        Direction result = instance.opposite();
        assertEquals(expResult, result);
    }
    
}
