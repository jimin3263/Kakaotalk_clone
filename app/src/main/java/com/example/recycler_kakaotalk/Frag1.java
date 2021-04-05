package com.example.recycler_kakaotalk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Frag1 extends Fragment {

    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;
    private ArrayList<MainData> dataList;
    private LinearLayoutManager linearLayoutManager;
    private View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frag1, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.iv_recycle);
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
        dataList.add(new MainData(R.mipmap.ic_launcher, "Profile1", "hello this is kakaotalk1"));
        dataList.add(new MainData(R.mipmap.ic_launcher, "Profile2", "hello this is kakaotalk2"));
        dataList.add(new MainData(R.mipmap.ic_launcher, "Profile3", "hello this is kakaotalk3"));
        dataList.add(new MainData(R.mipmap.ic_launcher, "Profile4", "hello this is kakaotalk4"));
        dataList.add(new MainData(R.mipmap.ic_launcher, "Profile5", "hello this is kakaotalk5"));
        dataList.add(new MainData(R.mipmap.ic_launcher, "Profile6", "hello this is kakaotalk6"));
        dataList.add(new MainData(R.mipmap.ic_launcher, "Profile7", "hello this is kakaotalk7"));
        dataList.add(new MainData(R.mipmap.ic_launcher, "Profile8", "hello this is kakaotalk8"));
        dataList.add(new MainData(R.mipmap.ic_launcher, "Profile9", "hello this is kakaotalk9"));
        dataList.add(new MainData(R.mipmap.ic_launcher, "Profile10", "hello this is kakaotalk10"));
        dataList.add(new MainData(R.mipmap.ic_launcher, "Profile11", "hello this is kakaotalk11"));
        dataList.add(new MainData(R.mipmap.ic_launcher, "Profile12", "hello this is kakaotalk12"));
        dataList.add(new MainData(R.mipmap.ic_launcher, "Profile13", "hello this is kakaotalk13"));

    }
}
