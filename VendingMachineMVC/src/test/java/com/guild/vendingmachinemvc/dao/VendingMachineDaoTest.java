/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guild.vendingmachinemvc.dao;

import com.guild.vendingmachinemvc.model.Item;
import com.guild.vendingmachinemvc.model.VendRequest;
import com.guild.vendingmachinemvc.model.VendResponse;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class VendingMachineDaoTest {
    
    private VendingMachineDao dao;
    
    public VendingMachineDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("vmdao", VendingMachineDao.class);
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from machine");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setTotalChange method and getTotalChange method of class VendingMachineDao.
     */
    @Test
    public void testSetGetTotalChange() {
        System.out.println("setTotalChange");
        
        dao.setTotalChange(100);
        assertTrue(dao.getTotalChange() == 100);
    }

    /**
     * Test of getItemBySlot method, of class VendingMachineDao.
     */
    @Test
    public void testAddItemGetItemBySlot() {
        System.out.println("getItemBySlot");
        Item item = new Item();
        item.setSlot("T3");
        item.setName("MnMs");
        item.setCost(200);
        item.setAmount(12);
        dao.addItem(item);
        
        assertEquals(dao.getItemBySlot(item.getSlot()), item);
    }

    /**
     * Test of updateItem method, of class VendingMachineDao.
     */
    @Test
    public void testUpdateItemGetItemBySlot() {
        System.out.println("getItemBySlot");
        Item item = new Item();
        item.setSlot("T3");
        item.setName("MnMs");
        item.setCost(200);
        item.setAmount(12);
        dao.addItem(item);
        assertTrue(dao.getContents().size() == 1);
        assertEquals(dao.getItemBySlot(item.getSlot()), item);
        
        item.setAmount(23);
        dao.updateItem(item);
        assertEquals(dao.getItemBySlot(item.getSlot()), item);
    }

    /**
     * Test of removeItem method, of class VendingMachineDao.
     */
    @Test
    public void testRemoveItemGetAllItems() {
        Item item = new Item();
        item.setSlot("B4");
        item.setName("Snickers");
        item.setCost(75);
        item.setAmount(14);
        dao.addItem(item);
        
        Item item2 = new Item();
        item2.setSlot("T3");
        item2.setName("MnMs");
        item2.setCost(200);
        item2.setAmount(12);
        dao.addItem(item2);
        
        Item item3 = new Item();
        item3.setSlot("A7");
        item3.setName("Chips");
        item3.setCost(150);
        item3.setAmount(12);
        dao.addItem(item3);
        
        List<Item> list = dao.getContents();
        assertEquals(3, list.size());
        
        dao.removeItem(item.getSlot());
        assertEquals(2, dao.getContents().size());
        
        assertNull(dao.getItemBySlot(item.getSlot()));
    }

    /**
     * Test of returnChange method, of class VendingMachineDao.
     */
    @Test
    public void testReturnChange() {
        
        assertEquals("There is no change.", dao.returnChange(0));
        assertEquals("Your change is: 1 quarter", dao.returnChange(25));
    }

    /**
     * Test of vendRequest method, of class VendingMachineDao.
     */
    @Test
    public void testVendRequest() {
        VendRequest req = new VendRequest();
        Item item = new Item();
        VendResponse resp;
        
        item.setSlot("B4");
        item.setName("Snickers");
        item.setCost(75);
        item.setAmount(14);
        dao.addItem(item);
        dao.setTotalChange(400);
        
        req.setSlot(item.getSlot());
        resp = dao.vendRequest(req);        
        assertEquals(3, resp.getStatus());        
        
        dao.setTotalChange(50);
        resp = dao.vendRequest(req);
        assertEquals(2, resp.getStatus());
        
        req.setSlot("44");
        resp = dao.vendRequest(req);
        assertEquals(1, resp.getStatus());
      
    }
    
}
