package com.stockmarket.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.stockmarket.dao.ICompanyDao;
import com.stockmarket.dao.MySessionFactory;
import com.stockmarket.model.Company;
@Repository("companyDao")
public class CompanyDao implements ICompanyDao {
	public CompanyDao() {
		// TODO Auto-generated constructor stub
	}
	Session session=MySessionFactory.getSession();
	@Override
	public boolean add(Company company) {
		// TODO Auto-generated method stub
		//if(isExistCompany(company.getNumberCode(), company.getStockCode()))
		{
			Transaction transaction=session.beginTransaction();
			int i=(Integer) session.save(company);
			System.out.println("add company i:"+i);
			transaction.commit();
			return i>0;
		}
		//return false;
		
	}

	@Override
	public List<Company> getLikeCompany() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isExistCompany(String numberCode, String stockCode) {
		// TODO Auto-generated method stub
		long count=Long.parseLong(session.createSQLQuery(" select count(*) from Company where numbercode=? or stockcode=?")
				.setParameter(0, numberCode).setParameter(1,stockCode).uniqueResult().toString());
		return !(count==0);
	}

	@Override
	public List<Company> getCompanyList() {
		List<Company> list=null;
		list=session.createSQLQuery("select * from Company").addEntity(Company.class).list();
		return list;
	}
	
}
