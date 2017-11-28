package com.codeup.kidsync.repositories;

import com.codeup.kidsync.models.Attendance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttendanceRepository extends CrudRepository<Attendance, Long> {
    List<Attendance> findAllByStudentId(long id);
}
