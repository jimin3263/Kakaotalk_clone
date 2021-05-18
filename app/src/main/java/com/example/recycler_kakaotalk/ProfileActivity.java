package com.example.recycler_kakaotalk;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {
    private ImageButton imageButton, backButton, setting, message, edit;
    private TextView textView, textView1;
    private static final String TAG = "ProfileActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        imageButton = findViewById(R.id.iv_profile2);
        textView = findViewById(R.id.iv_name2);
        textView1 = findViewById(R.id.iv_message2);
        edit = findViewById(R.id.iv_edit_profile);
        message = findViewById(R.id.iv_message_profile);

        backButton = findViewById(R.id.iv_back_profile);
        setting = findViewById(R.id.iv_setting_profile);
        //버튼 클릭시 되돌아가기 => frag1.java, 프로필 이런거 바꾸면 데이터 들고가서 다시 수정
        /*
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
*/
        Intent profile_intent = getIntent();
        try{
            if(profile_intent.hasExtra("message") && profile_intent.hasExtra("name")
                    && profile_intent.hasExtra("uri")){

                String name = profile_intent.getStringExtra("name");
                String message = profile_intent.getStringExtra("message");
                String image = profile_intent.getStringExtra("uri");

                Glide.with(this).load(image).circleCrop().into(imageButton);
                textView.setText(name);
                textView1.setText(message);

                Log.d(TAG, "success to load");
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
