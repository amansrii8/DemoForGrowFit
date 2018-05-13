package com.example.flochat.demoforgrowfit.request;

import com.example.flochat.demoforgrowfit.model.ProductList;



import retrofit2.http.GET;
import rx.Observable;

public class ProductRequest {

	public interface ProductListService {
		@GET("v2/59b6a65a0f0000e90471257d")
		Observable<ProductList> fetchProductList();
	}
}
