/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operations;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class StateTest {
    
    public StateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getStateName method, of class State.
     */
    @Test
    public void testGetStateName() {
        System.out.println("getStateName");
        State instance = new State("MN", 7.2);
        
        String expResult = "MN";
        String result = instance.getStateName();
        
        assertEquals(expResult, result);
                
    }

    /**
     * Test of getTaxRate method, of class State.
     */
    @Test
    public void testGetTaxRate() {
        System.out.println("getTaxRate");
        State instance = new State("MN", 7.2);
        
        double expResult = 7.2;
        double result = instance.getTaxRate();
        
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of toString method, of class State.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        State instance = new State("MN", 7.2);
        
        String expResult = "MN7.2";
        String result = instance.toString();
        
        assertEquals(expResult, result);
    
    }
    
}
