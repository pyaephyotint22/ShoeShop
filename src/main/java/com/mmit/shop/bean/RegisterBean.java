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
public class RegisterBean implements Serializable{
	private List<Users1> users;
	
	private Users1 user;
	
	@Inject
	private UserService service;
	@Inject
	private FacesContext cxt;
	@Inject
	private LoginBean loginBean;
	private String tmp_msg=null;
	@PostConstruct
	private void init() {
		users=service.findAllExceptMe();
		String uId=cxt.getExternalContext().getRequestParameterMap().get("userId");
		if(uId !=null) {
			user=service.findById(Integer.parseInt(uId));
			tmp_msg="edit";
		}else
		{
		user=new Users1();
		user.setRole(Role.Customer);
		}
	}
	public String reg_save() {
		try {
			
			service.save(user);
			if(loginBean.getLoginUser().getId() !=0 && tmp_msg !=null)
				loginBean.setLoginUser(user);
			return "/customer_view.xhtml?faces-redirect=true";
		} catch (EJBException e) {
			FacesContext cxt=FacesContext.getCurrentInstance();
			cxt.addMessage("editForm:id", new FacesMessage("Log in ID already exists!"));
		}return null;
	}
	public List<Users1> getUsers() {
		return users;
	}
	public void setUsers(List<Users1> users) {
		this.users = users;
	}
	public Users1 getUser() {
		return user;
	}
	public void setUser(Users1 user) {
		this.user = user;
	}
	public UserService getService() {
		return service;
	}
	public void setService(UserService service) {
		this.service = service;
	}
	public FacesContext getCxt() {
		return cxt;
	}
	public void setCxt(FacesContext cxt) {
		this.cxt = cxt;
	}
	public String getTmp_msg() {
		return tmp_msg;
	}
	public void setTmp_msg(String tmp_msg) {
		this.tmp_msg = tmp_msg;
	}
	
	
	
	
}
