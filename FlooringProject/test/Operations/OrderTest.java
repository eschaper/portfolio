/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operations;

import java.text.NumberFormat;
import java.util.Locale;
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
public class OrderTest {

    public OrderTest() {
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
     * Test of toString method, of class Order.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Order instance = new Order(2, "Last Name", "First Name", 24.5,
                new State("MN", 7.2), new Product("Wood", 32, 45));
        String expResult = "Order #2. Last Name, First Name"
                + "\nArea: 24.5 sqft. Product: Wood."
                + "\nState: MN. Total: " + instance.getProjectTotal();
        String result = instance.toString();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setCustLastName method, of class Order.
     */
    @Test
    public void testSetCustLastName() {
        System.out.println("setCustLastName");
        Order instance = new Order(2, "Last Name", "First Name", 24.5,
                new State("MN", 7.2), new Product("Wood", 32, 45));
        String custLastName = "Bob";
        instance.setCustLastName(custLastName);

        assertTrue(instance.getCustLastName().equals(custLastName));

    }

    /**
     * Test of setCustFirstName method, of class Order.
     */
    @Test
    public void testSetCustFirstName() {
        System.out.println("setCustFirstName");

        Order instance = new Order(2, "Last Name", "First Name", 24.5,
                new State("MN", 7.2), new Product("Wood", 32, 45));
        String custFirstName = "Willy";
        instance.setCustFirstName(custFirstName);

        assertTrue(instance.getCustFirstName().equals(custFirstName));

    }

    /**
     * Test of setArea method, of class Order.
     */
    @Test
    public void testSetArea() {
        System.out.println("setArea");
        Order instance = new Order(2, "Last Name", "First Name", 24.5,
                new State("MN", 7.2), new Product("Wood", 32, 45));

        double area = 33;
        instance.setArea(area);

        assertTrue(area == instance.getArea());
    }

    /**
     * Test of setState method, of class Order.
     */
    @Test
    public void testSetState() {
        System.out.println("setState");
        Order instance = new Order(2, "Last Name", "First Name", 24.5,
                new State("MN", 7.2), new Product("Wood", 32, 45));

        State state = new State("wi", 5.4);
        instance.setState(state);

        assertTrue(state.toString().equals(instance.getState().toString()));
    }

    /**
     * Test of setProduct method, of class Order.
     */
    @Test
    public void testSetProduct() {
        System.out.println("setProduct");
        Order instance = new Order(2, "Last Name", "First Name", 24.5,
                new State("MN", 7.2), new Product("Wood", 32, 45));

        Product product = new Product("concrete", 5, 4);
        instance.setProduct(product);

        assertTrue(product.toString().equals(instance.getProduct().toString()));
    }

    /**
     * Test of getOrderNum method, of class Order.
     */
    @Test
    public void testGetOrderNum() {
        System.out.println("getOrderNum");

        Order instance = new Order(2, "Last Name", "First Name", 24.5,
                new State("MN", 7.2), new Product("Wood", 32, 45));
        int expResult = 2;
        int result = instance.getOrderNum();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCustLastName method, of class Order.
     */
    @Test
    public void testGetCustLastName() {
        System.out.println("getCustLastName");
        Order instance = new Order(2, "Last Name", "First Name", 24.5,
                new State("MN", 7.2), new Product("Wood", 32, 45));

        String expResult = "Last Name";
        String result = instance.getCustLastName();

        assertEquals(expResult, result);
    }

    /**
     * Test of getCustFirstName method, of class Order.
     */
    @Test
    public void testGetCustFirstName() {
        System.out.println("getCustFirstName");
        Order instance = new Order(2, "Last Name", "First Name", 24.5,
                new State("MN", 7.2), new Product("Wood", 32, 45));

        String expResult = "First Name";
        String result = instance.getCustFirstName();
        assertEquals(expResult, result);

    }

    /**
     * Test of getArea method, of class Order.
     */
    @Test
    public void testGetArea() {
        System.out.println("getArea");
        Order instance = new Order(2, "Last Name", "First Name", 24.5,
                new State("MN", 7.2), new Product("Wood", 32, 45));

        double expResult = 24.5;
        double result = instance.getArea();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getState method, of class Order.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        Order instance = new Order(2, "Last Name", "First Name", 24.5,
                new State("MN", 7.2), new Product("Wood", 32, 45));
        State expResult = new State("MN", 7.2);
        StateInterface result = instance.getState();
        assertEquals(expResult.toString(), result.toString());
        
    }

    /**
     * Test of getProduct method, of class Order.
     */
    @Test
    public void testGetProduct() {
        System.out.println("getProduct");

        Order instance = new Order(2, "Last Name", "First Name", 24.5,
                new State("MN", 7.2), new Product("Wood", 32, 45));

        Product expResult = new Product("Wood", 32, 45);
        ProductInterface result = instance.getProduct();
        assertEquals(expResult.toString(), result.toString());

    }

    /**
     * Test of getMaterialCostTotal method, of class Order.
     */
    @Test
    public void testGetMaterialCostTotal() {
        System.out.println("getMaterialCostTotal");

        Order instance = new Order(2, "Last Name", "First Name", 24.5,
                new State("MN", 7.2), new Product("Wood", 32, 45));

        double expResult = instance.getArea() * instance.getProduct().getCostPerSqFt();
        double result = instance.getMaterialCostTotal();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getLaborCostTotal method, of class Order.
     */
    @Test
    public void testGetLaborCostTotal() {
        System.out.println("getLaborCostTotal");

        Order instance = new Order(2, "Last Name", "First Name", 24.5,
                new State("MN", 7.2), new Product("Wood", 32, 45));

        double expResult = instance.getArea() * instance.getProduct().getLaborPerSqFt();
        double result = instance.getLaborCostTotal();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getTaxTotal method, of class Order.
     */
    @Test
    public void testGetTaxTotal() {
        System.out.println("getTaxTotal");
        
        Order instance = new Order(2, "Last Name", "First Name", 24.5,
                new State("MN", 7.2), new Product("Wood", 32, 45));
        
        double expResult = (instance.getLaborCostTotal() 
                + instance.getMaterialCostTotal()) 
                * instance.getState().getTaxRate() / 100;
        double result = instance.getTaxTotal();
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of getProjectTotal method, of class Order.
     */
    @Test
    public void testGetProjectTotal() {
        System.out.println("getProjectTotal");
        
        Order instance = new Order(2, "Last Name", "First Name", 24.5,
                new State("MN", 7.2), new Product("Wood", 32, 45));
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
        double total;
        
        total = instance.getLaborCostTotal() 
                + instance.getMaterialCostTotal() 
                + instance.getTaxTotal();
        
        
        String expResult = n.format(total);
        String resultString = instance.getProjectTotal();

        assertTrue(expResult.equals(resultString));
        
    }

}
