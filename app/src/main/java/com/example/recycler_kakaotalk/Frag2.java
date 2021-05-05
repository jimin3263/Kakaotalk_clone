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

import com.example.recycler_kakaotalk.PersonAdapter;

import java.util.ArrayList;
//import org.techtown.kakaobottombar.R;

public class Frag2 extends Fragment {
    RecyclerView recyclerView;
    PersonAdapter personAdapter;
    private  ArrayList<Person> cDataList;
    private LinearLayoutManager linearLayoutManager;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.frag2, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.iv_recycle);
        personAdapter = new PersonAdapter(cDataList);
        linearLayoutManager = new LinearLayoutManager( getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(personAdapter);
        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cDataList = new ArrayList<>();
        cDataList.add(new Person(R.mipmap.ic_launcher,"강세미", "ㄱ"));
        cDataList.add(new Person(R.mipmap.ic_launcher,"나영현", "ㄴ"));
        cDataList.add(new Person(R.mipmap.ic_launcher,"임지민", "ㄷ"));
        cDataList.add(new Person(R.mipmap.ic_launcher,"조예진", "ㄹ"));
        cDataList.add(new Person(R.mipmap.ic_launcher,"수학여행", "ㅁ"));
    }

}

