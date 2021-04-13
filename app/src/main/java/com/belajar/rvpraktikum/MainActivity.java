package com.belajar.rvpraktikum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<MotorTersedia> dataList;
    RecyclerView recyclerView;
    DatabaseReference dbRef;
    Context context;
    DatabaseError error;
    InStockAdapter inStockAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_tersedia);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataList = new ArrayList<>();

        dbRef = FirebaseDatabase.getInstance().getReference("motor_terjual");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                dataList.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    MotorTersedia motorTersedia = postSnapshot.getValue(MotorTersedia.class);
                    dataList.add(motorTersedia);
                }
                inStockAdapter = new InStockAdapter(context, dataList);
                recyclerView.setAdapter(inStockAdapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });

        }
    }
