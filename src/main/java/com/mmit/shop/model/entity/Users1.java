package com.mmit.shop.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Entity implementation class for Entity: Users
 *
 */
@Entity

@NamedQuery(name="Users.findAll",query="SELECT u FROM Users1 u WHERE u.LoginId <> :id ")
@NamedQuery(name="Users.findLoginId",query="SELECT u FROM Users1 u WHERE u.LoginId=:LoginId")
@NamedQuery(name="User.getCount",query="SELECT count(u) FROM Users1 u")
@Table(name="users")
public class Users1 implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		private String userName;
		@Column(nullable = false,unique = true)
		private String LoginId;
		private String password;
		private String phone;
		private String address;
		@CreationTimestamp
		private LocalDate created_at;
		@UpdateTimestamp
		private LocalDate updated_at;
		
		private Role role;
		
		public enum Role{
			Admin,Staff,Customer
		}
		
	public Users1() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginId() {
		return LoginId;
	}

	public void setLoginId(String loginId) {
		LoginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDate created_at) {
		this.created_at = created_at;
	}

	public LocalDate getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDate updated_at) {
		this.updated_at = updated_at;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	
   
}
