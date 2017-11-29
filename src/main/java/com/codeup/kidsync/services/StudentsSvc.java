package com.codeup.kidsync.services;



import com.codeup.kidsync.models.Student;
import com.codeup.kidsync.repositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //Step 1 Annotate class- Spring Boot can't create object
    public class StudentsSvc {

        private final StudentsRepository studentsDoa;

        @Autowired
        public StudentsSvc(StudentsRepository studentsDoa) {
            this.studentsDoa = studentsDoa;
        }

        public Iterable<Student> findAll() {  //returns ALL students
            return studentsDoa.findAll();
        }

        public Student save(Student student) {
            studentsDoa.save(student);
            return student;
        }

    public List<Student> getStudentsByUserId(Long userId){
        return studentsDoa.findAllByUserId(userId);
    }

    public List<Student> getStudentsByClassId(Long classId){
        return studentsDoa.findAllByClassroomId(classId);
    }


        public Student findOne(long id) {
            return studentsDoa.findOne(id);
        }

    }
