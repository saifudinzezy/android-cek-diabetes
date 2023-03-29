package com.example.quisdiabetesi.activity.history;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.quisdiabetesi.R;
import com.example.quisdiabetesi.adapter.AdapterHistory;
import com.example.quisdiabetesi.helper.SharedRef;
import com.example.quisdiabetesi.model.pasien.PasienItem;
import com.example.quisdiabetesi.model.pasien.ResponsePasien;
import com.example.quisdiabetesi.network.ApiService;
import com.example.quisdiabetesi.network.RetroClient;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class History extends AppCompatActivity {

    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    SharedRef sharedRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("History");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedRef = new SharedRef(this);

        getHistory();
        refresh.setOnRefreshListener(() -> {
            getHistory();
            refresh.setRefreshing(false);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
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
        Call<ResponsePasien> call = service.getByUUIDHistory(sharedRef.getUUID());

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
                    Toast.makeText(History.this, "Cek koneksi internet anda!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePasien> call, Throwable t) {
                loading.setVisibility(View.GONE);
                //Toast.makeText(History.this, "error = " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(History.this, "Cek koneksi internet anda!", Toast.LENGTH_SHORT).show();
                Log.e("error ", t.getMessage());
            }
        });
    }

    private void loadItem(List<PasienItem> list) {
        rv.setLayoutManager(new LinearLayoutManager(History.this));
        AdapterHistory adapter = new AdapterHistory(list, History.this);
        loading.setVisibility(View.GONE);
        rv.setAdapter(adapter);
    }
}