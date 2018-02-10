package com.feng.github.service.impl;

import com.feng.github.dao.api.I${className}Dao;
import com.feng.github.dao.repository.${className}Repository;
import com.feng.github.entity.model.${className};
import com.feng.github.service.api.I${className}Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author rapidAutoCreate
 * @createTime <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 */
@Service("${className?uncap_first}Service")
public class ${className}ServiceImpl extends PagingAndSortingService<${className},Long> implements I${className}Service {

    private static final Logger logger = LoggerFactory .getLogger(${className}ServiceImpl.class);

    @Autowired
    private ${className}Repository ${className?uncap_first}Respository;
    @Autowired
    private I${className}Dao ${className?uncap_first}Dao;

    @Override
    public BaseRepository getJpaRepository() {
        return ${className?uncap_first}Respository;
    }
}