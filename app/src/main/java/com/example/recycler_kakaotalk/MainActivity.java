package com.example.recycler_kakaotalk;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;
    private ArrayList<MainData> dataList;
    private LinearLayoutManager linearLayoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //데이터 초기화
        this.initializeData();

        recyclerView = (RecyclerView)findViewById(R.id.iv_recycle);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mainAdapter);

    }

    public void initializeData(){
        dataList = new ArrayList<>();
        dataList.add(new MainData(R.mipmap.ic_launcher, "Profile1", "hello this is kakaotalk1"));
        dataList.add(new MainData(R.mipmap.ic_launcher, "Profile2", "hello this is kakaotalk2"));
        dataList.add(new MainData(R.mipmap.ic_launcher, "Profile3", "hello this is kakaotalk3"));
        dataList.add(new MainData(R.mipmap.ic_launcher, "Profile4", "hello this is kakaotalk4"));
        dataList.add(new MainData(R.mipmap.ic_launcher, "Profile5", "hello this is kakaotalk5"));
    }
}