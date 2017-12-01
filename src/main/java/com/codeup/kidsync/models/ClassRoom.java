package com.codeup.kidsync.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="class")
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(nullable =false, name= "user_id")
    public User user;
    @Column(nullable = false, name = "grade_level")
    public String gradeLevel;
    @Column(nullable =false, name = "class_name")
    private String className;


    public ClassRoom() {
    }

    public ClassRoom(User user, String gradeLevel, String name) {
        this.user = user;
        this.gradeLevel = gradeLevel;
        this.className = name;

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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
