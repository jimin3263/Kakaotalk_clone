package com.example.recycler_kakaotalk.navigation;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycler_kakaotalk.MainActivity;
import com.example.recycler_kakaotalk.data.MainData;
import com.example.recycler_kakaotalk.R;
import com.example.recycler_kakaotalk.adapters.MainAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Frag1 extends Fragment {

    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;
    private ArrayList<MainData> dataList;
    private LinearLayoutManager linearLayoutManager;
    private View v;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frag1, container, false);

        recyclerView = v.findViewById(R.id.iv_recycle);
        recyclerView.setHasFixedSize(true);

        mainAdapter = new MainAdapter(dataList);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mainAdapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Person");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //firebase 데이터 받아옴
                dataList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MainData mainData = snapshot.getValue(MainData.class);
                    dataList.add(mainData);
                }

                //리스트 새로고침
                mainAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //데이터베이스 가져오면서 에러 발생시 로그
                Log.e("Person_list",String.valueOf(error.toException()));
            }
        });
    }

}