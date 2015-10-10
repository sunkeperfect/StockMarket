package com.stockmarket.test.stock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.stockmarket.dao.impl.CompanyDao;
import com.stockmarket.model.Company;

public class TestCompany {
	CompanyDao companyDao=new CompanyDao();
	@Before
	public void init(){
		
	}
	@After
	public void destory(){
		
	}
	@Test
	public void testAddCompany(){
		Company company=new Company();
		company.setName("百度");
		company.setNumberCode("300110");
		company.setStockCode("BAIDU");
		company.setStockTotal(100000000);
		company.setCirculateStock(30000000);
		companyDao.add(company);
	}
	
}
