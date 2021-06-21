package com.example.quisdiabetes.activity.data_diri;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quisdiabetes.R;
import com.example.quisdiabetes.activity.question.CheckDM;
import com.example.quisdiabetes.helper.RandomString;
import com.example.quisdiabetes.helper.SharedRef;
import com.example.quisdiabetes.model.Question;
import com.example.quisdiabetes.model.ResponseCRUD;
import com.example.quisdiabetes.model.history.HistoryItem;
import com.example.quisdiabetes.network.ApiService;
import com.example.quisdiabetes.network.RetroClient;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.quisdiabetes.helper.Contans.DATA;

public class Pasien extends AppCompatActivity {
    @BindView(R.id.edt_umur)
    EditText edtUmur;
    @BindView(R.id.rg_opt)
    RadioGroup rgOpt;
    @BindView(R.id.edt_rasio_dm)
    TextInputEditText edtRasioDm;
    @BindView(R.id.edt_pekerjaan)
    TextInputEditText edtPekerjaan;
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.btn_simpan)
    Button btnSimpan;
    @BindView(R.id.rb_l)
    RadioButton rbL;
    @BindView(R.id.rb_p)
    RadioButton rbP;
    String jenkel;
    SharedRef sharedRef;
    @BindView(R.id.sp_provinsi)
    Spinner spProvinsi;
    String provinsi = "";
    @BindView(R.id.edt_hp)
    TextInputEditText edtHp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasien);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Data Diri");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedRef = new SharedRef(this);
        initSpProvinsi();
        initRadio();
    }

    private void initSpProvinsi() {
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, Question.provinces);

        //Memasukan Adapter pada Spinner
        spProvinsi.setAdapter(adapter);

        //Mengeset listener untuk mengetahui event/aksi saat item dipilih
        spProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(),adapter.getItem(i) + i, Toast.LENGTH_SHORT).show();
                provinsi = adapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView adapterView) {

            }
        });
    }

    private void initRadio() {
        rbL.setChecked(true);
        jenkel = rbL.getText().toString();

        rgOpt.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_l:
                    Toast.makeText(this, rbL.getText(), Toast.LENGTH_SHORT).show();
                    jenkel = rbL.getText().toString();
                    break;
                case R.id.rb_p:
                    Toast.makeText(this, rbP.getText(), Toast.LENGTH_SHORT).show();
                    jenkel = rbP.getText().toString();
                    break;
                default:
                    Toast.makeText(this, "Nothing", Toast.LENGTH_SHORT).show();
                    break;
            }
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

    @OnClick(R.id.btn_simpan)
    public void onViewClicked() {
        getDataDiri();
    }

    private void getDataDiri() {
        HistoryItem historyItem = new HistoryItem();
        //setter
        historyItem.setUmur(edtUmur.getText().toString());
        historyItem.setJenkel(jenkel);
        historyItem.setRasioDm(edtRasioDm.getText().toString());
        historyItem.setPekerjaan(edtPekerjaan.getText().toString());
        if (provinsi == null) this.provinsi = "Aceh";
        historyItem.setProvinsi(provinsi);

        savePasien(historyItem);
    }

    private void savePasien(HistoryItem historyItem) {
        sharedRef.saveUUIP(RandomString.generateRandomStringByUUID());
        String uuip = sharedRef.getUUIP();

        ApiService service = RetroClient.getApiService();
        Call<ResponseCRUD> call = service.savePasien(sharedRef.getUUID(), uuip, historyItem.getUmur(), historyItem.getJenkel(),
                historyItem.getRasioDm(), historyItem.getPekerjaan(), historyItem.getProvinsi(), edtHp.getText().toString());

        call.enqueue(new Callback<ResponseCRUD>() {
            @Override
            public void onResponse(Call<ResponseCRUD> call, Response<ResponseCRUD> response) {
                loading.setVisibility(View.VISIBLE);
                if (response.body().isResponse()) {
                    //Toast.makeText(Pasien.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    loading.setVisibility(View.GONE);
                    startActivity(new Intent(Pasien.this, CheckDM.class)
                            .putExtra(DATA, historyItem));
                } else {
                    loading.setVisibility(View.GONE);
                    //Toast.makeText(Pasien.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(Pasien.this, "Data gagal tersimpan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCRUD> call, Throwable t) {
                loading.setVisibility(View.GONE);
                //Toast.makeText(Pasien.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(Pasien.this, "Cek koneksi internet anda!", Toast.LENGTH_SHORT).show();
                Log.e("error ", t.getMessage());
            }
        });
    }
}