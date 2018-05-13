package com.example.flochat.demoforgrowfit.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable, Comparable<Product> {

	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("style")
	@Expose
	private String style;
	@SerializedName("code_color")
	@Expose
	private String codeColor;
	@SerializedName("color_slug")
	@Expose
	private String colorSlug;
	@SerializedName("color")
	@Expose
	private String color;
	@SerializedName("on_sale")
	@Expose
	private boolean onSale;
	@SerializedName("regular_price")
	@Expose
	private String regularPrice;
	@SerializedName("actual_price")
	@Expose
	private String actualPrice;
	@SerializedName("discount_percentage")
	@Expose
	private String discountPercentage;
	@SerializedName("installments")
	@Expose
	private String installments;
	@SerializedName("image")
	@Expose
	private String image;
	@SerializedName("sizes")
	@Expose
	private ArrayList<Sizes> sizeList;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getCodeColor() {
		return codeColor;
	}

	public void setCodeColor(String codeColor) {
		this.codeColor = codeColor;
	}

	public String getColorSlug() {
		return colorSlug;
	}

	public void setColorSlug(String colorSlug) {
		this.colorSlug = colorSlug;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isOnSale() {
		return onSale;
	}

	public void setOnSale(boolean onSale) {
		this.onSale = onSale;
	}

	public String getRegularPrice() {
		return regularPrice;
	}

	public void setRegularPrice(String regularPrice) {
		this.regularPrice = regularPrice;
	}

	public String getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(String actualPrice) {
		this.actualPrice = actualPrice;
	}

	public String getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(String discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public String getInstallments() {
		return installments;
	}

	public void setInstallments(String installments) {
		this.installments = installments;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public ArrayList<Sizes> getSizeList() {
		return sizeList;
	}

	public void setSizeList(ArrayList<Sizes> sizeList) {
		this.sizeList = sizeList;
	}

	@Override
	public int compareTo(@NonNull Product o) {
		if (this.equals(o)) return 0;
		if ("".equals(discountPercentage)) {
			if (getPriceInInt(this.actualPrice) > getPriceInInt(o.actualPrice)) {
				return -1;
			} else {
				return 1;
			}
		} else {
			float discountPrice1 = getPriceInInt(this.actualPrice)
					* (Integer.parseInt(discountPercentage.replace("%", "")) / 100);
			float discountPrice2 = getPriceInInt(o.actualPrice)
					* (Integer.parseInt(discountPercentage.replace("%", "")) / 100);
			if (discountPrice1 > discountPrice2) {
				return -1;
			} else {
				return 1;
			}
		}
	}

	public int getPriceInInt(String actualPrice) {
		return Integer.parseInt(actualPrice.replace("R$ ", "").replace(",", ""));
	}
}
