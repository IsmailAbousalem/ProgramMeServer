package com.programme.ProgramMe.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Programmer extends Customer {
    private String skills;
    private String description;

    @OneToMany(mappedBy = "programmer", cascade = CascadeType.REMOVE)
    private List<Post> posts;
}

