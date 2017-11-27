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
    private int status;
    @Column(name = "date")
    private Date create_date;

    public void setStudent(Student student) {
        this.student = student;
    }



}
