package com.codeup.kidsync.models;

import javax.persistence.*;

@Entity
@Table(name = "grades")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private int rating;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String notes;




    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public void setStudent(Student student) {
        this.student = student;
    }

    public Grade() {
    }

    public Grade(long id, int rating, String notes, Student student) {
        this.id = id;
        this.rating = rating;
        this.notes = notes;
        this.student = student;
    }
}
