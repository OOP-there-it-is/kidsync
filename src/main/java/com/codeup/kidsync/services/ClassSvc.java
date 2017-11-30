package com.codeup.kidsync.services;


import com.codeup.kidsync.models.ClassRoom;

import com.codeup.kidsync.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassSvc {

    private ClassRepository classDoa;

    @Autowired
    public ClassSvc(ClassRepository classDoa) {
        this.classDoa = classDoa;
    }

    public Iterable<ClassRoom> findAll() {  //returns ALL students
        return classDoa.findAll();
    }

    public ClassRoom save(ClassRoom classroom) {
        classDoa.save(classroom);
        return classroom;
    }

    public List<ClassRoom> findClassByTeacher(Long userId){
        return classDoa.findAllByUserId(userId);
    }

    public List<ClassRoom> findClassById(Long classId){
        return classDoa.findAllById(classId);
    }

}
