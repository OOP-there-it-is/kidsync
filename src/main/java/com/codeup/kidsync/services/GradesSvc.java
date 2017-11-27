package com.codeup.kidsync.services;


import com.codeup.kidsync.models.Grade;
import com.codeup.kidsync.models.Student;
import com.codeup.kidsync.repositories.GradesRepository;
import com.codeup.kidsync.repositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service //Step 1 Annotate class- Spring Boot can't create object
public class GradesSvc {

    private final GradesRepository gradesDoa;


    @Autowired
    public GradesSvc(GradesRepository gradesDoa) {
        this.gradesDoa = gradesDoa;
    }

    public Iterable<Grade> findAll() {  //returns ALL grades
        return gradesDoa.findAll();
    }


    public List<Grade> getGradesByStudent(Long studentId){
      return gradesDoa.findAllByStudentId(studentId);
    }



    public Grade save(Grade grade) {
        gradesDoa.save(grade);
        return grade;
    }

    public Grade findOne(long id) {
        return gradesDoa.findOne(id);
    }

//        public void deletePost(long id) {
//            studentsDoa.delete(id);
//        }


}
