package com.codeup.kidsync.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String f_name;
    @Column(nullable = false)
    private String l_name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private int role;
    @Column
    private String phone;


//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<Student> students;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<Post> posts;

    public User() {

    }

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        f_name = copy.f_name;
        l_name = copy.l_name;
        password = copy.password;
        phone = copy.phone;
    }

    public User(String f_name, String l_name, String email, String password, int role, String phone) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

}