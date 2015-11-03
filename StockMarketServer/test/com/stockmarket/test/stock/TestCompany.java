package com.stockmarket.test.stock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stockmarket.dao.impl.CompanyDao;
import com.stockmarket.model.Company;

@RunWith(SpringJUnit4ClassRunner.class)
@org.springframework.test.context.ContextConfiguration(locations = "file:WebContent/WEB-INF/applicationContext.xml") 
public class TestCompany {
	@Autowired
	CompanyDao companyDao;
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
