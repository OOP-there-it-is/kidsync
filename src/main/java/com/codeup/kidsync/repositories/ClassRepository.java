package com.codeup.kidsync.repositories;

import com.codeup.kidsync.models.Class;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClassRepository extends CrudRepository<Class, Long>{
    List<Class> findClassByUserId(long id);
}
