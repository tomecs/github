package com.feng.github.entity.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

<#list clazz.importClasses as importClass>
import ${importClass.javaType};
</#list>

/**
 * @author rapidAutoCreate
 * @createTime <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 */
public class ${clazz.className}DTO {
        private static final Logger logger = LoggerFactory.getLogger(${clazz.className}DTO.class);

        /**
         * id
         */
        private Long id;
    <#list clazz.fields as field>
        private ${field.javaType} ${field.fieldName};
    </#list>
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

    <#list clazz.fields as field>
        public ${field.javaType} get${field.fieldName?cap_first}(){
            return this.${field.fieldName};
        }
        public void  set${field.fieldName?cap_first}(${field.javaType} ${field.fieldName}){
            this.${field.fieldName} = ${field.fieldName};
        }
    </#list>

        public ${clazz.className}DTO transfer(${clazz.className} ${className?uncap_first}) {
            try{
                BeanUtils.copyProperties(${className?uncap_first}, this);
            }catch (Exception e){
                logger.error("转换错误",e);
            }
            return this;
        }

}

