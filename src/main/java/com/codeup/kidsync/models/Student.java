package com.codeup.kidsync.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String f_name;
    @Column(nullable = false)
    private String l_name;
    @Column
    private String special_needs;
    @Column(nullable = false)
    private Date dob;


    @OneToMany
    @JoinColumn(name = "user_Id")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public Student(String f_name, String l_name, String special_needs) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.special_needs = special_needs;
    }


}
