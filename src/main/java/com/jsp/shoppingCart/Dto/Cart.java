package com.jsp.shoppingCart.Dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private  int id;
    private String name;
    private double totalprice;
    @OneToMany
    private List<Item> items;
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
	public double getTotalPrice() {
		return totalprice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalprice = totalPrice;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
}
