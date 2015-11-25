package com.aegis.cms.controller;

import com.aegis.cms.dao.PostTagDao;
import com.aegis.cms.model.Post;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class PostManagerController {

    PostTagDao dao;

    @Inject
    public PostManagerController(PostTagDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/postMan", method = RequestMethod.GET)
    public String displayPostManager() {
        return "postManager";
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Post getPostById(@PathVariable("id") int id) {
        return dao.getPostById(id);
    }
    
    @RequestMapping(value = "/allposts", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getAllPosts() {
        return dao.getAllPosts();
    }
    
    @RequestMapping(value = "/allunpub", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getAllUnpublished() {
        return dao.getAllUnpublished();
    }
    
    @RequestMapping(value = "/allexpired", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getAllExpired() {
        return dao.getAllExpired();
    }
    
    @RequestMapping(value="publish/{postId}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void publishPost(@PathVariable("postId") int postId){
        dao.publishPost(postId);
    }
    
    @RequestMapping(value="unpublish/{postId}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unpublishPost(@PathVariable("postId") int postId){
        dao.unpublishPost(postId);
    }
    
    @RequestMapping(value="post/{postId}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editPost(@PathVariable("postId") int postId, @Valid @RequestBody Post post){
        post.setPostId(postId);
        dao.editPost(post);
    }
    
    @RequestMapping (value="post/{id}", method=RequestMethod.DELETE)
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable("id")int id) {
        dao.deletePostTagById(id);
    }
}
