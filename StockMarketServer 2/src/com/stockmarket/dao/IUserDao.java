package com.stockmarket.dao;

import com.stockmarket.model.User;

public interface IUserDao {
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public boolean add(User user);
	/**
	 * 通过device 查询user
	 */
	public User getUserByDeviceId (String device_id);
	/**
	 * 通过临时id查询user
	 */
	public User getUserByTempId(String temp_id);
	/**
	 * 通过账号查询user
	 * 
	 */
	public User getUserByUserName(String username);
	/**
	 * 用户是否存在  username or temp_id
	 * true 存在 false 不存在
	 * 
	 */
	public boolean isExistUser(String username,String temp_id);
}
