package com.mmit.shop.model.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mmit.shop.model.entity.Brand;
import com.mmit.shop.model.entity.Category;
import com.mmit.shop.model.entity.Product;

@Stateless
public class BrandService {
	
	@PersistenceContext
	private EntityManager em;

	public void save(Brand brand) {
		if(brand.getId()== 0)
			em.persist(brand);
		else em.merge(brand);
		
	}

	public List<Brand> findAll() {
		TypedQuery<Brand> query=em.createNamedQuery("Brand.findAll", Brand.class);
		List<Brand> list=query.getResultList();
		list.forEach(b-> b.getProducts().forEach(p->System.out.println()));
		return list;
	}

	public Brand findById(int id) {
		
		return em.find(Brand.class, id);
	}

	

}
