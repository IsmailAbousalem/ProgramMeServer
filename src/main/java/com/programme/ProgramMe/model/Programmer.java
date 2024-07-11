package com.programme.ProgramMe.model;

import jakarta.persistence.Entity;

@Entity
public class Programmer extends Customer {
    private String skills;
    private String description;


    public Programmer() {
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
