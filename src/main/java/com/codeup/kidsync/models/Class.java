package com.codeup.kidsync.models;

import javax.persistence.*;

@Entity
@Table(name="class")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;
    @Column(nullable = false, name = "grade_level")
    public String gradeLevel;
    @Column(name = "name")
    private String name;



    public Class() {
    }

    public Class(User user, String gradeLevel, String name) {
        this.user = user;
        this.gradeLevel = gradeLevel;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
