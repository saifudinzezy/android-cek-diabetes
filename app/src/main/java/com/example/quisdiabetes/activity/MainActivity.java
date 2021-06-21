package com.example.quisdiabetes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quisdiabetes.R;
import com.example.quisdiabetes.activity.data_diri.Pasien;
import com.example.quisdiabetes.activity.history.History;
import com.example.quisdiabetes.activity.question.CheckDM;
import com.example.quisdiabetes.helper.RandomString;
import com.example.quisdiabetes.helper.SharedRef;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    SharedRef sharedRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        UUID();
        if (!sharedRef.getPerijinan()) permission();
    }

    @OnClick({R.id.cek_resiko, R.id.riwayat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cek_resiko:
                startActivity(new Intent(this, Pasien.class));
                break;
            case R.id.riwayat:
                startActivity(new Intent(this, History.class));
                break;
        }
    }

    private void UUID(){
        sharedRef = new SharedRef(this);
        if (sharedRef.getUUID().isEmpty()) sharedRef.saveUUID(RandomString.generateRandomStringByUUIDNoDash());
        //Toast.makeText(this, "UUID " + sharedRef.getUUID(), Toast.LENGTH_SHORT).show();
    }

    public void permission() {
        AlertDialog.Builder aleBuilder = new AlertDialog.Builder(MainActivity.this);
        aleBuilder.setTitle("Perijinan");
        aleBuilder
                .setMessage("Ijinkan Data yang anda isi akan kami simpan, untuk histori anda ketika pengecekan?")
                .setCancelable(false)
                .setPositiveButton("Ya", (dialog, which) -> {
                    Toast.makeText(this, "Data tersimpan!", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                    sharedRef.savePerijinan(true);
                })
                .setNegativeButton("Tidak", (dialog, id1) -> {
                    finish();
                    dialog.cancel();
                });
        AlertDialog alertDialog = aleBuilder.create();
        alertDialog.show();
    }
}