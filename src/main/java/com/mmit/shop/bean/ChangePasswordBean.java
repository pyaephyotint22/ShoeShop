package com.mmit.shop.bean;


import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.security.CommonException;
import com.mmit.shop.model.service.UserService;

@Named
@RequestScoped
public class ChangePasswordBean {
	
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	@Inject
	private UserService service;
	@Inject
	private FacesContext cxt;
	public String changePassword() {
		
		try {
			service.changePassword(oldPassword,newPassword,confirmPassword);
			cxt.getExternalContext().invalidateSession();
			return "/login.xhtml?faces-redirect=true";
		} catch (CommonException e) {
			String str=e.getMessage();
			if(str.contains("Incorrect"))
				{
				cxt.addMessage("editForm:oldPass", new FacesMessage(e.getMessage()));
				
				}else if(str.contains("Confirm")) {
					cxt.addMessage("editForm:confirmPass", new FacesMessage(e.getMessage()));
				}
		}
		return null;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
}
