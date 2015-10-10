package com.stockmarket.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.stockmarket.dao.ICompanyDao;
import com.stockmarket.dao.MySessionFactory;
import com.stockmarket.model.Company;

public class CompanyDao implements ICompanyDao {
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
	
}
