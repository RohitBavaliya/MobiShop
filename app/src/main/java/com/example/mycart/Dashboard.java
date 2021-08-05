package com.example.mycart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.mycart.models.Model_item_data;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter_Dashboard adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        processData();
    }

    private void processData() {
        Call<List<Model_item_data>> call = ApiController.getInstance().getApi().getFetchData();
        call.enqueue(new Callback<List<Model_item_data>>() {
            @Override
            public void onResponse(Call<List<Model_item_data>> call, Response<List<Model_item_data>> response) {
                List<Model_item_data> itemData = response.body();
                adapter = new Adapter_Dashboard(itemData,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Model_item_data>> call, Throwable t) {
                Toast.makeText(Dashboard.this, "Error:"+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_search_menu,menu);
        MenuItem item = menu.findItem(R.id.search_menu);

        // create searchView when click searchBar
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}