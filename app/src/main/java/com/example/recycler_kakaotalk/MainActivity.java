package com.example.recycler_kakaotalk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import com.example.recycler_kakaotalk.navigation.Frag1;
import com.example.recycler_kakaotalk.navigation.Frag2;
import com.example.recycler_kakaotalk.navigation.Frag3;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Frag1 frag1;
    private Frag2 frag2;
    private Frag3 frag3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* tool bar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* bottom navigation*/
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


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.main_frame, frag1);
                getSupportActionBar().setTitle("친구");
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame, frag2);
                getSupportActionBar().setTitle("채팅");
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame, frag3);
                getSupportActionBar().setTitle("설정");
                ft.commit();
                break;

        }
    }

}