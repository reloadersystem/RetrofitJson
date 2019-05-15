package com.example.silvia.retrofitjson;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.silvia.retrofitjson.Adapter.CardListAdapter;
import com.example.silvia.retrofitjson.Helper.Common;
import com.example.silvia.retrofitjson.Model.Item;
import com.example.silvia.retrofitjson.Model.iMenuRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private final String URL_API="https://api.androidhive.info/json/menu.json";
    private RecyclerView recyclerView;
    private List<Item>list;
    private CardListAdapter adapter;
    private CoordinatorLayout rootLayout;

    private GridLayoutManager layoutManager;

    iMenuRequest mService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mService= Common.getMenuRequest();

        recyclerView=  findViewById(R.id.recycler_view);

        list= new ArrayList<>();
        adapter= new CardListAdapter(this, list);

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        //agregamos GridLayoutManager en 2  filas
//        layoutManager= new GridLayoutManager(this,2);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(adapter);

        addItemToCart();

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, list.get(recyclerView.getChildAdapterPosition(view)).getDescription(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addItemToCart() {
        mService.getMenuList(URL_API)
                .enqueue(new Callback<List<Item>>() {
                    @Override
                    public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                        list.clear(); // remove old items
                        list.addAll(response.body());
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<List<Item>> call, Throwable t) {

                    }
                });

    }
}
