package com.programme.ProgramMe.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Double price;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "programmer_id")
    private Programmer programmer;


    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Programmer getProgrammer() {
        return programmer;
    }

    public void setProgrammer(Programmer programmer) {
        this.programmer = programmer;
    }
    // Getters and setters
}
