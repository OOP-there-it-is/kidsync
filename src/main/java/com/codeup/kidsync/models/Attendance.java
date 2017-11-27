package com.codeup.kidsync.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @Column
    private String status;
    @Column(nullable = false)
    private Date date;

    public Attendance(){

    }

    public Attendance(long id, Student student, String status, Date date) {
        this.id = id;
        this.student = student;
        this.status = status;
        this.date = date;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
