package com.rhc.ddd;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//import com.rhc.ddd.domain.AccountService;

public class AtmApplication {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		AccountService as = ac.getBean("accountService", AccountService.class);
//		System.out.println(as.getBalance("1000"));
	}

}
