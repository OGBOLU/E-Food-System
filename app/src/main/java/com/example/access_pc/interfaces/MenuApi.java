package com.example.access_pc.interfaces;

import com.example.access_pc.dao.Dish;
import com.example.access_pc.dao.RegisterCustomer;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;

/**
 * Created by Hari on 20/11/17.
 */

public interface MenuApi {

    @Headers("Accept: application/json")
    @GET("/api/dishes")
    Call<ArrayList<Dish>> getMenu();


    @PUT("api/userlogin")
    Call<ResponseBody> users(@Body RegisterCustomer user);

}