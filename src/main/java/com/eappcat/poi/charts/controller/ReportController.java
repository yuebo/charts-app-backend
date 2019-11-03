package com.eappcat.poi.charts.controller;

import com.eappcat.poi.charts.service.CaseService;
import com.eappcat.poi.charts.vo.ChartResponse;
import com.eappcat.poi.charts.vo.CountStatVO;
import com.eappcat.poi.charts.vo.SelectFilterVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class ReportController {
    @Autowired
    private CaseService caseService;

    @GetMapping("/charts")
    @ApiOperation("查询数据统计")
    public ChartResponse requestData(SelectFilterVO selectFilterVO){
        ChartResponse chartResponse=new ChartResponse();
        List<CountStatVO> countStatVOList = caseService.selectData(selectFilterVO,"tbl_case");
        Map<String,CountStatVO> cache=new HashMap<>();
        countStatVOList.stream().forEach(data->cache.put(data.getCol(),data));
        if (selectFilterVO.isLastYear()){
            List<CountStatVO> result2 = caseService.selectData(selectFilterVO,"tbl_case_2018");

            result2.forEach(data->{
                CountStatVO countStatVO=cache.get(data.getCol());
                if(countStatVO==null){
                    CountStatVO statVO=new CountStatVO();
                    statVO.setCol(data.getCol());
                    statVO.setCnt2(data.getCnt());
                    cache.put(data.getCol(),statVO);
                }else {
                    countStatVO.setCnt2(data.getCnt());
                }
            });

        }
        countStatVOList=cache.values().stream().collect(Collectors.toList());
        chartResponse.setAxis(countStatVOList.stream().map(CountStatVO::getCol).collect(Collectors.toList()));
        chartResponse.setData(countStatVOList.stream().map(CountStatVO::getCnt).collect(Collectors.toList()));
        chartResponse.setData2(countStatVOList.stream().map(CountStatVO::getCnt2).collect(Collectors.toList()));

        return chartResponse;
    }

    @Autowired
    private ApplicationContext applicationContext;
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        applicationContext.publishEvent(file.getInputStream());
        return ResponseEntity.ok("ok");
    }
    @DeleteMapping("/clear")
    @ApiOperation("清除数据库")
    public ResponseEntity<String> clear(){
        caseService.truncate("tbl_case");
//        caseService.truncate("tbl_case_2018");
        return ResponseEntity.ok("ok");
    }
}
