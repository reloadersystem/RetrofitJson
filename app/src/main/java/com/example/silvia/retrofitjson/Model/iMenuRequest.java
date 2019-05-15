package com.example.silvia.retrofitjson.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface iMenuRequest {

    @GET
    Call<List<Item>> getMenuList(@Url String url);
}
