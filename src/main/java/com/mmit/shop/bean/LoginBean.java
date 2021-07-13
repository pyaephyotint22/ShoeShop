package com.mmit.shop.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import com.mmit.shop.model.entity.Users1;
import com.mmit.shops.AppException;

@Named
@SessionScoped
public class LoginBean implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Require login Id")
	private String loginId;
	
	@NotEmpty(message = "Require password")
	private String password;
	
	@Inject
	private ExternalContext exContent;
	@Inject
	private SecurityContext securityContext;
	
	private Users1 loginUser;
	
	@PostConstruct
	public void init() {
		loginUser=new Users1();
		
	}
	
		public String login() {
			try {
				HttpServletRequest req=(HttpServletRequest) exContent.getRequest();
				HttpServletResponse response=(HttpServletResponse) exContent.getResponse();
				UsernamePasswordCredential credential=new UsernamePasswordCredential(loginId, password);
				AuthenticationStatus status=securityContext.authenticate(req, response, AuthenticationParameters.withParams().credential(credential));
				
				if(status== AuthenticationStatus.SUCCESS)
					return "/admin-home";
			} catch (AppException e) {
				FacesContext cxt=FacesContext.getCurrentInstance();
				cxt.addMessage(null, new FacesMessage(e.getMessage()));
			}
			return null;
		}
		public String cus_login() {
			try {
				HttpServletRequest req=(HttpServletRequest) exContent.getRequest();
				HttpServletResponse response=(HttpServletResponse) exContent.getResponse();
				UsernamePasswordCredential credential=new UsernamePasswordCredential(loginId, password);
				AuthenticationStatus status=securityContext.authenticate(req, response, AuthenticationParameters.withParams().credential(credential));
				
				if(status== AuthenticationStatus.SUCCESS)
					return "/customer_view";
			} catch (AppException e) {
				FacesContext cxt=FacesContext.getCurrentInstance();
				cxt.addMessage(null, new FacesMessage(e.getMessage()));
			}
			return null;
		}
		
		public String logout() {
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			return "/index.xhtml? faces-redirect=true";
		}
		public String cus_logout() {
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			return "/customer_view.xhtml? faces-redirect=true";
		}
		public String getLoginId() {
			return loginId;
		}
		public void setLoginId(String loginId) {
			this.loginId = loginId;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}

		public Users1 getLoginUser() {
			return loginUser;
		}

		public void setLoginUser(Users1 user1) {
			this.loginUser = user1;
		}

		public ExternalContext getExContent() {
			return exContent;
		}

		public void setExContent(ExternalContext exContent) {
			this.exContent = exContent;
		}

		public SecurityContext getSecurityContext() {
			return securityContext;
		}

		public void setSecurityContext(SecurityContext securityContext) {
			this.securityContext = securityContext;
		}
		
}
