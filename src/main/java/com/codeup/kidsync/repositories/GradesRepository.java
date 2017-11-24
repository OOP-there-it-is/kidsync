package com.codeup.kidsync.repositories;

import com.codeup.kidsync.models.Grade;
import org.springframework.data.repository.CrudRepository;

public interface GradesRepository extends CrudRepository<Grade, Long> {

}
