package com.eappcat.poi.charts.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.eappcat.poi.charts.entity.CaseEntity;
import com.eappcat.poi.charts.service.CaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class CaseDataListener extends AnalysisEventListener<CaseEntity> {
    private List<CaseEntity> list = new ArrayList<CaseEntity>();
    private long count=0;
    @Autowired
    private CaseService caseService;
    @Override
    public void invoke(CaseEntity caseEntity, AnalysisContext analysisContext) {
        list.add(caseEntity);
        if (list.size()>=5000){
            log.info("{}",count);
            saveData();
            list.clear();
        }
        count++;
    }

    public void saveData() {
        caseService.saveAll(list);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
    }
}
