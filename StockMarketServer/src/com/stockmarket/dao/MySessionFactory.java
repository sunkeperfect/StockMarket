package com.stockmarket.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
//import org.hibernate.boot.registry.StandardServiceRegistry;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class MySessionFactory {

    private static SessionFactory sessionFactory=null;
	private static Session session;

    private MySessionFactory(){
    }
     
    static{
         
    	// 创建配置对象
    			// 创建服务注册对象
//    			StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//    					.applySettings(config.getProperties()).build();
    			// 创建会话工厂对象
    			sessionFactory =  new Configuration().configure().buildSessionFactory();
    			session=sessionFactory.openSession();
    		
    }
     
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    public static Session getSession(){
    	return session;
    }
}
