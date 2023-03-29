package com.example.quisdiabetesi.activity.data_diri;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quisdiabetesi.R;
import com.example.quisdiabetesi.activity.question.CheckDM;
import com.example.quisdiabetesi.helper.RandomString;
import com.example.quisdiabetesi.helper.SharedRef;
import com.example.quisdiabetesi.model.Question;
import com.example.quisdiabetesi.model.ResponseCRUD;
import com.example.quisdiabetesi.model.history.HistoryItem;
import com.example.quisdiabetesi.network.ApiService;
import com.example.quisdiabetesi.network.RetroClient;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.quisdiabetesi.helper.Contans.DATA;

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
    String jenkel, isPuasa = "ya";
    SharedRef sharedRef;
    @BindView(R.id.sp_provinsi)
    Spinner spProvinsi;
    String provinsi = "";
    @BindView(R.id.edt_hp)
    TextInputEditText edtHp;
    @BindView(R.id.bottom_sheet)
    FrameLayout bottomSheet;
    BottomSheetBehavior sheetBehavior;
    BottomSheetDialog sheetDialog;
    List arrQuestion = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasien);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Data Diri");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sheetBehavior = BottomSheetBehavior.from(bottomSheet);

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
//        saveDataDiri();
        popUp();
    }

    private void saveDataDiri(String isPuasa, String lamaPuasa, String alasan) {
        HistoryItem historyItem = new HistoryItem();
        //setter
        historyItem.setUmur(edtUmur.getText().toString());
        historyItem.setJenkel(jenkel);
        historyItem.setRasioDm(edtRasioDm.getText().toString());
        historyItem.setPekerjaan(edtPekerjaan.getText().toString());
        if (provinsi == null) this.provinsi = "Aceh";
        historyItem.setProvinsi(provinsi);

        savePasien(historyItem, isPuasa, lamaPuasa, alasan);
    }

    private void savePasien(HistoryItem historyItem, String isPuasa, String lamaPuasa, String alasan) {
        sharedRef.saveUUIP(RandomString.generateRandomStringByUUID());
        String uuip = sharedRef.getUUIP();

        ApiService service = RetroClient.getApiService();
        Call<ResponseCRUD> call = service.savePasien(sharedRef.getUUID(), uuip, historyItem.getUmur(), historyItem.getJenkel(),
                historyItem.getRasioDm(), historyItem.getPekerjaan(), historyItem.getProvinsi(), edtHp.getText().toString(),
                isPuasa, lamaPuasa, alasan);

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

    private void popUp() {
        CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7;
        ImageView imgClose;
        Button btnNext;
        EditText edtLongDay;
        RadioGroup rgQuestion;
        RadioButton rbY, rbN;

        View view = getLayoutInflater().inflate(R.layout.form_plus_pasien, null);

        sheetDialog = new BottomSheetDialog(this);
        sheetDialog.setContentView(view);
        setupFullHeight(sheetDialog);

        //init
        imgClose = view.findViewById(R.id.img_close);
        rgQuestion = view.findViewById(R.id.rg_question);
        rbY = view.findViewById(R.id.rb_y);
        rbN = view.findViewById(R.id.rb_n);
        edtLongDay = view.findViewById(R.id.edt_long_day);
        btnNext = view.findViewById(R.id.btn_save);
        cb1 = view.findViewById(R.id.cb_1); cb2 = view.findViewById(R.id.cb_2);
        cb3 = view.findViewById(R.id.cb_3); cb4 = view.findViewById(R.id.cb_4);
        cb5 = view.findViewById(R.id.cb_5); cb6 = view.findViewById(R.id.cb_6);
        cb7 = view.findViewById(R.id.cb_7);

        rbY.setChecked(true);
        rgQuestion.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_y:
                        removeAll();
                        unCheck(cb1); unCheck(cb2); unCheck(cb3); unCheck(cb4);
                        unCheck(cb5); unCheck(cb6); unCheck(cb7);
                        isPuasa = "ya";
                    break;
                case R.id.rb_n:
                        isPuasa = "tidak";
                    break;
                default:
                    Toast.makeText(this, "Nothing", Toast.LENGTH_SHORT).show();
                    break;
            }
        });

        imgClose.setOnClickListener(v -> {
            sheetDialog.dismiss();
        });

        btnNext.setOnClickListener(v -> {
            JSONArray jsonArray = new JSONArray();
            removeAll();
            getCheckbox(cb1); getCheckbox(cb2); getCheckbox(cb3);
            getCheckbox(cb4); getCheckbox(cb5); getCheckbox(cb6);
            getCheckbox(cb7);

            putJsonArr(jsonArray);
            Log.d("checkbox", jsonArray.toString());
            saveDataDiri(isPuasa, edtLongDay.getText().toString(), jsonArray.toString());
        });

        sheetDialog.show();
    }

    private void setupFullHeight(BottomSheetDialog bottomSheetDialog) {
        FrameLayout bottomSheet = (FrameLayout) bottomSheetDialog.findViewById(R.id.design_bottom_sheet);
        BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
        ViewGroup.LayoutParams layoutParams = bottomSheet.getLayoutParams();

        int windowHeight = getWindowHeight();
        if (layoutParams != null) {
            layoutParams.height = windowHeight;
        }
        bottomSheet.setLayoutParams(layoutParams);
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private int getWindowHeight() {
        // Calculate window height for fullscreen use
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    private void getCheckbox(CheckBox checkBox){
        if (checkBox.isChecked()) {
            arrQuestion.add(checkBox.getText().toString());
        }
    }

    private void unCheck(CheckBox checkBox){
        checkBox.setChecked(false);
    }

    private void removeAll(){
        arrQuestion.clear();
    }

    private String putJsonArr(JSONArray jsonArray){
        for (int i = 0; i < arrQuestion.size(); i++) {
            jsonArray.put(arrQuestion.get(i));
        }
        return jsonArray.toString();
    }
}