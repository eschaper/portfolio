package com.aegis.cms.dao;

import com.aegis.cms.model.StaticContent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class StaticDaoTest {

    private StaticDao dao;

    public StaticDaoTest() {
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
        dao = ctx.getBean("cmsstatic", StaticDao.class);
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from static_content");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddStaticContent() {
        StaticContent tContent = new StaticContent();
        
        tContent.setContent("Test Content");
        tContent.setContentId(1);
        dao.addStaticContent(tContent);
        
        StaticContent fromDb = dao.getStaticContent();        
        assertTrue(fromDb instanceof StaticContent);
        assertEquals(fromDb, tContent);
    }

    @Test
    public void testGetStaticContent() {
        StaticContent tContent = new StaticContent();
        
        tContent.setContent("More Test Content");
        tContent.setContentId(1);
        dao.addStaticContent(tContent);

        assertTrue(dao.getStaticContent() instanceof StaticContent);
        assertEquals(dao.getStaticContent(), tContent);
    }

    @Test
    public void testUpdateStaticContent() {
        StaticContent tContent = new StaticContent();
        tContent.setContent("Test Content");
        tContent.setContentId(1);
        
        StaticContent tContentUpdate = new StaticContent();
        tContentUpdate.setContent("Updated Content");
        tContentUpdate.setContentId(1);
        
        dao.addStaticContent(tContent);
        assertEquals(tContent, dao.getStaticContent());
        
        dao.updateStaticContent(tContentUpdate);        
        assertEquals(tContentUpdate, dao.getStaticContent());
    }

    @Test
    public void testDeleteStaticContent() {
        StaticContent tContent = new StaticContent();
        
        tContent.setContent("Its more content");
        tContent.setContentId(1);
        
        dao.addStaticContent(tContent);
        assertTrue(dao.getStaticContent() != null);
        
        dao.deleteStaticContent(tContent);
        assertNull(dao.getStaticContent());
    }
    
}