package com.example.quisdiabetes.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quisdiabetes.R;
import com.example.quisdiabetes.activity.MainActivity;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edt_username)
    TextInputEditText edtUsername;
    @BindView(R.id.edt_password)
    TextInputEditText edtPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        if (getText(edtUsername).equals("admin") && getText(edtPassword).equals("admin")){
            startActivity(new Intent(this, MainActivity.class));
            Toast.makeText(this, "Selamat datang admin", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "username dan passeord salah!!", Toast.LENGTH_SHORT).show();
        }
    }

    private String getText(TextInputEditText input){
        return input.getText().toString();
    }
}