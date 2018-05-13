package com.example.flochat.demoforgrowfit;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flochat.demoforgrowfit.model.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailActivity extends Activity {

	@BindView(R.id.imageview_product)
	ImageView productImage;

	@BindView(R.id.textview_product_name)
	TextView textViewName;

	@BindView(R.id.textview_product_price)
	TextView textViewProductPrice;

	@BindView(R.id.textview_product_promo_price)
	TextView textViewProductPromoPrice;

	@BindView(R.id.textview_product_size)
	TextView textViewProductSize;

	@BindView(R.id.textview_product_sale)
	TextView textViewProductOnSale;

	Product product;
	private static final String PRODUCT = "PRODUCT";
	private static final String DISCOUNT = "Discount: ";
	private static final String ON_SALE = "On sale";
	private static final String SIZES = "Sizes: ";

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_details);
		ButterKnife.bind(this);

		product = (Product) getIntent().getSerializableExtra(PRODUCT);
		if (product == null) return;

		Glide.with(this).load(product.getImage()).into(productImage);
		textViewName.setText(product.getName());
		textViewProductPrice.setText(product.getRegularPrice());
		if ("".equals(product.getDiscountPercentage())) {
			textViewProductPromoPrice.setVisibility(View.GONE);
		} else {
			textViewProductPromoPrice.setText(DISCOUNT + product.getDiscountPercentage());
		}
		StringBuilder size = new StringBuilder(SIZES);
		for (int i = 0; i < product.getSizeList().size(); i++) {
			if (product.getSizeList().get(i).isAvailable())
				size = size.append(" " + product.getSizeList().get(i).getSize());
		}
		if ("".equals(size)) {
			textViewProductSize.setVisibility(View.GONE);
		} else {
			textViewProductSize.setText(size);
		}

		if (product.isOnSale())
			textViewProductOnSale.setText(ON_SALE);

	}
}
