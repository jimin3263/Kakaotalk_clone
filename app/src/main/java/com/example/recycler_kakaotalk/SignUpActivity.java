package com.example.recycler_kakaotalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recycler_kakaotalk.data.MainData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "SignUpActivity";

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.signup_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

    }

    private void signUp() {
        String email = ((EditText) findViewById(R.id.Email)).getText().toString();
        String password = ((EditText) findViewById(R.id.Password)).getText().toString();
        String CheckPassword = ((EditText) findViewById(R.id.CheckPassword)).getText().toString();
        String name = ((EditText) findViewById(R.id.Name)).getText().toString();

        if (email.length() > 0 && password.length()>0 && CheckPassword.length()>0 && name.length()>0 ) {
            if (password.equals(CheckPassword)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    //Log.d(TAG, "createUserWithEmail:success");

                                    //type ???????????? ?????? ?????? ??????????????? 0, ????????? 1??? ????????? ????????? ????????? ????????? 0?????? ??????..?
                                    //????????? ?????????????????? ???????????? ????????????..?
                                    //?????? ????????? ??? ???????????? ?????? ?????? => ?????? ?????? 1??? ????????????, ????????? ?????? ?????? ???????????? 0?????? ?????????. ????????? ????????????????????? ?????????????
                                    //?????? ????????? ???????????? ?????? ????????????????????? ???????????? ??????

                                    //?????? -> ??????????????? ????????????
                                    MainData mainData = new MainData();
                                    mainData.setIv_name(name);
                                    mainData.setIv_profile("https://firebasestorage.googleapis.com/v0/b/talk-8aece.appspot.com/o/pngegg.png?alt=media&token=05e37d54-f16b-4fbb-8955-eb27a209c5e6");
                                    mainData.setIv_message("");

                                    String uid = task.getResult().getUser().getUid();
                                    FirebaseDatabase.getInstance().getReference().child("Person").child(uid).setValue(mainData);

                                    //???????????? ?????? -> ????????? ???????????? ??????
                                    Intent intent= new Intent(SignUpActivity.this,LoginActivity.class);
                                    startActivity(intent);
                                } else {
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());


                                }
                            }
                        });
            } else {
                Toast.makeText(this, "??????????????? ???????????? ????????????.", Toast.LENGTH_SHORT).show();
            }
        } else{
            Toast.makeText(this,"????????? ????????? ????????????.",Toast.LENGTH_LONG).show();
        }
    }
}