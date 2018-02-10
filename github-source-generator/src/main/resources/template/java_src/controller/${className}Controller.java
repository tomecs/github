package com.feng.github.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
/**
 * @author rapidAutoCreate
 * @createTime <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 */
@RestController
@RequestMapping("/${clazz.className?uncap_first}")
public class ${className}Controller{

	private static Logger logger = LoggerFactory.getLogger(${className}Controller.class);
	@Autowired
	private I${className}Service ${className?uncap_first}Service;

	/**
	 * 获取数据分页
	 *
	 * @param request
	 * @param response
	 * @return Result<DataStore<${className}DTO>>
	 * @throws Exception
	 */
	@RequestMapping(value = "pageList", method = RequestMethod.GET)
	@ResponseBody
	public Result<DataStore<${className}DTO>> pageList(HttpServletRequest request, HttpServletResponse response)  {
		try{
			// 过滤条件
			Specification<${className}> specification = new Specification<${className}>() {
				@Override
				public Predicate toPredicate(Root<${className}> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> predicateList = Lists.newArrayList();
					predicateList.add(SpecificationUtil.buildFromHttpRequest(request, root, cb));
					return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
				}
			};
			// 默认排序
			Sort.Order defOrder = new Sort.Order(Sort.Direction.DESC, "lastChangeTime");
			Sort sort = SpringmvcUtil.getSort(request, defOrder);
			// 得到分页
			Pageable pageable = SpringmvcUtil.getPageable(request, sort);
			Page<${className}> pageResult = ${className?uncap_first}Service.findPage(specification, pageable);
			DataStore<${className}DTO> ds = null;
			List<${className}DTO> list = Lists.newArrayList();
			if (pageResult != null) {
				for (${className} ${className?uncap_first} : pageResult.getContent()) {
					list.add(new ${className}DTO().transfer(${className?uncap_first}));
				}
				ds = new DataStore<${className}DTO>(pageResult.getTotalElements(), list);
			} else {
				ds = new DataStore<${className}DTO>();
			}
			return Result.newSuccess(ds);
		}catch (Exception e){
			logger.error(MessageConstant.QUERYFAILED, e);
			return  Result.newFaild(MessageConstant.QUERYFAILED);
		}
	}

	/**
	 * 获取数据列表
	 *
	 * @param request
	 * @param response
	 * @return Result<List<${className}DTO>>
	 * @throws Exception
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<${className}DTO>> list(HttpServletRequest request, HttpServletResponse response)  {
		try{
			// 过滤条件
			Specification<${className}> specification = new Specification<${className}>() {
				@Override
				public Predicate toPredicate(Root<${className}> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> predicateList = Lists.newArrayList();
					predicateList.add(SpecificationUtil.buildFromHttpRequest(request, root, cb));
					return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
				}
			};
			// 默认排序
			Sort.Order defOrder = new Sort.Order(Sort.Direction.DESC, "lastChangeTime");
			Sort sort = SpringmvcUtil.getSort(request, defOrder);
			List<${className}> listResult = ${className?uncap_first}Service.findAll(specification, sort);
			List<${className}DTO> dtoList = Lists.newArrayList();
			if (!CollectionUtils.isEmpty(listResult)) {
				for (${className} ${className?uncap_first} : listResult) {
					dtoList.add(new ${className}DTO().transfer(${className?uncap_first}));
				}
			}
			return Result.newSuccess(dtoList);
		}catch (Exception e){
			logger.error(MessageConstant.QUERYFAILED, e);
			return  Result.newFaild(MessageConstant.QUERYFAILED);
		}
	}

	/**
	 * 保存操作
	 *
	 * @param ${className?uncap_first}
	 * @return Result
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Result save(@RequestBody ${className} ${className?uncap_first}) {
		try {
			${className?uncap_first}Service.save(${className?uncap_first});
			return  Result.newSuccess();
		} catch (Exception e) {
			logger.error(MessageConstant.SAVEFAILED, e);
			return  Result.newFaild(MessageConstant.SAVEFAILED);
		}
	}

	/**
	 * 更新操作
	 *
	 * @param ${className?uncap_first}
	 * @return Result
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public Result update(@RequestBody ${className} ${className?uncap_first}) {
		try {
			${className?uncap_first}Service.update(${className?uncap_first});
			return  Result.newSuccess();
		} catch (Exception e) {
			logger.error(MessageConstant.UPDATEFAILED, e);
			return  Result.newFaild(MessageConstant.UPDATEFAILED);
		}
	}

	/**
	 * 删除操作
	 *
	 * @param bodyStr
	 * @return OperateInfo
	 */
	@RequestMapping(value = "deletes", method = RequestMethod.POST)
	@ResponseBody
	public Result deletes(@RequestBody String bodyStr) {
		if(StringUtils.isBlank(bodyStr)){
			return Result.newFaild(MessageConstant.getMessage(MessageConstant.PARAMSMISS1,"ids"));
		}
		try{
			JSONObject jsonObject = JSONObject.parseObject(bodyStr);
			String[] idArray = jsonObject.getString("ids").split(",");
			List<Long> idList = Lists.newArrayList();
			for(String id:idArray){
				idList.add(Long.valueOf(id));
			}
			List<${className}> ${className?uncap_first}List = ${className?uncap_first}Service.findAll(idList);
			if (!CollectionUtils.isEmpty(${className?uncap_first}List )) {
				${className?uncap_first}Service.deleteInBatch(${className?uncap_first}List);
			}
			return  Result.newSuccess();
		}catch (Exception e){
			logger.error(MessageConstant.DELETEFAILED, e);
			return  Result.newFaild(MessageConstant.DELETEFAILED);
		}
	}

	/**
	 * @return boolean
	 * @throws
	 * @Title: checkForm
	 * @Description:验证重名
	 */
	@RequestMapping(value = "checkForm/{param}", method = RequestMethod.GET)
	@ResponseBody
	public Result checkForm(@PathVariable("param") String param,HttpServletRequest request) {
		String value = SpringmvcUtil.getParameter(param);
		String id = SpringmvcUtil.getParameter("id");
		// 过滤条件
		Specification<${className}> specification = new Specification<${className}>() {
			@Override
			public Predicate toPredicate(Root<${className}> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				///List<Predicate> predicateList = Lists.newArrayList();
				///predicateList.add(SpecificationUtil.buildFromHttpRequest(request, root, cb));
				///return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
				return cb.equal(root.get(param),value);
			}
		};
		List<${className}> list = ${className?uncap_first}Service.findAll(specification);
		if (list == null || list.size() == 0) {
			return Result.newSuccess(true);
		}
		if (StringUtils.isNotBlank(id) && list.size() == 1 && list.get(0).getId().equals(id)) {
			return Result.newSuccess(true);
		}
		return Result.newSuccess(false);
	}
	
}
