package com.example.quisdiabetes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quisdiabetes.activity.MainActivity;
import com.example.quisdiabetes.activity.data_diri.Pasien;
import com.example.quisdiabetes.activity.question.CheckDM;
import com.example.quisdiabetes.helper.RandomString;
import com.example.quisdiabetes.helper.SharedRef;
import com.example.quisdiabetes.model.ResponseCRUD;
import com.example.quisdiabetes.model.history.HistoryItem;
import com.example.quisdiabetes.network.ApiService;
import com.example.quisdiabetes.network.RetroClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.quisdiabetes.helper.Contans.DATA;

public class Rating extends AppCompatActivity {

    @BindView(R.id.tv_sb_1)
    TextView tvSb1;
    @BindView(R.id.sb_1)
    SeekBar sb1;
    @BindView(R.id.tv_sb_2)
    TextView tvSb2;
    @BindView(R.id.sb_2)
    SeekBar sb2;
    @BindView(R.id.tv_sb_3)
    TextView tvSb3;
    @BindView(R.id.sb_3)
    SeekBar sb3;
    @BindView(R.id.tv_sb_4)
    TextView tvSb4;
    @BindView(R.id.sb_4)
    SeekBar sb4;
    @BindView(R.id.tv_sb_5)
    TextView tvSb5;
    @BindView(R.id.sb_5)
    SeekBar sb5;
    @BindView(R.id.tv_sb_6)
    TextView tvSb6;
    @BindView(R.id.sb_6)
    SeekBar sb6;
    @BindView(R.id.tv_sb_7)
    TextView tvSb7;
    @BindView(R.id.sb_7)
    SeekBar sb7;
    @BindView(R.id.tv_sb_8)
    TextView tvSb8;
    @BindView(R.id.sb_8)
    SeekBar sb8;
    @BindView(R.id.tv_sb_9)
    TextView tvSb9;
    @BindView(R.id.sb_9)
    SeekBar sb9;
    @BindView(R.id.tv_sb_10)
    TextView tvSb10;
    @BindView(R.id.sb_10)
    SeekBar sb10;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    int tvprog1, tvprog2, tvprog3, tvprog4, tvprog5, tvprog6, tvprog7, tvprog8, tvprog9, tvprog10= 5;
    int prog1 = 0, prog2 = 0, prog3 = 0, prog4 = 0, prog5 = 0, prog6 = 0, prog7 = 0, prog8 = 0, prog9 = 0, prog10 = 0;
    SharedRef sharedRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_questioners);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Ulasan");
        sharedRef = new SharedRef(this);
        init();
    }

    private void init(){

        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvprog1 = tvprog1 + (i - prog1);
                prog1 = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tvSb1.setText(prog1 + "/" + seekBar.getMax());
            }
        });

        sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvprog2 = tvprog2 + (i - prog2);
                prog2 = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tvSb2.setText(prog2 + "/" + seekBar.getMax());
            }
        });

        sb3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvprog3 = tvprog3 + (i - prog3);
                prog3 = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tvSb3.setText(prog3 + "/" + seekBar.getMax());
            }
        });

        sb4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvprog4 = tvprog4 + (i - prog4);
                prog4 = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tvSb4.setText(prog4 + "/" + seekBar.getMax());
            }
        });

        sb5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvprog5 = tvprog5 + (i - prog5);
                prog5 = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tvSb5.setText(prog5 + "/" + seekBar.getMax());
            }
        });

        sb6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvprog6 = tvprog6 + (i - prog6);
                prog6 = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tvSb6.setText(prog6 + "/" + seekBar.getMax());
            }
        });

        sb7.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvprog7 = tvprog7 + (i - prog7);
                prog7 = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tvSb7.setText(prog7 + "/" + seekBar.getMax());
            }
        });

        sb8.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvprog8 = tvprog8 + (i - prog8);
                prog8 = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tvSb8.setText(prog8 + "/" + seekBar.getMax());
            }
        });

        sb9.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvprog9 = tvprog9 + (i - prog9);
                prog9 = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tvSb9.setText(prog9 + "/" + seekBar.getMax());
            }
        });

        sb10.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvprog10 = tvprog10 + (i - prog10);
                prog10 = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tvSb10.setText(prog10 + "/" + seekBar.getMax());
            }
        });
    }

    private boolean checkProg(int val){
        return val == 0;
    }

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        if (checkProg(prog1)){
            Toast.makeText(this, "Pertanyaan Pertama tidak boleh kosong/nol", Toast.LENGTH_SHORT).show();
        }else if (checkProg(prog2)){
            Toast.makeText(this, "Pertanyaan  Ke-2 tidak boleh kosong/nol", Toast.LENGTH_SHORT).show();
        }else if (checkProg(prog3)){
            Toast.makeText(this, "Pertanyaan  Ke-3 tidak boleh kosong/nol", Toast.LENGTH_SHORT).show();
        }else if (checkProg(prog4)){
            Toast.makeText(this, "Pertanyaan  Ke-4 tidak boleh kosong/nol", Toast.LENGTH_SHORT).show();
        }else if (checkProg(prog5)){
            Toast.makeText(this, "Pertanyaan  Ke-5 tidak boleh kosong/nol", Toast.LENGTH_SHORT).show();
        }else if (checkProg(prog6)){
            Toast.makeText(this, "Pertanyaan  Ke-6 tidak boleh kosong/nol", Toast.LENGTH_SHORT).show();
        }else if (checkProg(prog7)){
            Toast.makeText(this, "Pertanyaan  Ke-7 tidak boleh kosong/nol", Toast.LENGTH_SHORT).show();
        }else if (checkProg(prog8)){
            Toast.makeText(this, "Pertanyaan  Ke-8 tidak boleh kosong/nol", Toast.LENGTH_SHORT).show();
        }else if (checkProg(prog9)){
            Toast.makeText(this, "Pertanyaan  Ke-9 tidak boleh kosong/nol", Toast.LENGTH_SHORT).show();
        }else if (checkProg(prog10)){
            Toast.makeText(this, "Pertanyaan  Ke-10 tidak boleh kosong/nol", Toast.LENGTH_SHORT).show();
        }else {
            saveUlasan(String.valueOf(prog1), String.valueOf(prog2), String.valueOf(prog3), String.valueOf(prog4), String.valueOf(prog5),
                    String.valueOf(prog6), String.valueOf(prog7), String.valueOf(prog8), String.valueOf(prog9), String.valueOf(prog10));
        }
    }

    private void saveUlasan(String q1, String q2, String q3, String q4, String q5, String q6, String q7, String q8,
                            String q9, String q10) {
        ApiService service = RetroClient.getApiService();
        Call<ResponseCRUD> call = service.saveUlasan(q1, q2, q3, q4, q5, q6, q7, q8, q9, q10);

        call.enqueue(new Callback<ResponseCRUD>() {
            @Override
            public void onResponse(Call<ResponseCRUD> call, Response<ResponseCRUD> response) {
//                loading.setVisibility(View.VISIBLE);
                if (response.body().isResponse()) {
//                    loading.setVisibility(View.GONE);
                    sharedRef.saveRating(true);
                    startActivity(new Intent(Rating.this, MainActivity.class));
                    Toast.makeText(Rating.this, "Terima kasih atas Ulasan anda.", Toast.LENGTH_SHORT).show();
                } else {
//                    loading.setVisibility(View.GONE);
                    Toast.makeText(Rating.this, "Data gagal tersimpan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCRUD> call, Throwable t) {
//                loading.setVisibility(View.GONE);
                Toast.makeText(Rating.this, "Cek koneksi internet anda!", Toast.LENGTH_SHORT).show();
                Log.e("error ", t.getMessage());
            }
        });
    }
}