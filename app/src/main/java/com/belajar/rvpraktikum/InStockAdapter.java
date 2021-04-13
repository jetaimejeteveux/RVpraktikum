package com.belajar.rvpraktikum;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class InStockAdapter
        extends RecyclerView.Adapter<InStockAdapter.InStockViewHolder> {
    Context context;
    List<MotorTersedia> dataList;
    public InStockAdapter(Context context, List<MotorTersedia> dataList) {
        this.dataList = dataList;
        this.context = context;
    }


    @NonNull
    @Override
    public InStockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_terjual, parent, false);
        return new InStockViewHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull InStockViewHolder holder, int position) {

        MotorTersedia motorTersedia = dataList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(motorTersedia.getUrl())
                .into(holder.imgPhoto);
        holder.tvModel.setText(motorTersedia.getModel());
        holder.tvJenis.setText(motorTersedia.getJenis());
        holder.tvPlat.setText(motorTersedia.getPlat());
        holder.ivShare.setOnClickListener(view -> {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, dataList.get(holder.getBindingAdapterPosition()).getHarga());
            shareIntent.setType("text/plain");
            context.startActivity(shareIntent);
        });
        holder.ivForward.setOnClickListener(view -> {
            Intent moveIntent = new Intent(context, DetailActivity.class);
            moveIntent.putExtra("data", (Parcelable) dataList);
            context.startActivity(moveIntent);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class InStockViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto, ivShare, ivForward;
        TextView tvPlat, tvJenis, tvModel;

        public InStockViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.iv_motor_terjual);
            tvPlat = itemView.findViewById(R.id.tv_plat_motor_terjual);
            tvJenis = itemView.findViewById(R.id.tv_jenis_terjual);
            tvModel = itemView.findViewById(R.id.tv_model_terjual);
            ivShare = itemView.findViewById(R.id.iv_share);
            ivForward = itemView.findViewById(R.id.iv_forward);
        }
    }
}



