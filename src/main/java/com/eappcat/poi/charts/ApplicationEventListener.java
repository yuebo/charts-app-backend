package com.eappcat.poi.charts;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.eappcat.poi.charts.entity.CaseEntity;
import com.eappcat.poi.charts.excel.CaseDataListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.InputStream;

@Component
@Slf4j
@EnableSwagger2
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
