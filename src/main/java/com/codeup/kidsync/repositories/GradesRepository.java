package com.codeup.kidsync.repositories;

import com.codeup.kidsync.models.Grade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GradesRepository extends CrudRepository<Grade, Long> {
    List<Grade> findAllByStudentId(long id);
}
