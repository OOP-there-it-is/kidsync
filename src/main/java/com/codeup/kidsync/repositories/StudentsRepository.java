package com.codeup.kidsync.repositories;

import com.codeup.kidsync.models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentsRepository extends CrudRepository<Student, Long> {
        List<Student> findAllByUserId(long id);
}