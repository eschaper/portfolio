package com.aegis.cms.controller;

import com.aegis.cms.dao.PostTagDao;
import com.aegis.cms.model.Post;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class CreatePostsController {

    private PostTagDao dao;

    @Inject
    public CreatePostsController(PostTagDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/createPosts", method = RequestMethod.GET)
    public String displayCreatePosts() {
        return "createPosts";
    }

    @RequestMapping(value = "/makePost", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Post addPost(HttpServletRequest request, @Valid @RequestBody Post post) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            post.setIsPublished(true);
            post.setAuthor(dao.getAuthorFromUserName(request.getRemoteUser()));
            dao.addPost(post);
        } else if (request.isUserInRole("ROLE_USER")) {
            post.setIsPublished(false);
            post.setAuthor(dao.getAuthorFromUserName(request.getRemoteUser()));
            dao.addPost(post);
        }
        return post;
    }

}
