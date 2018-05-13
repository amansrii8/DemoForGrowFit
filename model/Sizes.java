package com.example.flochat.demoforgrowfit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sizes implements Serializable {

	@SerializedName("available")
	@Expose
	private boolean available;
	@SerializedName("size")
	@Expose
	private String size;
	@SerializedName("sku")
	@Expose
	private String sku;

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
}
