package com.codeup.kidsync.services;

import com.codeup.kidsync.models.HealthLog;
import com.codeup.kidsync.repositories.HealthLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthLogSvc {

    private final HealthLogRepository healthDao;

    @Autowired
    public HealthLogSvc(HealthLogRepository healthDao) {
        this.healthDao = healthDao;
    }

    public Iterable<HealthLog> findAll() {  //returns ALL healthLog
        return healthDao.findAll();
    }


    public List<HealthLog> getHealthLogByStudent(Long studentId){
        return healthDao.findAllByStudentId(studentId);
    }



    public HealthLog save(HealthLog log) {
        healthDao.save(log);
        return log;
    }

    public HealthLog findOne(long id) {
        return healthDao.findOne(id);
    }
}
