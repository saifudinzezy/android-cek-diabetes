package com.example.quisdiabetes.activity.question;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.example.quisdiabetes.R;
import com.example.quisdiabetes.activity.MainActivity;
import com.example.quisdiabetes.activity.history.History;
import com.example.quisdiabetes.helper.SharedRef;
import com.example.quisdiabetes.model.QuestionData;
import com.example.quisdiabetes.model.ResponseCRUD;
import com.example.quisdiabetes.model.pasien.PasienItem;
import com.example.quisdiabetes.model.pasien.QuestionItem;
import com.example.quisdiabetes.network.ApiService;
import com.example.quisdiabetes.network.RetroClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.quisdiabetes.helper.Contans.DATA;

public class DetailQuestion extends AppCompatActivity {

    @BindView(R.id.tv_resiko)
    TextView tvResiko;
    @BindView(R.id.tv_resiko_desc)
    TextView tvResikoDesc;
    @BindView(R.id.cv_resiko)
    CardView cvResiko;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    String questionListAsString;
    SharedRef sharedRef;
    PasienItem data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_question);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Hasil");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentOnDesc();
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

    private void setContentOnDesc() {
        Gson gson = new Gson();
        String question = "";
        double skor = 0;

        questionListAsString = getIntent().getStringExtra("list_as_string");
        Type type = new TypeToken<List<QuestionData>>() {
        }.getType();

        if (questionListAsString != null) {
            List<QuestionData> questionDataList = gson.fromJson(questionListAsString, type);
            for (QuestionData questionData : questionDataList) {
                Log.i("Car Data", questionData.getQuestion() + "-" + questionData.getAnswer());
                question += questionData.getQuestion() + " \n"
                        + questionData.getAnswer() + "\n\n";
                skor += questionData.getSekor();
            }

            if (skor <= 3) {
                tvResiko.setText("Risiko Rendah");
                tvResikoDesc.setText(getString(R.string.resiko_rendah));
                cvResiko.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));
            } else if (skor <= 6) {
                tvResiko.setText("Risiko Sedang");
                tvResikoDesc.setText(getString(R.string.resiko_sedang));
                cvResiko.setCardBackgroundColor(ContextCompat.getColor(this, R.color.yello));
            } else {
                tvResiko.setText("Risiko Tinggi");
                tvResikoDesc.setText(getString(R.string.resiko_tinggi));
                cvResiko.setCardBackgroundColor(ContextCompat.getColor(this, R.color.pink));
            }

            tvDetail.setText(question);
        }

        //data from adapter
        data = getIntent().getParcelableExtra(DATA);
        if (data != null) {
            for (QuestionItem questionData : data.getQuestion()) {
                Log.i("Car Data", questionData.getQuestion() + "-" + questionData.getAnswer());
                question += questionData.getQuestion() + " \n"
                        + questionData.getAnswer() + "\n\n";
                skor += Double.parseDouble(questionData.getSkor());
            }

            if (skor <= 3) {
                tvResiko.setText("Risiko Rendah");
                tvResikoDesc.setText(getString(R.string.resiko_rendah));
                cvResiko.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));
            } else if (skor <= 6) {
                tvResiko.setText("Risiko Sedang");
                tvResikoDesc.setText(getString(R.string.resiko_sedang));
                cvResiko.setCardBackgroundColor(ContextCompat.getColor(this, R.color.yello));
            } else {
                tvResiko.setText("Risiko Tinggi");
                tvResikoDesc.setText(getString(R.string.resiko_tinggi));
                cvResiko.setCardBackgroundColor(ContextCompat.getColor(this, R.color.pink));
            }

            tvDetail.setText(question);
        }
    }

    public void onBackPressed() {
        sharedRef = new SharedRef(DetailQuestion.this);
        if (!sharedRef.getRating()) {
            rating();
        } else {
            startActivity(new Intent(DetailQuestion.this, History.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
    }

    private void rating() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(DetailQuestion.this).inflate(R.layout.pop_rating,
                viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailQuestion.this);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        //init ui
        Button btnSend = dialogView.findViewById(R.id.btn_submit);
        TextView mRatingScale = dialogView.findViewById(R.id.tv_rating);
        EditText edtFeedback = dialogView.findViewById(R.id.edt_feedback);
        RatingBar ratingBar = dialogView.findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener((ratingBar1, v, b) -> {
            mRatingScale.setText(String.valueOf(v));
            switch ((int) ratingBar1.getRating()) {
                case 1:
                    mRatingScale.setText("Very bad");
                    break;
                case 2:
                    mRatingScale.setText("Need some improvement");
                    break;
                case 3:
                    mRatingScale.setText("Good");
                    break;
                case 4:
                    mRatingScale.setText("Great");
                    break;
                case 5:
                    mRatingScale.setText("Awesome. I love it");
                    break;
                default:
                    mRatingScale.setText("");
            }
        });

        btnSend.setOnClickListener(v -> {
            if (ratingBar.getRating() == 0) {
                Toast.makeText(DetailQuestion.this, "Please Rating Star", Toast.LENGTH_LONG).show();
            } else {
                saveRating("" + ratingBar.getRating(), edtFeedback.getText().toString(), alertDialog);
            }
        });

        alertDialog.show();
    }

    private void saveRating(String star, String ulasan, AlertDialog alertDialog) {
        ApiService service = RetroClient.getApiService();
        Call<ResponseCRUD> call = service.saveRating(star, ulasan);

        call.enqueue(new Callback<ResponseCRUD>() {
            @Override
            public void onResponse(Call<ResponseCRUD> call, Response<ResponseCRUD> response) {
                if (response.body().isResponse()) {
                    Toast.makeText(DetailQuestion.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    sharedRef.saveRating(true);
                    Toast.makeText(DetailQuestion.this, "Terima kasih sudah memberi rating", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(DetailQuestion.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCRUD> call, Throwable t) {
                Toast.makeText(DetailQuestion.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error ", t.getMessage());
            }
        });
    }

    @OnClick(R.id.home)
    public void onViewClicked() {
      onBackPressed();
    }
}