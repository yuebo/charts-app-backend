package com.example.poi.demo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.example.poi.demo.dao.CaseDao;
import com.example.poi.demo.entity.CaseEntity;
import com.example.poi.demo.excel.CaseDataListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;

@Component
@Slf4j
public class ApplicationEventListener {
    @Autowired
    private ApplicationContext applicationContext;

    @EventListener
    @Async
    public void processFile(InputStream inputStream){
        log.info("start to process");
        try{
            ExcelReader excelReader= EasyExcel.read(inputStream, CaseEntity.class,applicationContext.getBean(CaseDataListener.class)).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
            excelReader.finish();
        }finally {
            IOUtils.closeQuietly(inputStream);
        }
        log.info("end to process");
    }
}
