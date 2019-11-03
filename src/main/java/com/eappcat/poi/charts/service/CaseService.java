package com.eappcat.poi.charts.service;

import com.eappcat.poi.charts.entity.CaseEntity;
import com.eappcat.poi.charts.vo.CountStatVO;
import com.eappcat.poi.charts.vo.SelectFilterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CaseService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void saveAll(List<CaseEntity> caseEntityList){
        jdbcTemplate.batchUpdate("INSERT INTO `tbl_case` (`activity_type`, `area_no`, `attribute`, `case_no`, `close_date`, `contact`, `contact_no`, `country`, `delay`, `department`, `description`, `due_date`, `end`, `feedback`, `level1`, `level2`, `level3`, `origin`, `phone_no`, `place`, `process_date`, `process_remark`, `received_date`, `reporter_name`, `reporter_no`, `response_dept`, `response_dept3`, `self`, `start`, `status`, `subject`, `suggestion`, `task_no`, `town`, `type`, `urgent`)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",caseEntityList.stream().map(entity->{
                    return new Object[]{entity.getActivityType(),entity.getAreaNo(),entity.getAttribute(),entity.getCaseNo(),entity.getCloseDate(),entity.getContact(),entity.getContactNo(),entity.getCountry(),entity.getDelay(),entity.getDepartment(),entity.getDescription(),entity.getDueDate(),entity.getEnd(),entity.getFeedback(),entity.getLevel1(),entity.getLevel2(),entity.getLevel3(),entity.getOrigin(),
                                        entity.getPhoneNo(),entity.getPlace(),entity.getProcessDate(),entity.getProcessRemark(),entity.getReceivedDate(),entity.getReporterName(),entity.getReporterNo(),entity.getResponseDept(),entity.getResponseDept3(),entity.getSelf(),entity.getStart(),entity.getStatus(),entity.getSubject(),entity.getSuggestion(),entity.getTaskNo(),entity.getTown(), entity.getType(),entity.getUrgent()};
        }).collect(Collectors.toList()));
    }

    public List<CountStatVO> selectData(SelectFilterVO filterVO,String table){
        String groupBy=filterVO.getGroupBy();
        if(!Arrays.asList("activity_type", "area_no", "attribute", "case_no", "close_date", "contact", "contact_no", "country", "delay", "department", "description", "due_date", "end", "feedback", "level1", "level2", "level3", "origin", "phone_no", "place", "process_date", "process_remark", "received_date", "reporter_name", "reporter_no", "response_dept", "response_dept3", "self", "start", "status", "subject", "suggestion", "task_no", "town", "type", "urgent")
                .contains(groupBy)){
            throw new IllegalArgumentException("group by cannot apply for this request");
        }
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("select count(1) cnt,"+filterVO.getGroupBy()+" as col from "+table+" where ");
        List<Object> args=new ArrayList<>();
        if (!StringUtils.isEmpty(filterVO.getLevel1())){
            stringBuilder.append("level1 = ?");
            args.add(filterVO.getLevel1());
        }else {
            stringBuilder.append("level2 = ?");
            args.add(filterVO.getLevel2());
        }
        if (filterVO.getStartDate()!=null){
            stringBuilder.append("and received_date >= ?");
            args.add(filterVO.getStartDate());
        }
        if (filterVO.getEndDate()!=null){
            stringBuilder.append("and received_date <= ?");
            args.add(filterVO.getEndDate());
        }
        stringBuilder.append("group by "+filterVO.getGroupBy());
        return jdbcTemplate.query(stringBuilder.toString(),args.toArray(new Object[]{}),new BeanPropertyRowMapper<>(CountStatVO.class));
    }

    public void truncate(String tbl_case) {
        jdbcTemplate.execute("truncate table "+tbl_case);
    }
}
