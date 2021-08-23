package com.restaurant.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Gallery {

	@Id
	@GeneratedValue
	Long id;
	
	String uri;

	public Gallery() {
		super();
	}

	public Gallery(Long id, String uri) {
		super();
		this.id = id;
		this.uri = uri;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@Override
	public String toString() {
		return "Gallery [id=" + id + ", uri=" + uri + "]";
	}
	
	
}
