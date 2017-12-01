package com.codeup.kidsync.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name ="health_log")
public class HealthLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, columnDefinition = "TEXT")
    public String symptoms;
    @Column(columnDefinition = "TEXT")
    private String notes;
    @Column(nullable = false)
    private Date date;
    @ManyToOne
    @JoinColumn(nullable = false, name = "student_id")
    private Student student;

    public HealthLog(){
    }

    public HealthLog(String symptoms, String notes, Date date, Student student) {
        this.symptoms = symptoms;
        this.notes = notes;
        this.date = date;
        this.student = student;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
