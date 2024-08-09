package com.programme.ProgramMe.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
    @Column(columnDefinition = "TEXT")  // Or @Column(length = 65535) for VARCHAR
    private String skills;

    @Column(columnDefinition = "TEXT")  // Or @Column(length = 65535) for VARCHAR
    private String description;

    @OneToMany(mappedBy = "programmer", cascade = CascadeType.REMOVE)
    private List<Post> posts;
}

