package com.belajar.rvpraktikum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.belajar.rvpraktikum.databinding.ActivityDetailBinding;
import com.belajar.rvpraktikum.databinding.ActivityMainBinding;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityDetailBinding binding;
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MotorTersedia dataList = getIntent().getParcelableExtra("data");
        binding.tvPlatMotorDetailTersedia.setText(dataList.getPlat());
        binding.tvJenisDetailTersedia.setText(dataList.getJenis());
        binding.tvHargaMotorTersedia.setText(dataList.getHarga());
        binding.tvTahunMotorTersedia.setText(dataList.getTahun());
        binding.tvKapasitasMesinDetailTersedia.setText(dataList.getKapasitas_mesin());
        Glide.with(this)
                .load(dataList.getUrl())
                .into(binding.ivMotorDetailTersedia);

    }
}