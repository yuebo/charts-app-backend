package com.eappcat.poi.charts.dao;

import com.eappcat.poi.charts.entity.CaseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseDao extends CrudRepository<CaseEntity,Long> {
}
