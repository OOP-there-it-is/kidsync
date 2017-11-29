package com.codeup.kidsync.repositories;

import com.codeup.kidsync.models.ClassRoom;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClassRepository extends CrudRepository<ClassRoom, Long>{
    List<ClassRoom> findAllByUserId(long id);

    List<ClassRoom> findAllById(long id);
}
