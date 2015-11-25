package com.aegis.cms.controller;

import com.aegis.cms.dao.PostTagDao;
import com.aegis.cms.model.Post;
import com.aegis.cms.model.Tag;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    PostTagDao dao;

    @Inject
    public HomeController(PostTagDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String displayHome() {
        return "home";
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getAllVisiblePosts() {
        return dao.getAllVisiblePosts();
    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    @ResponseBody
    public List<Tag> getAllTags() {
        return dao.getAllTags();
    }

    @RequestMapping(value = "/postsByTag/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getAllPostsByTag(@PathVariable("id") int id) {
        return dao.getAllPostsByTag(id);
    }

}
