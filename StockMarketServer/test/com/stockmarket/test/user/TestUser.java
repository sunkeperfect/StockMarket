package com.stockmarket.test.user;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.stockmarket.model.User;

public class TestUser {
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	@Before
	public void init() {
		// 创建配置对象
		Configuration config = new Configuration().configure();
		// 创建服务注册对象
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		// 创建会话工厂对象
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		session = sessionFactory.openSession();
		// 开启事务
		transaction = session.beginTransaction();

	}

	@After
	public void destory() {
		transaction.commit();
		// 提交事务
		session.close();
		// 关闭会话
		sessionFactory.close();
	}

	@Test
	public void testSaveUser() {
		User user = new User(1, "sunke", "123", "75138858@qq.com", "");
		session.save(user);

	}

}
