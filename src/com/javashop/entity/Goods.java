package com.javashop.entity;

/**
 * @author Medivhm
 *
 */
public class Goods {
	private int id;
	private String goodsName;
	private String prodArea;
	private String spec;
	private double price;
	private String picture;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getProdArea() {
		return prodArea;
	}

	public void setProdArea(String prodArea) {
		this.prodArea = prodArea;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
}
