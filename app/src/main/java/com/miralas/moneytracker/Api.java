package com.miralas.moneytracker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by tiburon on 27/03/2018.
 */

public interface Api {

    @GET("/items")
    Call<List<Item>> getItems(@Query("type") String type);

    @POST("/items/add")
    Call<String> addItem(@Body Item item);

}
