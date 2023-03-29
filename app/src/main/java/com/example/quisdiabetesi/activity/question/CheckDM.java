package com.example.quisdiabetesi.activity.question;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quisdiabetesi.R;
import com.example.quisdiabetesi.activity.MainActivity;
import com.example.quisdiabetesi.helper.SharedRef;
import com.example.quisdiabetesi.model.Question;
import com.example.quisdiabetesi.model.QuestionData;
import com.example.quisdiabetesi.model.ResponseCRUD;
import com.example.quisdiabetesi.network.ApiService;
import com.example.quisdiabetesi.network.RetroClient;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckDM extends AppCompatActivity {

    @BindView(R.id.tv_no)
    TextView tvNo;
    @BindView(R.id.tv_soal)
    TextView tvSoal;
    @BindView(R.id.rb_a)
    RadioButton rbA;
    @BindView(R.id.rb_b)
    RadioButton rbB;
    @BindView(R.id.rb_c)
    RadioButton rbC;
    @BindView(R.id.rb_d)
    RadioButton rbD;
    @BindView(R.id.rb_e)
    RadioButton rbE;
    @BindView(R.id.rb_f)
    RadioButton rbF;
    @BindView(R.id.rb_g)
    RadioButton rbG;
    @BindView(R.id.rg_opt)
    RadioGroup rgOpt;
    @BindView(R.id.btn_lanjut)
    Button btnLanjut;

    double skor = 0;
    int arrQuestion;
    int currentQuestion;
    Question question;
    List<QuestionData> questionDataList;
    SharedRef sharedRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_d_m);
        ButterKnife.bind(this);

        sharedRef = new SharedRef(this);
        questionDataList = new ArrayList<>();

        question = new Question();
        setContent();
    }

    private void setContent() {
        rgOpt.clearCheck();
        arrQuestion = question.question.length;
        int no = currentQuestion + 1;
        if (no == 14) btnLanjut.setText("Selesai");
        tvNo.setText("No. " + no);

        if (currentQuestion >= arrQuestion) {
            //rating();
            Gson gson = new Gson();
            String jsonQuestion = gson.toJson(questionDataList);

            Intent intent = new Intent(getApplicationContext(), DetailQuestion.class);
            intent.putExtra("list_as_string", jsonQuestion);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            finish();
        } else {
            tvSoal.setText(question.getQuestion(currentQuestion));

            int lengtAnswer = question.getArrAnswer(currentQuestion).length;
            switch (lengtAnswer) {
                case 2:
                    //a, b
                    rbA.setText(question.getAnswer(currentQuestion, 0));
                    rbB.setText(question.getAnswer(currentQuestion, 1));
                    goneRadio2();
                    break;
                case 3:
                    //a, b, c
                    rbA.setText(question.getAnswer(currentQuestion, 0));
                    rbB.setText(question.getAnswer(currentQuestion, 1));
                    rbC.setText(question.getAnswer(currentQuestion, 2));
                    goneRadio3();
                    visibleRadio3();
                    break;
                case 4:
                    //a, b, c, d
                    rbA.setText(question.getAnswer(currentQuestion, 0));
                    rbB.setText(question.getAnswer(currentQuestion, 1));
                    rbC.setText(question.getAnswer(currentQuestion, 2));
                    rbD.setText(question.getAnswer(currentQuestion, 3));
                    goneRadio4();
                    visibleRadio4();
                    break;
                case 5:
                    //a, b, c, d, e
                    rbA.setText(question.getAnswer(currentQuestion, 0));
                    rbB.setText(question.getAnswer(currentQuestion, 1));
                    rbC.setText(question.getAnswer(currentQuestion, 2));
                    rbD.setText(question.getAnswer(currentQuestion, 3));
                    rbE.setText(question.getAnswer(currentQuestion, 4));
                    goneRadio5();
                    visibleRadio5();
                    break;
                default:
                    //a, b, c, d, e, f, g
                    visibleRadio7();
                    rbA.setText(question.getAnswer(currentQuestion, 0));
                    rbB.setText(question.getAnswer(currentQuestion, 1));
                    rbC.setText(question.getAnswer(currentQuestion, 2));
                    rbD.setText(question.getAnswer(currentQuestion, 3));
                    rbE.setText(question.getAnswer(currentQuestion, 4));
                    rbF.setText(question.getAnswer(currentQuestion, 5));
                    rbG.setText(question.getAnswer(currentQuestion, 6));
                    break;
            }

        }

        currentQuestion++;
    }

    private void checkAnswer() {
        try {
            if (getCheckRadio(rbA)) {
                //a
                setSkor(rbA, 0);
            } else if (getCheckRadio(rbB)) {
                //b
                setSkor(rbB, 1);
            } else if (getCheckRadio(rbC)) {
                //c
                setSkor(rbC, 2);
            } else if (getCheckRadio(rbD)) {
                //d
                setSkor(rbD, 3);
            } else if (getCheckRadio(rbE)) {
                //e
                setSkor(rbE, 4);
            } else if (getCheckRadio(rbF)) {
                //f
                setSkor(rbF, 5);
            } else if (getCheckRadio(rbG)) {
                //f
                setSkor(rbG, 6);
            } else {
                Toast.makeText(this, "Silahkan pilih jawaban dulu!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "opt " + question.getAnswer(currentQuestion, 0), Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_lanjut)
    public void onViewClicked() {
        checkAnswer();
    }

    private void goneRadio2() {
        rbC.setVisibility(View.GONE);
        goneRadio3();
    }

    private void goneRadio3() {
        rbD.setVisibility(View.GONE);
        goneRadio4();
    }

    private void goneRadio4() {
        rbE.setVisibility(View.GONE);
        goneRadio5();
    }

    private void goneRadio5() {
        rbF.setVisibility(View.GONE);
        rbG.setVisibility(View.GONE);
    }

    //visible
    private void visibleRadio3() {
        rbC.setVisibility(View.VISIBLE);
    }

    private void visibleRadio4() {
        rbC.setVisibility(View.VISIBLE);
        rbD.setVisibility(View.VISIBLE);
    }

    private void visibleRadio5() {
        rbC.setVisibility(View.VISIBLE);
        rbD.setVisibility(View.VISIBLE);
        rbE.setVisibility(View.VISIBLE);
    }

    private void visibleRadio7() {
        rbC.setVisibility(View.VISIBLE);
        rbD.setVisibility(View.VISIBLE);
        rbE.setVisibility(View.VISIBLE);
        rbF.setVisibility(View.VISIBLE);
        rbG.setVisibility(View.VISIBLE);
    }

    private void setSkor(RadioButton radioOpt, int index) {
        int currentAnswer = (currentQuestion - 1);
        String answerRadio = Character.toString(radioOpt.getText().toString().charAt(0));
        String answerAlpabet = Character.toString(question.getAnswer(currentAnswer, index).charAt(0));

        if (answerRadio.equals(answerAlpabet)) {
            //add skor, answer, question
            questionDataList.add(new QuestionData(question.getQuestion(currentAnswer),
                    question.getAnswer(currentAnswer, index), question.getSkor(currentAnswer, index))
            );

            skor += question.getSkor(currentAnswer, index);

            /*Toast.makeText(this, "opt : " + question.getAnswer(currentAnswer, index)
                    + ", skor : "+ question.getSkor(currentAnswer, index), Toast.LENGTH_SHORT).show();*/
            saveQuestion(new QuestionData(question.getQuestion(currentAnswer),
                    question.getAnswer(currentAnswer, index), question.getSkor(currentAnswer, index)));

        } else {
            Toast.makeText(this, "Jawaban Salah " + answerAlpabet + " radio " + answerRadio, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean getCheckRadio(@NotNull RadioButton radioOpt) {
        return radioOpt.isChecked();
    }

    public void onBackPressed() {
        AlertDialog.Builder aleBuilder = new AlertDialog.Builder(CheckDM.this);
        aleBuilder.setTitle("Keluar");
        aleBuilder
                .setMessage("Apakah anda yakin ingin keluar?")
                .setCancelable(false)
                .setPositiveButton("Ya", (dialog, which) -> {
                    startActivity(new Intent(CheckDM.this, MainActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                })
                .setNegativeButton("Tidak", (dialog, id1) -> {
                    //cancel
                    dialog.cancel();
                });
        AlertDialog alertDialog = aleBuilder.create();
        alertDialog.show();
    }

    private void saveQuestion(QuestionData questionData){
        String uuip = sharedRef.getUUIP();

        ApiService service = RetroClient.getApiService();
        Call<ResponseCRUD> call = service.saveHistory(sharedRef.getUUID(), uuip, questionData.getQuestion(),
                questionData.getAnswer(),  questionData.getSekor());

        call.enqueue(new Callback<ResponseCRUD>() {
            @Override
            public void onResponse(Call<ResponseCRUD> call, Response<ResponseCRUD> response) {
                if (response.body().isResponse()) {
                    //Toast.makeText(CheckDM.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    setContent();
                } else {
                    //Toast.makeText(CheckDM.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(CheckDM.this, "Data gagal tersimpan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCRUD> call, Throwable t) {
                //Toast.makeText(CheckDM.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(CheckDM.this, "Cek koneksi internet anda!", Toast.LENGTH_SHORT).show();
                Log.e("error ", t.getMessage());
            }
        });
    }
}