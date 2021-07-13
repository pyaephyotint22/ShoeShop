package com.mmit.shop.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import static javax.persistence.FetchType.EAGER;

/**
 * Entity implementation class for Entity: Category
 *
 */
@Entity
@Table(name = "categories")
@NamedQuery(name = "Category.findAll",query="SELECT c FROM Category c")
@NamedQuery(name="Category.findByName",query="SELECT c FROM Category c WHERE c.name= :name")
public class Category implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true, nullable = true)
	private String name;
	@CreationTimestamp
	private LocalDate created_at;
	@UpdateTimestamp
	private LocalDate updated_at;
	@ManyToOne
	@JoinColumn(name = "created_by")
	private Users1 created_by;
	@ManyToOne 
	@JoinColumn(name = "updated_by")
	private Users1 updated_by;
	
	@OneToMany(mappedBy = "category")
	private List<Product> products=new ArrayList<Product>();
	public Category() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Users1 getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Users1 created_by) {
		this.created_by = created_by;
	}

	public Users1 getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Users1 updated_by) {
		this.updated_by = updated_by;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
   
}
