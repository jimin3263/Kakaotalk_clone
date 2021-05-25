package com.example.recycler_kakaotalk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recycler_kakaotalk.data.MainData;
import com.example.recycler_kakaotalk.navigation.Frag1;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private GoogleApiClient googleApiClient;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int REQ_SIGN_GOOGLE = 100; //구글 로그인 결과 코드
    private static final String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.default_web_client_id))
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener(){
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();

        //mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.signup_button).setOnClickListener(onClickListener);
        findViewById(R.id.signin_button).setOnClickListener(onClickListener);
        findViewById(R.id.googleSignin_button).setOnClickListener(onClickListener);
        
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.signin_button:
                    signIn();
                    break;
                case R.id.signup_button:
                    Intent intent = new Intent(v.getContext(), SignUpActivity.class);
                    startActivity(intent);
                    break;
                case R.id.googleSignin_button:
                    google_signIn();
                    break;
            }
        }
    };

    private void signIn(){
        String email = ((EditText) findViewById(R.id.Input_Email)).getText().toString();
        String password = ((EditText) findViewById(R.id.Input_Password)).getText().toString();

        if (email.length() > 0 && password.length()>0) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success -> main 으로 이동
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "가입되지 않은 아이디거나 잘못된 비밀번호입니다.", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        } else {
            Toast.makeText(this,"입력이 바르지 않습니다.",Toast.LENGTH_LONG).show();
        }

    }

    private void google_signIn(){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQ_SIGN_GOOGLE);
        Log.d(TAG , "GOOGLE PLEASE SUCCESS");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //로그인 인증
        if(requestCode == REQ_SIGN_GOOGLE){
            // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle: " + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Log.w(TAG, "Google Sign in failed", e);
            }
            /*
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                GoogleSignInAccount account = result.getSignInAccount();
                resultLogin(account); //로그인 결과값을 출력
            }
             */
        }
    }
    //고유하게 식별하는 해당 ID 토큰
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){ //로그인 성공
                            Log.d(TAG, "signInWithGoogle:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        }else{
                            Log.d(TAG, "signInWithGoogle:failure");
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if(user != null){
            String uid = null;
            MainData mainData = new MainData();

            for(UserInfo profile : user.getProviderData()){
                mainData.setIv_name(profile.getDisplayName());
                mainData.setIv_message(" ");
                mainData.setIv_profile(profile.getPhotoUrl().toString());

                uid = profile.getUid();
            }

            FirebaseDatabase.getInstance().getReference().child("Person").child(uid).setValue(mainData);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

/*
    private void resultLogin(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){ //로그인 성공
                            Log.d(TAG, "signInWithGoogle:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }else{
                            Log.d(TAG, "signInWithGoogle:failure");
                        }
                    }
                });
    }
*/
}