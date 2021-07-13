package com.mmit.shops;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.mmit.shop.model.entity.Users1;
import com.mmit.shop.model.entity.Users1.Role;
import com.mmit.shop.model.service.UserService;


@ApplicationScoped
@Singleton
@Startup
public class AdminCreation {
	@Inject
	private UserService service;
	@PostConstruct
	private void init() {
		long userCount=service.getCount();
		if(userCount == 0) {
		Users1 admin=new Users1();
		admin.setLoginId("ywk@gmail.com");
		admin.setPassword("12345678");
		admin.setRole(Role.Admin);
		admin.setUserName("ywk");
		service.save(admin);
		}
	}
}
