package com.mmit.shop.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.OrderDetail;
import com.mmit.shop.model.entity.Orders;
import com.mmit.shop.model.entity.Users1;
import com.mmit.shop.model.service.OrderService;

@Named
@ViewScoped
public class PlaceOrderBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String receiverName;
	private String receiverAddress;
	private String receiverPhone;
	
	@Inject
	private OrderService service;
	
	@Inject
	private CartBean cartBean;
	
	@Inject
	private LoginBean loginbean;
	private List<Orders> orders;
	private List<OrderDetail> orderDetails;
	@PostConstruct
	private void init() {
		Users1 u=loginbean.getLoginUser();
		receiverName=u.getUserName();
		receiverAddress=u.getAddress();
		receiverPhone=u.getPhone();
		orderDetails=new ArrayList<OrderDetail>();	
		orders=new ArrayList<Orders>();
	}
	public void getOrderDetails(int orderId) {
		Orders o=service.findById(orderId);
		orderDetails=o.getDetails();
	}
	
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	public String placeOrder() {
		service.saveOrder(cartBean.getOrder(),receiverAddress,receiverName,receiverPhone);
		
		cartBean.setOrder(new Orders());
		return "/frontend/order.xhtml?faces-redirect=true";
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public LoginBean getLoginbean() {
		return loginbean;
	}
	public void setLoginbean(LoginBean loginbean) {
		this.loginbean = loginbean;
	}
	public OrderService getService() {
		return service;
	}
	public void setService(OrderService service) {
		this.service = service;
	}
	public CartBean getCartBean() {
		return cartBean;
	}
	public void setCartBean(CartBean cartBean) {
		this.cartBean = cartBean;
	}
	public List<Orders> getOrders() {
		return service.findByloginId(loginbean.getLoginUser().getId());
	}
	
	
}
