package com.codeup.kidsync.repositories;

import com.codeup.kidsync.models.HealthLog;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HealthLogRepository extends CrudRepository<HealthLog, Long>{
    List<HealthLog> findAllByStudentId(long id);
}
