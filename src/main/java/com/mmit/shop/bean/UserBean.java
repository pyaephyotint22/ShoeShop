package com.mmit.shop.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.Users1;
import com.mmit.shop.model.entity.Users1.Role;
import com.mmit.shop.model.service.UserService;

@Named
@ViewScoped
public class UserBean implements Serializable{
	private List<Users1> users;
	
	private Users1 user;
	
	@Inject
	private UserService service;
	@Inject
	private FacesContext cxt;
	
	@PostConstruct
	private void init() {
		users=service.findAllExceptMe();
		String uId=cxt.getExternalContext().getRequestParameterMap().get("userId");
		if(uId !=null) {
			user=service.findById(Integer.parseInt(uId));
		}else
		user=new Users1();
	}
	public String save() {
		try {
			
			service.save(user);
			return "/admin/users.xhtml?faces-redirect=true";
		} catch (EJBException e) {
			FacesContext cxt=FacesContext.getCurrentInstance();
			cxt.addMessage("editForm:id", new FacesMessage("Log in ID already exists!"));
		}return null;
	}
	public String reg_save() {
		service.save(user);
		return "/customer_view.xhtml?faces-redirect=true";
	}
	public Users1 getUser() {
		return user;
	}

	public void setUser(Users1 user) {
		this.user = user;
	}
	
	public List<Users1> getUsers() {
		return users;
	}
	public void setUsers(List<Users1> users) {
		this.users = users;
	}
	public UserService getService() {
		return service;
	}
	public void setService(UserService service) {
		this.service = service;
	}
	
	
}
