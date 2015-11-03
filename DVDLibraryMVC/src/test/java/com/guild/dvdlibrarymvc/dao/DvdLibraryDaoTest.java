///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package com.guild.dvdlibrarymvc.dao;

import com.guild.dvdlibrarymvc.model.Dvd;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class DvdLibraryDaoTest {
    
    DvdLibraryDao dao;
    
    public DvdLibraryDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("dvdLibraryDao", DvdLibraryDao.class);
        
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from dvds");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addDvd method, of class DvdLibraryDao.
     */
    @Test
    public void addGetDeleteDvd() {
        System.out.println("addGetDeleteDvd");
        
        Dvd dvd = new Dvd();
        dvd.setTitle("Cats");
        dvd.setReleaseDate("1997");
        dvd.setStudio("Paramount");
        dvd.setDirector("Spike");
        dvd.setMpaaRating("R");
        dvd.setNote("Okay");
        
        dao.addDvd(dvd);
        Dvd fromDb = dao.getDvdById(dvd.getDvdId());
        assertEquals(fromDb, dvd);
        dao.removeDvd(dvd.getDvdId());
        assertNull(dao.getDvdById(dvd.getDvdId()));
        
    }

    @Test
    public void testUpdateDvd() {
        System.out.println("updateDvd");
       
        Dvd dvd = new Dvd();
        dvd.setTitle("Cats");
        dvd.setReleaseDate("1997");
        dvd.setStudio("Paramount");
        dvd.setDirector("Spike");
        dvd.setMpaaRating("R");
        dvd.setNote("Okay");
        
        dao.addDvd(dvd);
        dvd.setDirector("Bobby");
        dao.updateDvd(dvd);
        
        Dvd fromDb = dao.getDvdById(dvd.getDvdId());
        assertEquals(fromDb, dvd);
        
    }

    @Test
    public void testGetAllDvds() {
        System.out.println("getAllDvds");
        
        Dvd dvd = new Dvd();
        dvd.setTitle("Cats");
        dvd.setReleaseDate("1997");
        dvd.setStudio("Paramount");
        dvd.setDirector("Spike");
        dvd.setMpaaRating("R");
        dvd.setNote("Okay");
        dao.addDvd(dvd);
        
        Dvd dvd2 = new Dvd();
        dvd2.setTitle("Bob joe");
        dvd2.setReleaseDate("2005");
        dvd2.setStudio("Paramount");
        dvd2.setDirector("Spike");
        dvd2.setMpaaRating("PG-13");
        dvd2.setNote("Good");
        dao.addDvd(dvd2);
        
        assertTrue(dao.getAllDvds().size() == 2);
        
    }

    @Test
    public void testSearchDvds() {
        System.out.println("searchDvds");
        
        Dvd dvd = new Dvd();
        dvd.setTitle("Cats");
        dvd.setReleaseDate("1997");
        dvd.setStudio("Paramount");
        dvd.setDirector("Spike");
        dvd.setMpaaRating("R");
        dvd.setNote("Okay");
        dao.addDvd(dvd);
        
        Dvd dvd2 = new Dvd();
        dvd2.setTitle("Bob joe");
        dvd2.setReleaseDate("2005");
        dvd2.setStudio("Paramount");
        dvd2.setDirector("Spike");
        dvd2.setMpaaRating("PG-13");
        dvd2.setNote("Good");
        dao.addDvd(dvd2);
    
        Dvd dvd3 = new Dvd();
        dvd3.setTitle("Tornado");
        dvd3.setReleaseDate("2005");
        dvd3.setStudio("AMC");
        dvd3.setDirector("Miyazaki");
        dvd3.setMpaaRating("PG-13");
        dvd3.setNote("Good");
        dao.addDvd(dvd3);
        
        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.DIRECTOR, "Spike");
        List<Dvd> results = dao.searchDvds(criteria);
        
        assertTrue(results.size() == 2);
        
        criteria.put(SearchTerm.RELEASE_DATE, "2005");
        results = dao.searchDvds(criteria);
        
        assertTrue(results.size() == 1);
        assertEquals(results.get(0), dvd2);
        
    }
    
}
