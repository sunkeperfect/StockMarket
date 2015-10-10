package com.stockmarket.dao;

import java.util.List;

import com.stockmarket.model.Company;

public interface ICompanyDao {
	/**
	 * 添加公司
	 * @param company
	 */
	public boolean add(Company company);
	/**
	 * 
	 * @return
	 */
	public List<Company> getLikeCompany();
	/**
	 * 该公司是否已经存在
	 */
	public boolean isExistCompany(String numberCode,String stockCode);
}
