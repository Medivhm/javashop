package com.javashop.entity;

public class MyGifts {
	
	private Gift gift;
	private int number;
	
	public MyGifts(Gift gift, int number) {
		super();
		this.gift = gift;
		this.number = number;
	}
	
	public Gift getGift() {
		return gift;
	}
	public void setGift(Gift gift) {
		this.gift = gift;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
}
