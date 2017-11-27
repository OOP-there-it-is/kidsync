package com.codeup.kidsync.services;


import com.codeup.kidsync.models.Attendance;
import com.codeup.kidsync.repositories.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceSvc {

    private final AttendanceRepository attendanceDao;

    @Autowired
    public AttendanceSvc(AttendanceRepository attendanceDao) {
        this.attendanceDao = attendanceDao;
    }

    public Attendance save(Attendance attendance) {
        attendanceDao.save(attendance);
        return attendance;
    }

    public List<Attendance> getGradesByStudent(Long studentId){
        return attendanceDao.findAllByStudentId(studentId);
    }
}
