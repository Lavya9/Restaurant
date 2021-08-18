package com.restaurant.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Menus {

	@Id
	@GeneratedValue
	Long id;

	String category;

	String image;

	String title;

	String description;

	int price;

	public Menus() {
		super();
	}

	public Menus(Long id, String category, String image, String title, String description, int price) {
		super();
		this.id = id;
		this.category = category;
		this.image = image;
		this.title = title;
		this.description = description;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Menus [id=" + id + ", category=" + category + ", image=" + image + ", title=" + title + ", description="
				+ description + ", price=" + price + "]";
	}

}
