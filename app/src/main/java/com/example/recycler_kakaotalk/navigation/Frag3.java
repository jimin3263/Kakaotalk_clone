package com.example.recycler_kakaotalk.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
    private Button logout, resign, edit_name, edit_message;
    private EditText name, message;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag3, container, false);
        auth = FirebaseAuth.getInstance();

        profileImage = view.findViewById(R.id.iv_profile);
        name = view.findViewById(R.id.iv_name);
        message= view.findViewById(R.id.iv_message);
        logout = view.findViewById(R.id.button_logout);
        resign = view.findViewById(R.id.button_resign);
        edit_name = view.findViewById(R.id.edit_name);
        edit_message = view.findViewById(R.id.edit_message);

        profileImage.setOnClickListener(onClickListener);
        logout.setOnClickListener(onClickListener);
        edit_name.setOnClickListener(onClickListener);
        edit_message.setOnClickListener(onClickListener);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid(); //현재 로그인한 사용자 가져오기
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Person").child(uid); //Person, 해당 uid 에 맞는 참조 가져오기(데이터)
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
                case R.id.edit_name:
                    //이름 설정
                    databaseReference.child("iv_name").setValue(name.getText().toString());
                    break;
                case R.id.edit_message:
                    //상메 설정
                    databaseReference.child("iv_message").setValue(message.getText().toString());
                    break;
                case R.id.button_logout:
                    //로그아웃
                    auth.signOut();
                    Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.button_resign:
                    //회원 탈퇴
                    auth.getCurrentUser().delete();
                    Intent intent2 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent2);

            }
        }
    };



    /*
    public void getProfileImage(){
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                MainData mainData = snapshot.getValue(MainData.class);

                String image = mainData.getIv_profile();
                Glide.with(getActivity()).load(image).circleCrop().into(profileImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("My profile",String.valueOf(error.toException()));
            }

        });

    }

     */

}
