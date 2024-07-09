package com.programme.ProgramMe.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
@DiscriminatorValue("Programmer")
public class Programmer extends User{
    @OneToMany(mappedBy = "author")
    @JsonManagedReference
    private List<Post> posts;

    public Programmer() {
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
