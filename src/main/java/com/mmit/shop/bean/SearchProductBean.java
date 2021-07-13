package com.mmit.shop.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import com.mmit.shop.model.entity.Product;
import com.mmit.shop.model.service.BrandService;
import com.mmit.shop.model.service.CategoryService;
import com.mmit.shop.model.service.ProductService;

@Named
@ViewScoped
public class SearchProductBean implements Serializable{
	
	private List<Product> products;
	@Inject
	private ProductService service;
	
	@Inject
	private CategoryService catService;
	@Inject
	private BrandService brandService;
	@Inject
	private ExternalContext ecxt;
	
	private String categoryName;
	private String brandName;

	@PostConstruct
	private void init() {
		String cid=ecxt.getRequestParameterMap().get("cId");
		String bid=ecxt.getRequestParameterMap().get("bId");
		
		if(cid !=null) {
			products=service.findByCategory(Integer.parseInt(cid));
			categoryName=catService.findById(Integer.parseInt(cid)).getName();
			}else if(bid!=null) {
				products=service.findByBrand(Integer.parseInt(bid));
				brandName=catService.findById(Integer.parseInt(bid)).getName();
			}else {
				products=service.findAll();
			}
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public ProductService getService() {
		return service;
	}

	public void setService(ProductService service) {
		this.service = service;
	}

	public CategoryService getCatService() {
		return catService;
	}

	public void setCatService(CategoryService catService) {
		this.catService = catService;
	}

	public BrandService getBrandService() {
		return brandService;
	}

	public void setBrandService(BrandService brandService) {
		this.brandService = brandService;
	}

	public ExternalContext getEcxt() {
		return ecxt;
	}

	public void setEcxt(ExternalContext ecxt) {
		this.ecxt = ecxt;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	
	
	
	
	
}
