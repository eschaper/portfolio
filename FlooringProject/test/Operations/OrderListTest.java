/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operations;

import java.text.SimpleDateFormat;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class OrderListTest {
    
    public OrderListTest() {
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
     * Test of dateExist method, of class OrderList.
     */
    @Test
    public void testDateExist() {
        System.out.println("dateExist");
        OrderList instance = new OrderList();
        instance.populate();
        
        String date = "09042015";
        boolean expResult = true;
        boolean result = instance.dateExist(date);
        assertEquals(expResult, result);
        
        date = "000000";
        expResult = false;
        result = instance.dateExist(date);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of orderExist method, of class OrderList.
     */
    @Test
    public void testOrderExist() {
        System.out.println("orderExist");
        OrderList instance = new OrderList();
        instance.populate();

        String date = "09042015";
        int orderNum = 2;
        boolean expResult = true;
        boolean result = instance.orderExist(date, orderNum);
        assertEquals(expResult, result);
        
        orderNum = 99;
        expResult = false;
        result = instance.orderExist(date, orderNum);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of displayOrders method, of class OrderList.
     */
    @Test
    public void testDisplayOrders() {
        System.out.println("displayOrders");
        OrderList instance = new OrderList();
        instance.populate();
        
        String date = "///";
        String expResult = "No orders for this day.\n";
        String result = instance.displayOrders(date);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getValidProduct method, of class OrderList.
     */
    @Test
    public void testGetValidProduct() {
        System.out.println("getProduct");
        OrderList instance = new OrderList();
        instance.populate();
        
        String material = "plywood";
        Product expResult = null;
        ProductInterface result = instance.getValidProduct(material);
        assertEquals(expResult, result);
    }

    /**
     * Test of addOrder method, of class OrderList.
     */
    @Test
    public void testAddOrder() {
        System.out.println("addOrder");
        OrderList instance = new OrderList();
        instance.populate();
        
        String date = "1234";
        Order order  = new Order(3, "Last Name", "First Name", 24.5,
                new State("MN", 7.2), new Product("Wood", 32, 45));
        instance.addOrder(date, order);
        
        // use orderExists to check if addOrder was sucessful
        int orderNum = 3;
        boolean expResult = true;
        boolean result = instance.orderExist(date, orderNum);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of removeOrder method, of class OrderList.
     */
    @Test
    public void testRemoveOrder() {
        System.out.println("removeOrder");
        OrderList instance = new OrderList();
        instance.populate();
        
        String date = "09042015";
        int orderNum = 2;
        
        // use orderExists to check if addOrder was sucessful
        instance.removeOrder(date, orderNum);
        boolean expResult = false;
        boolean result = instance.orderExist(date, orderNum);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of populate method, of class OrderList.
     */
    @Test
    public void testPopulate() {
        System.out.println("populate");
        OrderList instance = new OrderList();
        instance.populate();
        
    }

    /**
     * Test of getValidState method, of class OrderList.
     */
    @Test
    public void testGetValidState() {
        System.out.println("getState");
        OrderList instance = new OrderList();
        instance.populate();
        
        String state = "ky";
        State expResult = null;
        StateInterface result = instance.getValidState(state);
        assertEquals(expResult, result);
        
        state = "mn";
        String expectedResult = "MN";
        result = instance.getValidState(state);
        assertEquals(expectedResult, result.getStateName());
    }

    /**
     * Test of getOrderNum method, of class OrderList.
     */
    @Test
    public void testGetOrderNum() {
        System.out.println("getOrderNum");
        OrderList instance = new OrderList();
        instance.populate();
        
        int expResult = 3;
        int result = instance.getOrderNum();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getTodaysDate method, of class OrderList.
     */
    @Test
    public void testGetTodaysDate() {
        System.out.println("getTodaysDate");
        
        OrderList instance = new OrderList();
        instance.setCalendar();
        
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MMddYYYY");
        String expResult = formatter.format(today);
        
        String result = instance.getTodaysDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrder method, of class OrderList.
     */
    @Test
    public void testGetOrder() {
        System.out.println("getOrder");
        
        OrderList instance = new OrderList();
        instance.populate();
        
        String date = "09042015";
        int orderNum = 2;
        Order expResult = new Order(2, "Last Name", "First Name", 24.5,
                new State("MN", 7.2), new Product("Wood", 32, 45));
        OrderInterface result = instance.getOrder(date, orderNum);
        assertEquals(expResult.getCustFirstName(), result.getCustFirstName());
    }

    /**
     * Test of getOrderList method, of class OrderList.
     */
    @Test
    public void testGetOrderList() {
        System.out.println("getOrderList");
        OrderList instance = new OrderList();
        instance.populate();
        
        HashMap<String, ArrayList<Order>> expResult = new HashMap<>();
        String date;
        ArrayList<Order> order = new ArrayList<>();
        Order ord1, ord2;
        date = "09042015";
        ord1 = new Order(2, "Last Name", "First Name", 24.5,
                new State("MN", 7.2), new Product("Wood", 32, 45));
        ord2 = new Order(42, "Last", "First", 75,
                new State("MN", 7.2), new Product("Wood", 32, 45));
        order.add(ord2);
        order.add(ord1);
        expResult.put(date, order);
        
        HashMap<String, ArrayList<OrderInterface>> result = instance.getOrderList();
        //assertEquals(expResult, result);
        
        Set<String> keys = result.keySet();
        for (String key : keys) {
            ArrayList<OrderInterface> test = result.get(key);
            ArrayList<Order> master = expResult.get(key);
            for (int i = 0; i < test.size(); i++) {
                assertTrue(test.get(i).toString().equals(master.get(i).toString()));
            }
        }
    }

    /**
     * Test of addProducts method, of class OrderList.
     */
    @Test
    public void testAddProducts() {
        System.out.println("addProducts");
        
        OrderList instanceTest = new OrderList();
        
        ArrayList<ProductInterface> newProductList = new ArrayList<>();
        newProductList.add(new Product("Wood", 32, 45));
        newProductList.add(new Product("Laminate", 10, 5));

        instanceTest.addProducts(newProductList);
        for (int i = 0; i < newProductList.size(); i++) {
            
            ProductInterface test = newProductList.get(i);
            ProductInterface master = instanceTest.getValidProduct(test.getType());
            assertTrue(test.toString().equals(master.toString()));
        }
        
    }

    /**
     * Test of addStates method, of class OrderList.
     */
    @Test
    public void testAddStates() {
        System.out.println("addStates");
        
        OrderList instanceTest = new OrderList();
        
        ArrayList<StateInterface> newStateList = new ArrayList<>();
        newStateList.add(new State("MN", 7.2));
        newStateList.add(new State("WI", 9));

        instanceTest.addStates(newStateList);
        
        for (int i = 0; i < newStateList.size(); i++) {
            StateInterface test = newStateList.get(i);
            StateInterface master = instanceTest.getValidState(test.getStateName());
            assertTrue(test.toString().equals(master.toString()));
        }
        
    }

    /**
     * Test of addOrders method, of class OrderList.
     */
    @Test
    public void testAddOrders() {
        System.out.println("addOrders");
        
        OrderList instanceMaster = new OrderList();
        instanceMaster.populate();
        HashMap<String, ArrayList<OrderInterface>> ordersMaster = instanceMaster.getOrderList();
        
        OrderList instanceTest = new OrderList();
        HashMap<String, ArrayList<OrderInterface>> ordersTest = new HashMap<>();
        Order ord1, ord2;
        ArrayList<OrderInterface> order = new ArrayList<>();

        String date = "09042015";
        ord1 = new Order(2, "Last Name", "First Name", 24.5,
                new State("MN", 7.2), new Product("Wood", 32, 45));
        ord2 = new Order(42, "Last", "First", 75,
                new State("MN", 7.2), new Product("Wood", 32, 45));
        order.add(ord2);
        order.add(ord1);
        ordersTest.put(date, order);
        
        instanceTest.addOrders(ordersTest);
        
        Set<String> keys = ordersTest.keySet();
        
        for (String key : keys) {
            ArrayList<OrderInterface> test = ordersTest.get(key);
            ArrayList<OrderInterface> master = ordersMaster.get(key);
            for (int i = 0; i < test.size(); i++) {
                assertTrue(test.get(i).toString().equals(master.get(i).toString()));
            }
        }
        
    }

    /**
     * Test of setCalendar method, of class OrderList.
     */
    @Test
    public void testSetCalendar() {
        System.out.println("setCalendar");
        OrderList instance = new OrderList();
        instance.setCalendar();
        
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MMddYYYY");
        String expResult = formatter.format(today);
        
        String result = instance.getTodaysDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of displayDates method, of class OrderList.
     */
    @Test
    public void testDisplayDates() {
        System.out.println("displayDates");
        OrderList instance = new OrderList();
        instance.populate();
        
        String expResult = "09042015\n";
        String result = instance.displayDates();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of displayOrderNums method, of class OrderList.
     */
    @Test
    public void testDisplayOrderNums() {
        System.out.println("displayOrderNums");
        OrderList instance = new OrderList();
        instance.populate();
        
        String date = "09042015";
        String expResult = "42\n2\n";
        
        String result = instance.displayOrderNums(date);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of addProduct method, of class OrderList.
     */
    @Test
    public void testAddProduct() {
        System.out.println("addProduct");
        
        OrderList instance = new OrderList();
        instance.populate();
        
        ProductInterface product = new Product("Concrete", 2, 3);
        instance.addProduct(product);
        
        assertTrue(instance.getValidProduct("concrete").toString().equals(product.toString()));
    }

    /**
     * Test of addState method, of class OrderList.
     */
    @Test
    public void testAddState() {
        System.out.println("addState");
        
        OrderList instance = new OrderList();
        instance.populate();
        
        StateInterface state = new State("WY", 3.3);
        instance.addState(state);
        
        assertTrue(instance.getValidState("wy").toString().equals(state.toString()));
    }

    /**
     * Test of removeProduct method, of class OrderList.
     */
    @Test
    public void testRemoveProduct() {
        System.out.println("removeProduct");
        
        OrderList instance = new OrderList();
        instance.populate();
        
        ProductInterface product = instance.getValidProduct("wood");
        
        instance.removeProduct(product);
        
        assertFalse(instance.getProductList().contains(product));
        
    }

    /**
     * Test of removeState method, of class OrderList.
     */
    @Test
    public void testRemoveState() {
        System.out.println("removeState");
        
        OrderList instance = new OrderList();
        instance.populate();
        
        StateInterface state = instance.getValidState("mn");
        
        instance.removeState(state);
        
        assertFalse(instance.getStateList().contains(state));
    }

    /**
     * Test of getStateList method, of class OrderList.
     */
    @Test
    public void testGetStateList() {
        System.out.println("getStateList");
        
        OrderList instance = new OrderList();
        instance.populate();
        
        ArrayList<StateInterface> expResult = new ArrayList<>();

        expResult.add(new State("MN", 7.2));
        expResult.add(new State("WI", 9));
        
        ArrayList<StateInterface> result = instance.getStateList();
        
        for (int i = 0; i < result.size(); i++) {
            assertEquals(expResult.get(i).toString(), result.get(i).toString());
        }
        
    }

    /**
     * Test of getProductList method, of class OrderList.
     */
    @Test
    public void testGetProductList() {
        System.out.println("getProductList");
        
        OrderList instance = new OrderList();
        instance.populate();
        
        ArrayList<ProductInterface> expResult = new ArrayList<>();
        
        expResult.add(new Product("Wood", 32, 45));
        expResult.add(new Product("Laminate", 10, 5));
        
        ArrayList<ProductInterface> result = instance.getProductList();
        
        for (int i = 0; i < result.size(); i++) {
            assertEquals(expResult.get(i).toString(), result.get(i).toString());
        }
        
    }

}
