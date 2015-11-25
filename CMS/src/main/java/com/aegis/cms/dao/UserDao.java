
package com.aegis.cms.dao;

import com.aegis.cms.model.User;
import java.util.List;

public interface UserDao {

    public void addUser(User newUser);

    public void deleteUser(int id);

    public List<User> getAllUsers();

    public User editUser(User user);
    
    public User getUserById(int id);

}
