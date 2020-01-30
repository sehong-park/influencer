package com.mycompany.vo;

import java.util.List;

public class Cart {
	public static final int SHOPPING = 0;
	public static final int ORDERD = 1;
	public static final int SHIPPING = 2;
	public static final int DELYVERED = 3;

	private int id;
	private int status;
	private int user_id;
	private List<Item> items;
	private int totalPrice;

	public Cart() {
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Cart(int status, int user_id) {
		this.status = status;
		this.user_id = user_id;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "CartVO [id=" + id + ", status=" + status + ", user_id=" + user_id + "]";
	}
}