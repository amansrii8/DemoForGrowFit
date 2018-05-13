package com.example.flochat.demoforgrowfit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductList {

	@SerializedName("products")
	@Expose
	private ArrayList<Product> productLists;

	public ArrayList<Product> getProductLists() {
		return productLists;
	}

	public void setProductLists(ArrayList<Product> productLists) {
		this.productLists = productLists;
	}
}
