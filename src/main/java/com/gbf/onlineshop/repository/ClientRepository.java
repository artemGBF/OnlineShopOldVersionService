package com.gbf.onlineshop.repository;

import com.gbf.onlineshop.model.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ClientRepository extends CrudRepository<Client, Long>/*, UserCommonRepository<Client, Long>*/ {
}
