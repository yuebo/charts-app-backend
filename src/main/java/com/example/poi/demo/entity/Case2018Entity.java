package com.example.poi.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "tbl_case_2018")
@Entity
@Data
public class Case2018Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ExcelProperty("案件状态")
    private String status;
    @ExcelProperty("任务号")
    private String taskNo;
    @ExcelProperty("案卷编号")
    private String caseNo;
    @ExcelProperty("发现时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm")
    private Date start;
    @ExcelProperty("受理时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm")
    private Date end;
    @ExcelProperty("流转方向")
    private String activityType;
    @ExcelProperty("业务类型")
    private String type;
    @ExcelProperty("案件来源")
    private String origin;
    @ExcelProperty("案件属性")
    private String attribute;
    @ExcelProperty("案件大类")
    private String level1;
    @ExcelProperty("案件小类")
    private String level2;
    @ExcelProperty("案件子类")
    private String level3;
    @ExcelProperty("街镇")
    private String town;
    @ExcelProperty("居村")
    private String country;
    @ExcelProperty("网格编号")
    private String areaNo;
    @ExcelProperty("发生地址")
    private String place;
    @ExcelProperty("上报部门")
    private String department;
    @ExcelProperty("监督员编号")
    private String reporterNo;
    @ExcelProperty("监督员姓名")
    private String reporterName;
    @ExcelProperty("诉求联系人")
    private String contact;
    @ExcelProperty("来电号码")
    private String phoneNo;
    @ExcelProperty("联系电话")
    private String contactNo;
    @ExcelProperty("诉求标题")
    private String subject;
    @ExcelProperty("问题描述")
    @Column(length = 2000)
    private String description;
    @ExcelProperty("是否紧急")
    private String urgent;
    @ExcelProperty("是否网格自治")
    private String self;
    @ExcelProperty("派遣时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm")
    private Date receivedDate;
    @ExcelProperty("主责部门")
    private String responseDept;
    @ExcelProperty("三级主责部门")
    private String responseDept3;
    @ExcelProperty("处理时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm")
    private Date processDate;
    @ExcelProperty("处理超时")
    private String delay;
    @ExcelProperty("处理截至时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm")
    private Date dueDate;
    @ExcelProperty("处理备注")
    @Column(length = 2000)
    private String processRemark;
    @ExcelProperty("回访用户评价")
    private String feedback;
    @ExcelProperty("结案时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm")
    private Date closeDate;
    @ExcelProperty("结案意见")
    @Column(length = 2000)
    private String suggestion;

}
