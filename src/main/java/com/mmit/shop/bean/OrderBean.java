package com.mmit.shop.bean;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.Orders;
import com.mmit.shop.model.entity.Orders.Status;
import com.mmit.shop.model.service.OrderService;

@Named
@RequestScoped
public class OrderBean {
	
	private List<Orders> orderList;
	@Inject
	private OrderService orderService;
	
	@PostConstruct
	private void init() {
		orderList=orderService.findAll();
	}

	public List<Orders> getOrderList() {
		return orderList;
	}

	/* Ajax call from dasboard.xhtml */
	public void changeOrderStatus(int orderId,String status) {
		Orders o=orderService.findById(orderId);
		if("Received".equals(status)) {
			o.setReceivedDate(LocalDate.now());
			o.setStatus(Status.Received);
			
		}else if("Delivered".equals(status)) {
			o.setStatus(Status.Delivered);
			o.getDelivery().setDeliveredDate(LocalDate.now());
			
		}
		orderService.changeStatus(o);
		orderList=orderService.findAll();
				
	}
}
