/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aegis.cms.dao;

import com.aegis.cms.model.Post;
import com.aegis.cms.model.Tag;
import com.aegis.cms.model.User;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
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
public class PostTagDaoTest {

    private PostTagDao ptDao;
    private UserDao userDao;
    private ApplicationContext ctx;

    public PostTagDaoTest() {
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
        ptDao = ctx.getBean("cmspt", PostTagDao.class);
        userDao = ctx.getBean("cmsuser", UserDao.class);

    }

    @After
    public void tearDown() {
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from post");
        cleaner.execute("delete from users");
        cleaner.execute("delete from tag");
    }

    @Test
    public void testAddPostGetAllPosts() {
        System.out.println("addPost");

        Post tPost = new Post();
        Date date = Calendar.getInstance().getTime();

        tPost.setTitle("Test Post");
        tPost.setBody("This is a test body");
        tPost.setTags("this post, has, two tags");
        tPost.setPostDate(date);
        tPost.setExpiration(null);
        tPost.setIsPublished(true);
        tPost.setAuthor("Larry");

        Post dbPost = ptDao.addPost(tPost);
        assertTrue(ptDao.getAllPosts().size() == 1);
        
        tPost.setPostId(dbPost.getPostId());
        assertEquals(tPost, dbPost);

    }

    @Test
    public void testGetAuthorFromUserNameAddUserDeleteUser() {
        System.out.println("getAuthorFromUserName");
        User user = new User();
        user.setPublicName("Larry Bobby");
        user.setUserName("lb22");
        user.setAuthority("ROLE_ADMIN");
        user.setPassword("1234");
        userDao.addUser(user);
        
        String result = ptDao.getAuthorFromUserName(user.getUserName());
        assertEquals(user.getPublicName(), result);
    }

    @Test
    public void testGetAllVisiblePosts() {
        System.out.println("getAllVisiblePosts");
        Post tPost = new Post();
        Post tPost2 = new Post();
        Post tPost3 = new Post();
        Post tPost4 = new Post();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = Calendar.getInstance().getTime();
        Calendar myCalendar2 = new GregorianCalendar(1901, 1, 1);
        Date date2 = myCalendar2.getTime();
        Calendar myCalendar3 = new GregorianCalendar(3015, 1, 1);
        Date date3 = myCalendar3.getTime();
        // Regular post, no expiration
        tPost.setTitle("Test Post 1");
        tPost.setBody("This is a test body");
        tPost.setTags("this post, has, three tags");
        tPost.setPostDate(date);
        tPost.setExpiration(null);
        tPost.setIsPublished(true);
        tPost.setAuthor("Larry");
        ptDao.addPost(tPost);
        // Post set to not visible
        tPost2.setTitle("Test Post 2");
        tPost2.setBody("This is a test body");
        tPost2.setTags("this post, has, three tags");
        tPost2.setPostDate(date);
        tPost2.setExpiration(null);
        tPost2.setIsPublished(false);
        tPost2.setAuthor("Larry");
        ptDao.addPost(tPost2);
        // Post that is already expired
        tPost3.setTitle("Test Post 3");
        tPost3.setBody("This is a test body");
        tPost3.setTags("this post, has, three tags");
        tPost3.setPostDate(date);
        tPost3.setExpiration(date2);
        tPost3.setIsPublished(false);
        tPost3.setAuthor("Larry");
        ptDao.addPost(tPost3);
        // Post that has a date in future
        tPost4.setTitle("Test Post 4");
        tPost4.setBody("This is a test body");
        tPost4.setTags("this post, has, three tags");
        tPost4.setPostDate(date3);
        tPost4.setExpiration(null);
        tPost4.setIsPublished(true);
        tPost4.setAuthor("Larry");
        ptDao.addPost(tPost4);
        
        List<Post> result = ptDao.getAllVisiblePosts();
        assertEquals(1, result.size());

        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetAllTags() {
        System.out.println("getAllTags");
        Post tPost = new Post();
        Date date = Calendar.getInstance().getTime();
        
        tPost.setTitle("Test Post 1");
        tPost.setBody("This is a test body");
        tPost.setTags("this post, has, three tags");
        tPost.setPostDate(date);
        tPost.setExpiration(null);
        tPost.setIsPublished(true);
        tPost.setAuthor("Larry");
        ptDao.addPost(tPost);
        
        List<Tag> result = ptDao.getAllTags();
        assertEquals(3, result.size());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetAllPostsByTag() {
        System.out.println("getAllPostsByTag");
        Post tPost = new Post();
        Post tPost2 = new Post();
        Post tPost3 = new Post();
        Post returnedPost1;
        int id1 = 0; int id2 = 0; int id3 = 0; 
        Date date = Calendar.getInstance().getTime();
        
        tPost.setTitle("Test Post 1");
        tPost.setBody("This is a test body");
        tPost.setTags("three, two, one");
        tPost.setPostDate(date);
        tPost.setExpiration(null);
        tPost.setIsPublished(true);
        tPost.setAuthor("Larry");
        returnedPost1 = ptDao.addPost(tPost);
        
        Set<Tag> tags1 = returnedPost1.getTags();
        for(Tag tag : tags1){
            if (tag.getTagName().equals("one")){
                id1 = tag.getTagId();
            }
            else if (tag.getTagName().equals("two")){
                id2 = tag.getTagId();
            }
            else if (tag.getTagName().equals("three")){
                id3 = tag.getTagId();
            }
        }
        
        tPost2.setTitle("Test Post 2");
        tPost2.setBody("This is a test body");
        tPost2.setTags("three, two");
        tPost2.setPostDate(date);
        tPost2.setExpiration(null);
        tPost2.setIsPublished(true);
        tPost2.setAuthor("Larry");
        ptDao.addPost(tPost2);
        
        tPost3.setTitle("Test Post 3");
        tPost3.setBody("This is a test body");
        tPost3.setTags("three");
        tPost3.setPostDate(date);
        tPost3.setExpiration(null);
        tPost3.setIsPublished(true);
        tPost3.setAuthor("Larry");
        ptDao.addPost(tPost3);
        
        List<Post> result = ptDao.getAllPostsByTag(id3);
        assertEquals(3, result.size());
        List<Post> result2 = ptDao.getAllPostsByTag(id2);
        assertEquals(2, result2.size());
        List<Post> result3 = ptDao.getAllPostsByTag(id1);
        assertEquals(1, result3.size());
        //TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetPostById() {
        System.out.println("getPostById");
        Post tPost = new Post();
        Post returnedPost;
        Date date = Calendar.getInstance().getTime();
        int id = 0;
        
        tPost.setTitle("Test Post 1");
        tPost.setBody("This is a test body");
        tPost.setTags("this post, has, three tags");
        tPost.setPostDate(date);
        tPost.setExpiration(null);
        tPost.setIsPublished(false);
        tPost.setAuthor("Larry");
        returnedPost = ptDao.addPost(tPost);
        id = returnedPost.getPostId();
        
        Post result = ptDao.getPostById(id);
        assertTrue(result.getTitle().equals("Test Post 1"));
        assertTrue(result.getBody().equals("This is a test body"));
        assertTrue(result.getAuthor().equals("Larry"));
        assertFalse(result.getIsPublished());
        assertEquals(result.getExpiration(), null);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetAllPosts() {
        System.out.println("getAllPosts");
        Post tPost = new Post();
        Post tPost2 = new Post();
        Post tPost3 = new Post();
        Post tPost4 = new Post();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = Calendar.getInstance().getTime();
        Calendar myCalendar2 = new GregorianCalendar(1901, 1, 1);
        Date date2 = myCalendar2.getTime();
        Calendar myCalendar3 = new GregorianCalendar(3015, 1, 1);
        Date date3 = myCalendar3.getTime();
        // Regular post, no expiration
        tPost.setTitle("Test Post 1");
        tPost.setBody("This is a test body");
        tPost.setTags("this post, has, three tags");
        tPost.setPostDate(date);
        tPost.setExpiration(null);
        tPost.setIsPublished(true);
        tPost.setAuthor("Larry");
        ptDao.addPost(tPost);
        // Post set to not visible
        tPost2.setTitle("Test Post 2");
        tPost2.setBody("This is a test body");
        tPost2.setTags("this post, has, three tags");
        tPost2.setPostDate(date);
        tPost2.setExpiration(null);
        tPost2.setIsPublished(false);
        tPost2.setAuthor("Larry");
        ptDao.addPost(tPost2);
        // Post that is already expired
        tPost3.setTitle("Test Post 3");
        tPost3.setBody("This is a test body");
        tPost3.setTags("this post, has, three tags");
        tPost3.setPostDate(date);
        tPost3.setExpiration(date2);
        tPost3.setIsPublished(false);
        tPost3.setAuthor("Larry");
        ptDao.addPost(tPost3);
        // Post that has a date in future
        tPost4.setTitle("Test Post 4");
        tPost4.setBody("This is a test body");
        tPost4.setTags("this post, has, three tags");
        tPost4.setPostDate(date3);
        tPost4.setExpiration(null);
        tPost4.setIsPublished(true);
        tPost4.setAuthor("Larry");
        ptDao.addPost(tPost4);
        
        List<Post> result = ptDao.getAllPosts();
        assertEquals(4, result.size());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    @Test
    public void testEditPost() {
        System.out.println("editPost");
        Post tPost = new Post();
        Date date = Calendar.getInstance().getTime();
        Calendar myCalendar2 = new GregorianCalendar(1901, 1, 1);
        Date date2 = myCalendar2.getTime();
        Calendar myCalendar3 = new GregorianCalendar(3015, 1, 1);
        Date date3 = myCalendar3.getTime();
        
        tPost.setTitle("Test Post 1");
        tPost.setBody("This is a test body");
        tPost.setTags("this post, has, three tags");
        tPost.setPostDate(date);
        tPost.setExpiration(null);
        tPost.setIsPublished(true);
        tPost.setAuthor("Larry");
        ptDao.addPost(tPost);
        
        tPost.setTitle("Edit post");
        tPost.setBody("Edit body");
        tPost.setTags("Edit, tags");
        tPost.setPostDate(date2);
        tPost.setExpiration(date3);
        tPost.setAuthor("Bob");
        ptDao.editPost(tPost);
        
        List<Post> allposts = ptDao.getAllPosts();
        Post editedPost = allposts.get(0);
        assertTrue(editedPost.getTitle().equals("Edit post"));
        assertTrue(editedPost.getBody().equals("Edit body"));
        assertTrue(editedPost.getTags().size() == 2);
        assertEquals(0, editedPost.getPostDate().compareTo(date2));
        assertEquals(0, editedPost.getExpiration().compareTo(date3));
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    @Test
    public void testPublishPost() {
        System.out.println("publishPost");
        Post tPost = new Post();
        Date date = Calendar.getInstance().getTime();
        int id = 0;
        Post returnedPost;
        
        tPost.setTitle("Test Post 1");
        tPost.setBody("This is a test body");
        tPost.setTags("this post, has, three tags");
        tPost.setPostDate(date);
        tPost.setExpiration(null);
        tPost.setIsPublished(false);
        tPost.setAuthor("Larry");
        ptDao.addPost(tPost);
        returnedPost = ptDao.addPost(tPost);
        id = returnedPost.getPostId();
       
        ptDao.publishPost(id);
        
        assertTrue(ptDao.getPostById(id).getIsPublished());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    @Test
    public void testUnpublishPost() {
        Post tPost = new Post();
        Date date = Calendar.getInstance().getTime();
        int id = 0;
        Post returnedPost;
        
        tPost.setTitle("Test Post 1");
        tPost.setBody("This is a test body");
        tPost.setTags("this post, has, three tags");
        tPost.setPostDate(date);
        tPost.setExpiration(null);
        tPost.setIsPublished(true);
        tPost.setAuthor("Larry");
        ptDao.addPost(tPost);
        returnedPost = ptDao.addPost(tPost);
        id = returnedPost.getPostId();
       
        ptDao.unpublishPost(id);
        
        assertFalse(ptDao.getPostById(id).getIsPublished());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
