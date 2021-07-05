package com.example.quisdiabetes.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quisdiabetes.R;
import com.example.quisdiabetes.activity.question.DetailQuestion;
import com.example.quisdiabetes.model.pasien.PasienItem;
import com.example.quisdiabetes.model.pasien.QuestionItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.quisdiabetes.helper.Contans.DATA;
import static com.example.quisdiabetes.helper.MyFucntion.view;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.ViewHolder> {
    private List<PasienItem> list;
    private Context context;

    public AdapterHistory(List<PasienItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(view(parent, R.layout.item_history));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list.get(position).getQuestion() != null) {
            PasienItem pasienItem = list.get(position);
            holder.tvDate.setText(list.get(position).getQuestion().get(0).getDate());
            double skor = getSkor(position);

            //rendah
            if (skor <= 3) {
                holder.tvResiko.setText("Risiko Rendah");
                holder.cvResiko.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorGreen));
                //sedang
            } else if (skor <= 6) {
                holder.tvResiko.setText("Risiko Sedang");
                holder.cvResiko.setCardBackgroundColor(ContextCompat.getColor(context, R.color.yello));
                //tinggi
            } else {
                holder.tvResiko.setText("Risiko Tinggi");
                holder.cvResiko.setCardBackgroundColor(ContextCompat.getColor(context, R.color.pink));
            }

            String pasien = "UMUR : " + pasienItem.getUmur() + " (Tahun)\n"
                    + "No. HP / WA : " + pasienItem.getNoHp() + "\n"
                    + "Jenis Kelamin : " + pasienItem.getJenkel() + "\n"
                    + "Lama Menderita DM : " + pasienItem.getRasioDm() + " (Tahun)\n"
                    + "Pekerjaan : " + pasienItem.getPekerjaan() + "\n"
                    + "Provinsi Tinggal : " + pasienItem.getProvinsi() + "\n\n";

            pasien += "Saya berpuasa selama : " + pasienItem.getLamaPuasa() + " Hari\n";
            String alasan = "";
            for (int i = 0; i < pasienItem.getAlasan().size(); i++) {
                alasan += "- " + pasienItem.getAlasan().get(i) + "\n";
            }
            pasien += "Saya tidak berpuasa karena : \n" + alasan;

            holder.tvResikoDesc.setText(pasien);
        } else {
            holder.cvResiko.setVisibility(View.GONE);
        }

        //onclick
        holder.cvResiko.setOnClickListener(v -> {
            context.startActivity(new Intent(context, DetailQuestion.class)
                    .putExtra(DATA, list.get(position)));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private double getSkor(int index) {
        double skor = 0;

        if (list.get(index).getQuestion() != null) {
            for (QuestionItem questionData : list.get(index).getQuestion()) {
                skor += Double.parseDouble(questionData.getSkor());
            }
        }

        return skor;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_resiko)
        TextView tvResiko;
        @BindView(R.id.tv_resiko_desc)
        TextView tvResikoDesc;
        @BindView(R.id.cv_resiko)
        CardView cvResiko;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
