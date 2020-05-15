package com.gbf.onlineshop.repository;

import com.gbf.onlineshop.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>/*, UserCommonRepository<User, Long>*/ {

    Optional<User> findByLogin(String login);
}
