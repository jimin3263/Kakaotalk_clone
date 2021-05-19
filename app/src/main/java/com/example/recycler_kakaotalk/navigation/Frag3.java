package com.example.recycler_kakaotalk.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.recycler_kakaotalk.LoginActivity;
import com.example.recycler_kakaotalk.R;
import com.google.firebase.auth.FirebaseAuth;

public class Frag3 extends Fragment {
    private View view;
    private FirebaseAuth auth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag3, container, false);
        auth = FirebaseAuth.getInstance();
        Button button = view.findViewById(R.id.button_logout);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

            }
        });

        return view;
    }




}
