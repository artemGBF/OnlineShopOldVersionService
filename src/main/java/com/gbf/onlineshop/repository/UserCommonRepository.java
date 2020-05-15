package com.gbf.onlineshop.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface UserCommonRepository<T, Long> extends CrudRepository<T, Long> {

    @Query("SELECT u FROM User u where u.dtype = T")
    List<T> getUsers();
}
