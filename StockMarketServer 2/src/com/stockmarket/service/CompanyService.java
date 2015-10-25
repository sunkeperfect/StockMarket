package com.stockmarket.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.dao.ICompanyDao;
import com.stockmarket.dao.impl.CompanyDao;
import com.stockmarket.model.Company;

@Service("companyService")
public class CompanyService {
	@Resource(name="companyDao")
	ICompanyDao companyDao;
	public ICompanyDao getCompanyDao() {
		return companyDao;
	}
	public void setCompanyDao(ICompanyDao companyDao) {
		this.companyDao = companyDao;
	}
	/**
	 * 获取公司列表
	 */
	public List<Company> getCompanyList(){
		return companyDao.getCompanyList();
	}
}
