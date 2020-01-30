package com.mycompany.vo;

public class Item {
	private int cart_id;
	private int book_id;
	private int amount;
	private Book book;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getTitle() {
		return book.getTitle();
	}

	public int getPrice() {
		return book.getPrice();
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "ItemVO [cart_id=" + cart_id + ", book_id=" + book_id + ", amount=" + amount + "]";
	}
}