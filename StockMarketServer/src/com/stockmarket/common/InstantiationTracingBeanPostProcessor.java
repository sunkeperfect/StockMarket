package com.stockmarket.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.stockmarket.market.Market;
@Component
@Scope("singleton")
public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	Market market;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		if (event.getApplicationContext().getParent() == null) {
			System.out.println("execute  onAppliactionEvent !!");
			System.out.println("----------------"+market.toString());
			market.init();
		}
	}

}
