package com.aegis.cms.controller;

import com.aegis.cms.dao.UserDao;
import com.aegis.cms.model.User;
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
public class UserManagerController {

    private UserDao userManDao;
    
    @RequestMapping(value = "/userMan", method = RequestMethod.GET)
    public String displayUserManager() {
        return "userManager";
    }

    @Inject
    public UserManagerController(UserDao userManDao) {
        this.userManDao = userManDao;
    }

    @RequestMapping(value = "/displayUserList", method = RequestMethod.GET)
    @ResponseBody
    public List <User> displayUserList() {
        return userManDao.getAllUsers();
    }
    
    @RequestMapping (value="user", method=RequestMethod.POST)
    @ResponseStatus (HttpStatus.CREATED)
    @ResponseBody
    public User addUser(@Valid @RequestBody User user) {
        userManDao.addUser(user);
        return user;
    }
    
    @RequestMapping (value="user/{id}", method=RequestMethod.DELETE)
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id")int id) {
        userManDao.deleteUser(id);
    }
    
    @RequestMapping (value="user/{id}", method=RequestMethod.GET)
    @ResponseBody
    public User getUser(@PathVariable("id")int id) {
        return userManDao.getUserById(id);
    }
    
    @RequestMapping (value = "user/{id}", method=RequestMethod.PUT)
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void editUser(@PathVariable ("id")int id, @Valid @RequestBody User user) {
        user.setUserId(id);
        userManDao.editUser(user);
    } 
}
