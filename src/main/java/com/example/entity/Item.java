package com.example.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ITEMS")
public class Item {
	
	@ManyToOne
	@JoinColumn(name = "category_id", insertable = false, updatable = false)
	private Category category;

	
	@Id
	@SequenceGenerator(name = "ITEM_ID_GENERATOR", sequenceName = "ITEM_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_ID_GENERATOR")
	@Column(name = "ID")
	private Integer id;
	
	
	@Column(name ="NAME")
	private String name;
	
	
	@Column(name = "PRICE")
	private Integer price;
	
	
	@Column(name = "DELETED_AT")
	private LocalDateTime deletedAt;
	
	
	@Column(name = "CATEGORY_ID")
	private Integer categoryId;
	
	
	@Column(name = "STOCK")
	private Integer stock;


	public Category getCategory() {
		return category;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}


	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}


	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}


	public Integer getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}


	public Integer getStock() {
		return stock;
	}


	public void setStock(Integer stock) {
		this.stock = stock;
	}


	public void setCategory(Category category) {
		this.category = category;
	}
	
}
