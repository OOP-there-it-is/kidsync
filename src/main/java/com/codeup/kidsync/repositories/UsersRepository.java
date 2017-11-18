package com.codeup.kidsync.repositories;

        import com.codeup.kidsync.models.User;
        import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
