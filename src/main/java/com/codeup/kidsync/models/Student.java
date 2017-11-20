//package com.codeup.kidsync.models;
//
//import javax.persistence.*;
//import java.sql.Date;
//
//@Entity
//@Table(name = "students")
//public class Student {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//    @Column(nullable = false)
//    private String f_name;
//    @Column(nullable = false)
//    private String l_name;
//    @Column
//    private String special_needs;
//    @Column(nullable = false)
//    private Date dob;
//
//
////    @OneToMany
////    @JoinColumn(name = "user_Id")
//    private User user;
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Student(String f_name, String l_name, String special_needs) {
//        this.f_name = f_name;
//        this.l_name = l_name;
//        this.special_needs = special_needs;
//    }
//
//    public Student(String f_name, String l_name, String special_needs, Date dob, User user) {
//        this.f_name = f_name;
//        this.l_name = l_name;
//        this.special_needs = special_needs;
//        this.dob = dob;
//        this.user = user;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getF_name() {
//        return f_name;
//    }
//
//    public void setF_name(String f_name) {
//        this.f_name = f_name;
//    }
//
//    public String getL_name() {
//        return l_name;
//    }
//
//    public void setL_name(String l_name) {
//        this.l_name = l_name;
//    }
//
//    public String getSpecial_needs() {
//        return special_needs;
//    }
//
//    public void setSpecial_needs(String special_needs) {
//        this.special_needs = special_needs;
//    }
//
//    public Date getDob() {
//        return dob;
//    }
//
//    public void setDob(Date dob) {
//        this.dob = dob;
//    }
//
//    public User getUser() {
//        return user;
//    }
//}
