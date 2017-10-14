package com.atguigu.crm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="STORAGES")
@Entity
public class Storage extends IdEntity{

	//所属商品
	private Product product;
	//仓库
	private String wareHouse;
	
	//库位
	private String stockWare;
	//数量
	private int stockCount;
	
	//备注
	private String memo;

	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	@Column(name="WARE_HOUSE")
	public String getWareHouse() {
		return wareHouse;
	}

	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}
	@Column(name="stock_ware")
	public String getStockWare() {
		return stockWare;
	}

	public void setStockWare(String stockWare) {
		this.stockWare = stockWare;
	}
	@Column(name="stock_count")
	public int getStockCount() {
		return stockCount;
	}

	public void setStockCount(int stockCount) {
		this.stockCount = stockCount;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
