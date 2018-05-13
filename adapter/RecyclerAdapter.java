package com.example.flochat.demoforgrowfit.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.flochat.demoforgrowfit.ProductDetailActivity;
import com.example.flochat.demoforgrowfit.R;
import com.example.flochat.demoforgrowfit.model.Product;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ProductViewHolder> {

	private ArrayList<Product> products = new ArrayList<>();
	Context mContext;

	public RecyclerAdapter(Context context) {
		mContext = context;
	}

	@NonNull
	@Override
	public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemViem = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
		return new ProductViewHolder(itemViem);
	}

	@Override
	public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
		Product product = products.get(holder.getAdapterPosition());

		Glide.with(holder.imageView.getContext()).load(products.get(position).getImage())
				.into(holder.imageView);
		holder.textViewProductName.setText(products.get(holder.getAdapterPosition()).getName());
		holder.textViewProductPrice.setText(products.get(holder.getAdapterPosition()).getRegularPrice());

		holder.bindClickListener(product, position);
	}

	@Override
	public int getItemCount() {
		return products.size();
	}

	public void notifyDataset(ArrayList<Product> productList) {
		products.clear();
		products.addAll(productList);
		notifyDataSetChanged();
	}

	class ProductViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.imageview_product)
		ImageView imageView;

		@BindView(R.id.textview_product_name)
		TextView textViewProductName;

		@BindView(R.id.textview_product_price)
		TextView textViewProductPrice;

		View rootView;

		public ProductViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
			rootView = itemView;
		}

		void bindClickListener(final Product product, int position) {
			rootView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					//TODO
					Intent intent = new Intent(mContext, ProductDetailActivity.class);
					intent.putExtra("PRODUCT", product);
					mContext.startActivity(intent);
				}
			});

		}
	}
}

