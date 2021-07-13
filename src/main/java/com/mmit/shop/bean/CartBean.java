package com.mmit.shop.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.OrderDetail;
import com.mmit.shop.model.entity.Orders;
import com.mmit.shop.model.entity.Product;
import com.mmit.shop.model.service.ProductService;

@Named
@SessionScoped
public class CartBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Inject
	private ProductService service;
	private Orders order;
	@PostConstruct
	private void init() {
		order=new Orders();
	}
	
	public void addToCart(int pId) {
		/* verify product already exist and in cart */
		for(OrderDetail od:order.getDetails()) {
			if(od.getProduct().getId()==pId) {
				od.setSubQty(od.getSubQty()+1);
				return;
			}
		}
		/* Add new product to cart */
		Product p=service.findById(pId);
		OrderDetail orderDetail=new OrderDetail();
		orderDetail.setProduct(p);
		orderDetail.setSubQty(1);
		order.addOrderItem(orderDetail);
	}
	public void updateQty() {
		order.getDetails().
		forEach(od->System.out.println
				(String.format("%s,%d", od.getProduct().getName())));
	}
	public String updateCart() {
		return null;
	}
	public String removeFromCart(OrderDetail od) {
		order.getDetails().remove(od);
		return "/cart.xhtml?faces-redirect=true";
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	
	public int getItemCount() {
		return order.getDetails().size();
	}
}
