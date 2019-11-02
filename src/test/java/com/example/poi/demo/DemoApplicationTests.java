package com.example.poi.demo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.example.poi.demo.dao.CaseDao;
import com.example.poi.demo.entity.CaseEntity;
import com.example.poi.demo.excel.CaseDataListener;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
@Slf4j
class DemoApplicationTests {
    @Autowired
    private CaseDao caseDao;

    @Autowired
    private CaseDataListener listener;

    @Test
    void contextLoads() {
        log.info("{}",caseDao);
        String root="/Users/yuebo/Downloads/历史工单备份/2018";
        File[] files=new File(root).listFiles();
        for (File file: files){
            log.info("{}",file.getAbsolutePath());
            ExcelReader excelReader=EasyExcel.read(file, CaseEntity.class,listener).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
            excelReader.finish();
        }
    }

}
