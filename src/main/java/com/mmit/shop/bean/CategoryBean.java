package com.mmit.shop.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.Category;
import com.mmit.shop.model.service.CategoryService;

@Named
@ViewScoped
public class CategoryBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CategoryService service;
	private Category category;
	private List<Category> categories;
	
	@PostConstruct
	private void init() {
		categories=service.findAll();
		category=new Category();
		
	}
	//ajax method
	public void getCategoryInfo(int id) {
		if(id ==0)
			category=new Category();
		else
			category=service.findById(id);
	}
	
	public String save() {
		service.save(category);
		return "/admin/categories?faces-redirect=true";
	}

	public List<Category> getCategories() {
		return categories;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
