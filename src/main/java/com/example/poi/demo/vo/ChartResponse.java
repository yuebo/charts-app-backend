package com.example.poi.demo.vo;

import lombok.Data;

import java.util.List;

@Data
public class ChartResponse {
    private List<String> axis;
    private List<Integer> data;
    private List<Integer> data2;
}
