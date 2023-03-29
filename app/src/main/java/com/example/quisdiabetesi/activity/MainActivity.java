package com.example.quisdiabetesi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quisdiabetesi.R;
import com.example.quisdiabetesi.activity.data_diri.Pasien;
import com.example.quisdiabetesi.activity.history.History;
import com.example.quisdiabetesi.helper.SharedRef;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    SharedRef sharedRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sharedRef = new SharedRef(this);
        if (sharedRef.getUUID().isEmpty()){
            UUID();
        }
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
        //if (sharedRef.getUUID().isEmpty()) sharedRef.saveUUID(RandomString.generateRandomStringByUUIDNoDash());
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(
                Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build())).build(), 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {

            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                if (!FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().isEmpty()) {
                    //save number
                    sharedRef.saveUUID(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());
                    Toast.makeText(this, "No HP Sudah Terdaftar!", Toast.LENGTH_SHORT).show();
                    if (!sharedRef.getPerijinan()) permission();
                    return;

                } else {
                    if (response == null) {
                        Toast.makeText(this, "Sign In Failed", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                        Toast.makeText(this, "No Network", Toast.LENGTH_SHORT).show();

                        return;
                    }

                    if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                        Toast.makeText(this, "unkown Error", Toast.LENGTH_SHORT).show();

                        return;
                    }
                }
            }
        }
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