package com.aegis.cms.dao;

import com.aegis.cms.model.User;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoTest {

    private UserDao userDao;
    private ApplicationContext ctx;

    public UserDaoTest() {

        ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
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
        userDao = ctx.getBean("cmsUserDao", UserDao.class);
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from users");
    }

    @After
    public void tearDown() {
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from users");
    }

    @Test
    public void testAddUser() {
        System.out.println("addUser");
        User newUser = new User();
        newUser.setPublicName("larry Bobby");
        newUser.setUserName("lars");
        newUser.setAuthority("ROLE_ADMIN");
        newUser.setPassword("1234");
        userDao.addUser(newUser);

        User result = userDao.getUserById(newUser.getUserId());
        assertTrue(result instanceof User);
        assertEquals("lars", result.getUserName());
    }

    @Test
    public void testGetUserById() {
        System.out.println("getUserById");
        User newUser = new User();
        newUser.setPublicName("John Jones");
        newUser.setUserName("johnny");
        newUser.setAuthority("ROLE_USER");
        newUser.setPassword("5678");
        userDao.addUser(newUser);

        User result = userDao.getUserById(newUser.getUserId());
        assertEquals("johnny", result.getUserName());
    }

    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        User newUser = new User();
        newUser.setPublicName("Mick Jones");
        newUser.setUserName("mickey");
        newUser.setAuthority("ROLE_ADMIN");
        newUser.setPassword("1234");
        userDao.addUser(newUser);
        userDao.deleteUser(newUser.getUserId());

        List<User> result = userDao.getAllUsers();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testEditUser() {
        System.out.println("editUser");
        User newUser = new User();
        newUser.setPublicName("Joe Strummer");
        newUser.setUserName("joey");
        newUser.setAuthority("ROLE_USER");
        newUser.setPassword("5678");
        userDao.addUser(newUser);

        assertEquals(userDao.getUserById(newUser.getUserId()).getUserName(), "joey");

        newUser.setUserName("bobby");
        userDao.editUser(newUser);

        assertEquals(userDao.getUserById(newUser.getUserId()).getUserName(), "bobby");
    }

    @Test
    public void testGetAllUsers() {
        System.out.println("getAllUsers");

        User newUser = new User();
        newUser.setPublicName("Joe Strummer");
        newUser.setUserName("joey");
        newUser.setAuthority("ROLE_USER");
        newUser.setPassword("5678");
        userDao.addUser(newUser);

        newUser.setPublicName("Mick Jones");
        newUser.setUserName("mickey");
        newUser.setAuthority("ROLE_ADMIN");
        newUser.setPassword("1234");
        userDao.addUser(newUser);

        newUser.setPublicName("John Jones");
        newUser.setUserName("johnny");
        newUser.setAuthority("ROLE_USER");
        newUser.setPassword("5678");
        userDao.addUser(newUser);

        List<User> result = userDao.getAllUsers();
        assertEquals(3,result.size());
    }
}
