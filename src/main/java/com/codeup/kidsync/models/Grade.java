package com.codeup.kidsync.models;

import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "grades")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    public String rating;
    @Column(columnDefinition = "TEXT")
    private String notes;
    @Column(nullable = false)
    private String quarter;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public void setStudent(Student student) {
        this.student = student;
    }

    public Grade() {
    }

    public Grade(long id) {
        this.id = id;
    }

    public Grade(long id, String rating, String notes, String quarter, Student student) {
        this.id = id;
        this.rating = rating;
        this.notes = notes;
        this.student = student;
        this.quarter = quarter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Student getStudent() {
        return student;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getDate() {
        return quarter;
    }

    public void setDate(String Quarter) {
        this.quarter = quarter;
    }
}
