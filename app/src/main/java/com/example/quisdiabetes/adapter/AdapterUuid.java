package com.example.quisdiabetes.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quisdiabetes.R;
import com.example.quisdiabetes.activity.history.History;
import com.example.quisdiabetes.model.pasien.PasienItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.quisdiabetes.helper.Contans.DATA;
import static com.example.quisdiabetes.helper.MyFucntion.view;

public class AdapterUuid extends RecyclerView.Adapter<AdapterUuid.ViewHolder> {
    private List<PasienItem> list;
    private Context context;

    public AdapterUuid(List<PasienItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(view(parent, R.layout.item_uuid));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PasienItem pasienItem = list.get(position);
        holder.tvUuid.setText(pasienItem.getUuid());

        //onclick
        holder.cvUuid.setOnClickListener(v -> {
            context.startActivity(new Intent(context, History.class)
                    .putExtra(DATA, list.get(position).getUuid()));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setFilter(List<PasienItem> pasienItems){
        list = new ArrayList<>();
        list.addAll(pasienItems);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_uuid)
        TextView tvUuid;
        @BindView(R.id.cv_uuid)
        CardView cvUuid;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
