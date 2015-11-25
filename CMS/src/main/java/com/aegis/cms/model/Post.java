package com.aegis.cms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Post implements Serializable {

    private int postId;

    @NotEmpty(message = "You must enter some text into the title field.")
    @Length(max = 50, message = "Title must be no more than 50 characters in length.")
    private String title;

    @NotEmpty(message = "You must enter some text into the body field.")
    @Length(max = 10000, message = "Title must be no more than 10,000 characters in length.")
    private String body;
    private String author;
    private Set<Tag> tags = new HashSet();

    @NotNull(message = "You must enter a date for this post.")
    private Date postDate;
    private Date expiration;
    private boolean isPublished;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTagsFromDb(Set<Tag> tagSet) {
        tags = tagSet;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(String tagString) {
        if (!tagString.equals("") || tagString != null) {
            Set<Tag> tagSet = new HashSet<>();
            String[] tempArray = tagString.split(",");
            for (int i = 0; i < tempArray.length; i++) {
                Tag tempTag = new Tag();
                tempTag.setTagName(tempArray[i].trim());
                tagSet.add(tempTag);
            }
            this.tags.addAll(tagSet);
            tags = tagSet;
        } else {
            tags = null;
        }
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.postId;
        hash = 47 * hash + Objects.hashCode(this.title);
        hash = 47 * hash + Objects.hashCode(this.body);
        hash = 47 * hash + Objects.hashCode(this.author);
        hash = 47 * hash + Objects.hashCode(this.tags);
        hash = 47 * hash + Objects.hashCode(this.postDate);
        hash = 47 * hash + Objects.hashCode(this.expiration);
        hash = 47 * hash + (this.isPublished ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Post other = (Post) obj;
        if (this.postId != other.postId) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.body, other.body)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.tags, other.tags)) {
            return false;
        }
        if (!Objects.equals(this.postDate, other.postDate)) {
            return false;
        }
        if (!Objects.equals(this.expiration, other.expiration)) {
            return false;
        }
        if (this.isPublished != other.isPublished) {
            return false;
        }
        return true;
    }

}
