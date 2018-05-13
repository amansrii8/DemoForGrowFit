package com.example.flochat.demoforgrowfit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.flochat.demoforgrowfit.adapter.RecyclerAdapter;
import com.example.flochat.demoforgrowfit.model.Product;
import com.example.flochat.demoforgrowfit.model.ProductList;
import com.example.flochat.demoforgrowfit.presenter.ProductPresenter;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ProductPresenter.ProductPresenterInterface {

	@BindView(R.id.recyclerview)
	RecyclerView mRecycler;
	@BindView(R.id.button_error)
	Button buttonError;
	@BindView(R.id.progressbar)
	ProgressBar progressBar;
	@BindView(R.id.filter)
	ImageView imageViewFilter;
	@BindView(R.id.search)
	ImageView imageViewSearch;
	@BindView(R.id.sort)
	ImageView imageViewSort;
	@BindView(R.id.wrapper)
	RelativeLayout relativeLayout;
	@BindView(R.id.edittext)
	EditText editTextSearch;
	@BindView(R.id.imageview_cross)
	ImageView imageViewCross;


	private ProductPresenter productPresenter;
	private RecyclerAdapter recyclerAdapter;
	private ArrayList<Product> productList = new ArrayList<>();
	private ArrayList<Product> productFilteredList = new ArrayList<>();
	private ArrayList<Product> productSearchList = new ArrayList<>();
	private ArrayList<Product> productTempList = new ArrayList<>();
	private boolean isFilterClicked, isSortClick;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mRecycler.setLayoutManager(linearLayoutManager);

		recyclerAdapter = new RecyclerAdapter(this);
		mRecycler.setAdapter(recyclerAdapter);
		productPresenter = new ProductPresenter();
		productPresenter.getProductList(this);
		progressBar.setVisibility(View.VISIBLE);

		buttonError.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				productPresenter.getProductList(MainActivity.this);
			}
		});

		imageViewFilter.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!isFilterClicked) {
					isFilterClicked = true;
					progressBar.setVisibility(View.VISIBLE);
					for (Product product : productList) {
						if (product.isOnSale())
							productFilteredList.add(product);
					}
					recyclerAdapter.notifyDataset(productFilteredList);
					progressBar.setVisibility(View.GONE);
				} else {
					isFilterClicked = false;
					recyclerAdapter.notifyDataset(productList);
				}
			}
		});

		imageViewSearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				imageViewFilter.setVisibility(View.GONE);
				imageViewSearch.setVisibility(View.GONE);
				imageViewSort.setVisibility(View.GONE);
				editTextSearch.setVisibility(View.VISIBLE);
				imageViewCross.setVisibility(View.VISIBLE);

			}
		});

		imageViewCross.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				imageViewFilter.setVisibility(View.VISIBLE);
				imageViewSearch.setVisibility(View.VISIBLE);
				imageViewSort.setVisibility(View.VISIBLE);
				editTextSearch.setVisibility(View.GONE);
				editTextSearch.setText("");
				imageViewCross.setVisibility(View.GONE);
				recyclerAdapter.notifyDataset(productList);
			}
		});

		editTextSearch.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if ("".equals(s.toString())) {
					recyclerAdapter.notifyDataset(productList);
				} else {
					searchResult(s.toString().toLowerCase().toLowerCase());
				}
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		imageViewSort.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!isSortClick) {
					isSortClick = true;
					productTempList = (ArrayList<Product>) productList.clone();
					Collections.sort(productTempList);
					recyclerAdapter.notifyDataset(productTempList);
				} else {
					isSortClick = false;
					recyclerAdapter.notifyDataset(productList);
				}
			}
		});
	}

	private void searchResult(String query) {
		productSearchList.clear();
		for (Product product : productList) {
			if (product.getName().toLowerCase().startsWith(query)) {
				productSearchList.add(product);
			}
		}
		if (productSearchList.size() > 0) {
			recyclerAdapter.notifyDataset(productSearchList);
		} else {
			recyclerAdapter.notifyDataset(productList);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onProductListFetchSuccess(ProductList productArrayList) {
		progressBar.setVisibility(View.GONE);
		relativeLayout.setVisibility(View.VISIBLE);
		if (buttonError.getVisibility() == View.VISIBLE) {
			buttonError.setVisibility(View.GONE);
		}
		productList.clear();
		productList.addAll(productArrayList.getProductLists());
		recyclerAdapter.notifyDataset(productList);
	}

	@Override
	public void onProductListFetchError() {
		relativeLayout.setVisibility(View.GONE);
		progressBar.setVisibility(View.GONE);
		buttonError.setVisibility(View.VISIBLE);
	}

}
