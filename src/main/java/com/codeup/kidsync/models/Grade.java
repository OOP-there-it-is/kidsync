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
    private String rating;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String notes;
//    @Column(nullable = false)
//    private Date date;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public void setStudent(Student student) {
        this.student = student;
    }

    public Grade() {
    }

    public Grade(long id, String rating, String notes, Date date, Student student) {
        this.id = id;
        this.rating = rating;
        this.notes = notes;
        this.student = student;
//        this.date = date;
    }


    public void setDate(Date date) {
//        this.date = date;
    }
}
