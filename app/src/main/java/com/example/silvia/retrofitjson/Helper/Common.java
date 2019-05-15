package com.example.silvia.retrofitjson.Helper;

import com.example.silvia.retrofitjson.Model.iMenuRequest;
import com.example.silvia.retrofitjson.Remote.RetrofitClient;

public class Common {

    public static iMenuRequest getMenuRequest()
    {
        return RetrofitClient.getClient("https://api.androidhive.info/").create(iMenuRequest.class);
    }
}
