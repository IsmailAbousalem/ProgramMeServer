package com.programme.ProgramMe.controller.dto;

import com.programme.ProgramMe.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private Date date;
    private Long programmerId;
    private String programmerName;
    private String programmerDescription;

    public PostDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.price = post.getPrice();
        this.date = post.getDate();
        this.programmerId = post.getProgrammer().getId();
        this.programmerName = post.getProgrammer().getName();
        this.programmerDescription = post.getProgrammer().getDescription();
    }

    // Getters and setters
}
