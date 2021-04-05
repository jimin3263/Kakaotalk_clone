package com.example.recycler_kakaotalk;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Frag1 frag1;
    private Frag2 frag2;
    private Frag3 frag3;

    /*
    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;
    private ArrayList<MainData> dataList;
    private LinearLayoutManager linearLayoutManager;
    */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.bottomBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.person:
                        setFrag(0);
                        break;
                    case R.id.chat:
                        setFrag(1);
                        break;
                    case R.id.settings:
                        setFrag(2);
                }
                return true;
            }
        });
        frag1=new Frag1();
        frag2=new Frag2();
        frag3=new Frag3();
        setFrag(0); //첫 프래그먼트 화면을 무엇으로 지정해줄 것인지 선택.


        /*
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
    */
    }

    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.main_frame, frag1);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame, frag2);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame, frag3);
                ft.commit();
                break;

        }
    }
}