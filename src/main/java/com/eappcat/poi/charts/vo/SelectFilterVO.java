package com.eappcat.poi.charts.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class SelectFilterVO {
    private String level1;
    private String level2="房产交易";
    private String groupBy="level3";
    private boolean total=true;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date endDate;
    private boolean lastYear;
}
