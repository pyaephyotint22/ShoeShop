package com.mmit.shop.model.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mmit.shop.bean.LoginBean;
import com.mmit.shop.model.entity.Delivery;
import com.mmit.shop.model.entity.OrderDetail;
import com.mmit.shop.model.entity.Orders;
import com.mmit.shop.model.entity.Orders.Status;

@Stateless
public class OrderService {
	@PersistenceContext
	private EntityManager em;
	@Inject
	private LoginBean loginbean;
	public List<Orders> findAll() {
		List<Orders> list=em.createNamedQuery("orders.findAll",Orders.class).getResultList();
		list.forEach(o-> o.getDetails().forEach(od->{}));
		return  list;
	}

	

	public void saveOrder(Orders order, String receiverAddress, String receiverName, String receiverPhone) {
		// TODO Auto-generated method stub
		order.setCustomer(loginbean.getLoginUser());
		Delivery d=new Delivery();
		d.setAddress(receiverAddress);
		d.setPhone(receiverPhone);
		d.setReceiver(receiverName);
		order.addDelivery(d);
		order.setStatus(Status.Pending);
		em.persist(order);
	}



	public List<Orders> findByloginId(int id) {
		List<Orders> list=em.createNamedQuery("order.findbyLoginId",Orders.class)
				.setParameter("loginId", id).getResultList();
		list.forEach(o->o.getDetails().forEach(od->{}));
		return list;
	}



	public Orders findById(int orderId) {
		Orders o=em.find(Orders.class, orderId);
		o.getDetails().forEach(od->{});
		return o;
	}



	public void changeStatus(Orders o) {
		em.merge(o);
		
	}

	
}
