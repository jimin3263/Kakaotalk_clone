package com.example.recycler_kakaotalk.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.recycler_kakaotalk.LoginActivity;
import com.example.recycler_kakaotalk.R;
import com.example.recycler_kakaotalk.SignUpActivity;
import com.example.recycler_kakaotalk.data.MainData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Frag3 extends Fragment {
    public static final int PICK_PROFILE_IMAGE = 10 ;
    private View view;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private ImageView profileImage;
    private Button logout;
    private TextView name, message;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag3, container, false);
        auth = FirebaseAuth.getInstance();

        profileImage = view.findViewById(R.id.iv_profile);
        name = view.findViewById(R.id.iv_name);
        message= view.findViewById(R.id.iv_message);
        logout = view.findViewById(R.id.button_logout);

        profileImage.setOnClickListener(onClickListener);
        logout.setOnClickListener(onClickListener);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Person").child(uid);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                MainData mainData = snapshot.getValue(MainData.class);
                name.setText(mainData.getIv_name());
                message.setText(mainData.getIv_message());
                String image = mainData.getIv_profile();
                Glide.with(getActivity()).load(image).circleCrop().into(profileImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("My profile",String.valueOf(error.toException()));
            }

        });

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_profile:
                    //프로필 사진 설정
                    Intent photoIntent = new Intent(Intent.ACTION_PICK);
                    photoIntent.setType("image/*");
                    startActivityForResult(photoIntent,PICK_PROFILE_IMAGE);
                    break;
                case R.id.iv_name:
                    //이름 설정
                    break;
                case R.id.iv_message:
                    //상메 설정
                    break;
                case R.id.button_logout:
                    auth.signOut();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

}
