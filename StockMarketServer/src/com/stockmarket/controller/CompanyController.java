package com.stockmarket.controller;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.stockmarket.model.Company;
import com.stockmarket.model.JsonResult;
import com.stockmarket.service.CompanyService;

@Controller
public class CompanyController {
	@Autowired
	CompanyService companyService;
	@RequestMapping(value="/company",method=RequestMethod.GET)
	public @ResponseBody Object add(@ModelAttribute Company company){
		JsonResult result=new JsonResult();
		result.setMsg("插入失败!");
		if(company.getCirculateStock()>0
				&&company.getStockTotal()>0
				&&!StringUtils.isEmpty(company.getName())
				&&!StringUtils.isEmpty(company.getStockCode())
				&&!StringUtils.isEmpty(company.getNumberCode())){
			
			if(companyService.add(company)){
				result.setCode(200);
				result.setMsg("成功");
			}
		}
			
		return result;
	}
}
