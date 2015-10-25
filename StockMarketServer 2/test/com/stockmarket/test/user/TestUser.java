package com.stockmarket.test.user;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.stockmarket.dao.impl.UserDao;
import com.stockmarket.model.User;

public class TestUser {
	UserDao userDao=new UserDao();

	@Before
	public void init() {
		// 创建配置对象
//		Configuration config = new Configuration().configure();
//		// 创建服务注册对象
//		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//				.applySettings(config.getProperties()).build();
//		// 创建会话工厂对象
//		sessionFactory = config.buildSessionFactory(serviceRegistry);
//		session = sessionFactory.openSession();
//		// 开启事务
//		transaction = session.beginTransaction();

	}

	@After
	public void destory() {
	//	transaction.commit();
		// 提交事务
	//	session.close();
		// 关闭会话
	//	sessionFactory.close();
	}

	@Test
	public void testSaveUser() {
		userDao.add(new User(0,"sunke2","1234","75138858@qq.com","","d3","d3"));
	}
	@Test
	public void testGetUserByDeviceId(){
		User user=userDao.getUserByDeviceId("d3");
		assertNotNull(user);
		System.out.println(" testGetUserByDeviceId user:"+user.toString());
	}

}
