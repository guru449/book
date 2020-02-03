package com.rakuten.experiment.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Publisher {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int pubid;
	
	String name;
	String city;
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "publisher", cascade = CascadeType.REMOVE)
	List<Book> listodbooks;
	public Publisher(String name, String city) {
		super();
		this.name = name;
		this.city = city;
	
	}
	public int getpubid() {
		return pubid;
	}
	public void setId(int id) {
		this.pubid = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public Publisher() {
		super();
	}
	public void setCity(String city) {
		this.city = city;
	}
	public List<Book> getListodbooks() {
		return listodbooks;
	}
	public void setListodbooks(List<Book> listodbooks) {
		this.listodbooks = listodbooks;
	}
	
	
	

}
