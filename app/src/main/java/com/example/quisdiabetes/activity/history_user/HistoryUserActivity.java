package com.example.quisdiabetes.activity.history_user;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.quisdiabetes.R;
import com.example.quisdiabetes.adapter.AdapterUuid;
import com.example.quisdiabetes.model.pasien.PasienItem;
import com.example.quisdiabetes.model.pasien.ResponsePasien;
import com.example.quisdiabetes.network.ApiService;
import com.example.quisdiabetes.network.RetroClient;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryUserActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    List<PasienItem> pasienItemList;
    AdapterUuid adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_user);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Pengguna");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pasienItemList = new ArrayList<>();

        getHistory();
        refresh.setOnRefreshListener(() -> {
            getHistory();
            refresh.setRefreshing(false);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        item.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when collapsed
                adapter.setFilter(pasienItemList);
                return true; // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                return true; // Return true to expand action view
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getHistory() {
        loading.setVisibility(View.VISIBLE);
        ApiService service = RetroClient.getApiService();
        Call<ResponsePasien> call = service.getUUID();

        call.enqueue(new Callback<ResponsePasien>() {
            @Override
            public void onResponse(Call<ResponsePasien> call, Response<ResponsePasien> response) {
                ResponsePasien pasien = response.body();
                try {
                    if (pasien.isResponse()) {
                        loadItem(response.body().getPasien());
                        loading.setVisibility(View.GONE);
                    } else {
                        loading.setVisibility(View.GONE);
                        Snackbar.make(loading, "Data Kosong!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                } catch (Exception e) {
                    loading.setVisibility(View.GONE);
                    //Toast.makeText(History.this, "error = " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(HistoryUserActivity.this, "Cek koneksi internet anda!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePasien> call, Throwable t) {
                loading.setVisibility(View.GONE);
                //Toast.makeText(History.this, "error = " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(HistoryUserActivity.this, "Cek koneksi internet anda!", Toast.LENGTH_SHORT).show();
                Log.e("error ", t.getMessage());
            }
        });
    }

    private void loadItem(List<PasienItem> list) {
        pasienItemList.addAll(list);
        rv.setLayoutManager(new LinearLayoutManager(HistoryUserActivity.this));
        adapter = new AdapterUuid(pasienItemList, HistoryUserActivity.this);
        loading.setVisibility(View.GONE);
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<PasienItem> filteredModelList = filter(pasienItemList, newText);
        adapter.setFilter(filteredModelList);
        return true;
    }

    private List<PasienItem> filter(List<PasienItem> models, String query) {
        query = query.toLowerCase();

        final List<PasienItem> filteredModelList = new ArrayList<>();
        for (PasienItem model : models) {
            final String text = model.getUuid().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
}