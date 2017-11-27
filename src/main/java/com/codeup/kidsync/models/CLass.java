package com.codeup.kidsync.models;

import javax.persistence.*;

@Entity
@Table(name="class")
public class CLass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    public String teacher;
    @Column(nullable= false, name="student_id")
    private long studentId;

}
