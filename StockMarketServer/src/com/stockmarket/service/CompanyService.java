package com.stockmarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.dao.ICompanyDao;
import com.stockmarket.model.Company;

@Service("companyService")
public class CompanyService {
	@Autowired
	ICompanyDao companyDao;

	/**
	 * 获取公司列表
	 */
	public List<Company> getCompanyList() {
		return companyDao.getCompanyList();
	}
}
