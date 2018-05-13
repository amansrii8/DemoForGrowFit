package com.example.flochat.demoforgrowfit.presenter;

import com.example.flochat.demoforgrowfit.model.ProductList;
import com.example.flochat.demoforgrowfit.request.ProductRequest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ProductPresenter {

	private static String BASEURL = "http://www.mocky.io/";

	public void getProductList(final ProductPresenterInterface productPresenterInterface) {

	final OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.readTimeout(20, TimeUnit.SECONDS)
				.connectTimeout(20, TimeUnit.SECONDS)
				.build();

		Retrofit retrofit = new Retrofit.Builder()
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.baseUrl(BASEURL)
				.client(okHttpClient)
				.build();

		ProductRequest.ProductListService productListService = retrofit.create(ProductRequest.ProductListService.class);
		Observable<ProductList> productListObservable = productListService.fetchProductList();

		Observer<ProductList> productListObserver = new Observer<ProductList>() {
			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable e) {
				productPresenterInterface.onProductListFetchError();
			}

			@Override
			public void onNext(ProductList productList) {
				productPresenterInterface.onProductListFetchSuccess(productList);
			}
		};

		productListObservable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(productListObserver);

	}

	public interface ProductPresenterInterface {
		void onProductListFetchSuccess(ProductList productLists);
		void onProductListFetchError();
	}
}
