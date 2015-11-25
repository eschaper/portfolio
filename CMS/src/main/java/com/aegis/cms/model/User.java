package com.aegis.cms.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


public class User {
    
    private int userId;
    
    @NotEmpty(message="You must enter a username into the title field.")
    @Length(max=20, message="Title must be no more than 20 characters in length.")
    private String userName;
    
    @NotEmpty(message="You must enter a password.")
    @Length(max=20, min=6, message="Passwords must be between 6 and 20 characters.")
    private String password;
    
    @NotEmpty(message="You must enter a name to display on the blog.")
    @Length(max=20, message="Display names must be no more than 20 characters in length.")
    private String publicName;
    private String authority;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
