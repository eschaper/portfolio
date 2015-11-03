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
public class ProductTest {
    
    public ProductTest() {
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
     * Test of getType method, of class Product.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Product instance = new Product("Wood", 32, 45);
        
        String expResult = "Wood";
        String result = instance.getType();
        
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCostPerSqFt method, of class Product.
     */
    @Test
    public void testGetCostPerSqFt() {
        System.out.println("getCostPerSqFt");
        Product instance = new Product("Wood", 32, 45);
        
        double expResult = 32;
        double result = instance.getCostPerSqFt();
        
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getLaborPerSqFt method, of class Product.
     */
    @Test
    public void testGetLaborPerSqFt() {
        System.out.println("getLaborPerSqFt");
        Product instance = new Product("Wood", 32, 45);
        
        double expResult = 45;
        double result = instance.getLaborPerSqFt();
        
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of toString method, of class Product.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Product instance = new Product("Wood", 32, 45);
        
        String expResult = "Wood32.045.0";
        String result = instance.toString();
        
        assertEquals(expResult, result);
    }
    
}
