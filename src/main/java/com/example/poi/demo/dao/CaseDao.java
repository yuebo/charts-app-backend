package com.example.poi.demo.dao;

import com.example.poi.demo.entity.CaseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseDao extends CrudRepository<CaseEntity,Long> {
}
