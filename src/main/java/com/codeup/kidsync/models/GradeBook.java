package com.codeup.kidsync.models;

import javax.persistence.*;

@Entity
@Table(name = "gradebook")
public class GradeBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private int rating;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String notes;
    @Column(nullable = false)
    private long student_id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public void setStudent(Student student) {
        this.student = student;
    }


}
