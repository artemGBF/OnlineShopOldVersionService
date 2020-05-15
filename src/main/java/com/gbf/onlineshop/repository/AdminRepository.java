package com.gbf.onlineshop.repository;

import com.gbf.onlineshop.model.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Long>/*, UserCommonRepository<Admin, Long>*/ {
}
