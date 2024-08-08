package com.programme.ProgramMe.controller.dto;

import com.programme.ProgramMe.model.Programmer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgrammerDTO {

    private Long id;
    private String name;
    private String email;
    private String number;
    private String skills;
    private String description;
    private List<PostDTO> posts;

    public ProgrammerDTO(Programmer programmer) {
        this.id = programmer.getId();
        this.name = programmer.getName();
        this.email = programmer.getEmail();
        this.number = programmer.getNumber();
        this.skills = programmer.getSkills();
        this.description = programmer.getDescription();
        this.posts = programmer.getPosts().stream()
                .map(PostDTO::new)
                .collect(Collectors.toList());
    }
}