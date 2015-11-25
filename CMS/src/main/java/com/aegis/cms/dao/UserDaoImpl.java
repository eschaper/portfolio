
package com.aegis.cms.dao;

import com.aegis.cms.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;


public class UserDaoImpl implements UserDao {

    private static final String SQL_INSERT_USER
            = "insert into users (publicname, username, authority, password) values (?, ?, ?, ?)";


    private static final String SQL_DELETE_USER
            = "delete from users where user_id = ?";
    
    private static final String SQL_UPDATE_USER
            ="update users set publicname = ?, username = ?, authority = ?, password = ? where user_id = ?";


    private static final String SQL_SELECT_ALLUSERS
            = "select * from users";
    
    private static final String SQL_SELECT_USER
                ="select * from users where user_id = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addUser(User newUser) {
        jdbcTemplate.update(SQL_INSERT_USER, 
                newUser.getPublicName(), 
                newUser.getUserName(), 
                newUser.getAuthority(), 
                newUser.getPassword());
        newUser.setUserId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",Integer.class));
        
    }
    
    public User getUserById(int id) {
        try{
            return jdbcTemplate.queryForObject(SQL_SELECT_USER, new UserMapper(), id);
        }
        catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void deleteUser(int id) {
        jdbcTemplate.update(SQL_DELETE_USER, id);
    
    }
    
    
    public User editUser(User user) {
        jdbcTemplate.update(SQL_UPDATE_USER, user.getPublicName(), user.getUserName(), user.getAuthority(), user.getPassword(), user.getUserId());
   return user;
    } 

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(SQL_SELECT_ALLUSERS, new UserMapper());

    }


    private static final class UserMapper implements ParameterizedRowMapper<User> {

        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("user_id"));
            user.setUserName(rs.getString("username"));
            user.setPublicName(rs.getString("publicname"));
            user.setPassword(rs.getString("password"));
            user.setAuthority(rs.getString("authority"));
            return user;
        }
    }

}
