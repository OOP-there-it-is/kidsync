package com.codeup.kidsync.services;


import com.codeup.kidsync.models.Class;
import com.codeup.kidsync.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassSvc {

    private ClassRepository classDoa;

    @Autowired
    public ClassSvc(ClassRepository classDoa) {
        this.classDoa = classDoa;
    }

    public Class save(Class classroom) {
        classDoa.save(classroom);
        return classroom;
    }

}
