package com.mmit.shop.model.service;

import java.security.Principal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import com.mmit.security.CommonException;
import com.mmit.shop.bean.LoginBean;
import com.mmit.shop.model.entity.Users1;
@Stateless
public class UserService {

	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private Pbkdf2PasswordHash encoder;
	@Inject
	private Principal principal;

	/*
	 * public List<Users1> findAll() {
	 * 
	 * return em.createNamedQuery("Users.findAll",Users1.class).getResultList(); }
	 */

	public void save(Users1 user) {
		if(user.getId()==0)
			{
			user.setPassword(encoder.generate(user.getPassword().toCharArray()));
			em.persist(user);
			}
		else {
				em.merge(user);
		}
	}

	public Users1 findByLogId(String LoginId) {
	
		TypedQuery<Users1> query=em.createNamedQuery("Users.findLoginId", Users1.class);
		query.setParameter("LoginId", LoginId);
		Users1 u=null;
		try {
			u= query.getSingleResult();
		} catch (NoResultException e) {
			u=null;
		}
		return u;
	}

	public long getCount() {
		TypedQuery<Long> query=em.createNamedQuery("User.getCount", Long.class);
		return query.getSingleResult();
	}

	public List<Users1> findAllExceptMe() {
		
		return em.createNamedQuery("Users.findAll",Users1.class).setParameter("id", principal.getName()).getResultList();
	}

	public void changePassword(String oldPassword, String newPassword, String confirmPassword) {
		Users1 loginUser=findByLogId(principal.getName());
		
		if(!encoder.verify(oldPassword.toCharArray(), loginUser.getPassword())) {
			//not match old password
			throw new CommonException("Incorrect old password!");
		}
		if(!newPassword.equals(confirmPassword)) {
			throw new CommonException("Confirm password do not match!");
			
		}
		loginUser.setPassword(encoder.generate(newPassword.toCharArray()));
		
	}

	/*
	 * private Users1 findByEmail(String LoginId) { TypedQuery<Users1>
	 * query=em.createNamedQuery("Users.findbyEmail", Users1.class);
	 * query.setParameter("LoginId",LoginId); Users1 u=null; try { u=
	 * query.getSingleResult(); } catch (NoResultException e) { u=null; } return u;
	 * }
	 */

	public Users1 findById(int id) {
		
		return em.find(Users1.class, id);
	}
}
