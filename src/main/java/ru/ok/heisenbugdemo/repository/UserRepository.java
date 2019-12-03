package ru.ok.heisenbugdemo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ok.heisenbugdemo.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
