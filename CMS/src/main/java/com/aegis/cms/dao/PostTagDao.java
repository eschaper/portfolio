package com.aegis.cms.dao;

import com.aegis.cms.model.Post;
import com.aegis.cms.model.Tag;
import java.util.List;

public interface PostTagDao {

    // Create Post  -- addPost returns post for purposes of testing
    public Post addPost(Post post);
    
    public String getAuthorFromUserName(String username);

    // Home Page
    public List<Post> getAllVisiblePosts();

    public List<Tag> getAllTags();

    public List<Post> getAllPostsByTag(int id);

    // Post Manager
    public Post getPostById(int id);

    public List<Post> getAllPosts();
    
    public List<Post> getAllExpired();
    
    public List<Post> getAllUnpublished();
    
    public void editPost(Post post);
    
    public void publishPost(int id);
    
    public void unpublishPost(int id);
    
    public void deletePostTagById(int id);

}
