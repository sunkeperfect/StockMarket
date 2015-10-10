package com.stockmarket.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.stockmarket.dao.IUserDao;
import com.stockmarket.dao.MySessionFactory;
import com.stockmarket.model.User;

@Repository("userDao")
public class UserDao  implements IUserDao{
	Session session=MySessionFactory.getSession();
	/**
	 * 注册
	 */
	public boolean add(User user){
		//如果用户账号或者临时账号在数据库已存在则无法创建
		if(!isExistUser(user.getUsername(),user.getTemp_id())){
		Transaction transaction=session.beginTransaction();
		int i=(Integer) session.save(user);
		System.out.println("add user i:"+i);
		transaction.commit();
		return i>0;
		}
		return false;
	}
	/**
	 * 
	 */
	
	/**
	 * 通过device 查询user
	 */
	public User getUserByDeviceId (String device_id){
			User user=(User)session.createQuery(" from User  where device_id=?").setParameter(0,device_id).uniqueResult();
			return user;
	}
	/**
	 * 通过临时id查询user
	 */
	public User getUserByTempId(String temp_id){
		User user=(User)session.createSQLQuery(" from User where temp_id=?")
				.setParameter(0, temp_id).uniqueResult();
		return user;
	}
	/**
	 * 通过账号查询user
	 * 
	 */
	public User getUserByUserName(String username){
		User user=(User)session.createSQLQuery(" from User where username=?")
				.setParameter(0, username).uniqueResult();
		return user;	}
	/**
	 * 用户是否存在  username or temp_id
	 * true 存在 false 不存在
	 * 
	 */
	public boolean isExistUser(String username,String temp_id){
		long count=Long.parseLong(session.createSQLQuery(" select count(*) from User where username=? or temp_id=?	")
				.setParameter(0, username).setParameter(1,temp_id).uniqueResult().toString());
		//r
		return !(count==0);
	}
	
	
	
}
