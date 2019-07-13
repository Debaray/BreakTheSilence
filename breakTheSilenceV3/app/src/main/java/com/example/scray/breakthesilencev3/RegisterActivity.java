package com.example.scray.breakthesilencev3;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout mDisplayName;
    private TextInputLayout mEmail;
    private TextInputLayout mPassword;
    private Button mCreateBtn;
    private TextView error_display,error_email,error_password;

    private Toolbar mToolbar;

    private DatabaseReference mDatabase;

    //ProgressDialog
    private ProgressDialog mRegProgress;

    //Firebase Auth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //Toolbar Set
//        mToolbar = (Toolbar) findViewById(R.id.register_toolbar);
//        setSupportActionBar(mToolbar);
          getSupportActionBar().setTitle("Create Account");
          getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mRegProgress = new ProgressDialog(this);


        // Firebase Auth

        mAuth = FirebaseAuth.getInstance();
        mDisplayName = findViewById(R.id.register_display_name);
        mEmail = findViewById(R.id.register_email);
        mPassword = findViewById(R.id.reg_password);
        error_display = findViewById(R.id.error_display);
        error_email = findViewById(R.id.error_email);
        error_password = findViewById(R.id.error_password);
        mCreateBtn = findViewById(R.id.reg_create_btn);

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String display_name = mDisplayName.getEditText().getText().toString();
                String email = mEmail.getEditText().getText().toString().trim();
                String password = mPassword.getEditText().getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if(TextUtils.isEmpty(display_name))
                {
                    error_display.setVisibility(View.VISIBLE);
                    error_display.setText("This field is required.");
                }
                else
                {
                    error_display.setVisibility(View.INVISIBLE);
                }

                if(TextUtils.isEmpty(email))
                {
                    error_email.setVisibility(View.VISIBLE);
                    error_email.setText("This field is required.");
                }
                else if(!email.matches(emailPattern))
                {
                    error_email.setVisibility(View.VISIBLE);
                    error_email.setText("Invalid Email Address");
                }
                else
                {
                    error_email.setVisibility(View.INVISIBLE);
                }

                if(TextUtils.isEmpty(password))
                {
                    error_password.setVisibility(View.VISIBLE);
                    error_password.setText("This field is required.");

                }
                else if(password.length() < 6)
                {
                    error_password.setVisibility(View.VISIBLE);
                    error_password.setText("Password is too weak.Password must be 6 characters.");
                }
                else
                {
                    error_password.setVisibility(View.INVISIBLE);
                }

                if(!TextUtils.isEmpty(display_name) && email.matches(emailPattern) && password.length() >= 6){

                    mRegProgress.setTitle("Registering User");
                    mRegProgress.setMessage("Please wait while we create your account !");
                    mRegProgress.setCanceledOnTouchOutside(false);
                    mRegProgress.show();
                    error_password.setVisibility(View.INVISIBLE);
                    error_email.setVisibility(View.INVISIBLE);
                    error_display.setVisibility(View.INVISIBLE);
                    register_user(display_name, email, password);

                }



            }
        });


    }

    private void register_user(final String display_name, final String email, final String password) {



            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){


                        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                        assert current_user != null;
                        String uid = current_user.getUid();

                        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

                        HashMap<String, Object> userMap = new HashMap<>();
                        userMap.put("email",email);
                        userMap.put("id",uid);
                        userMap.put("password",password);
                        userMap.put("userStatus", "Hi there.");
                        userMap.put("username", display_name);
                        userMap.put("status", "true");
                        userMap.put("imageURL", "default");
                        userMap.put("thumb_image", "default");
                        userMap.put("online",100000);
                        userMap.put("search", display_name.toLowerCase());

                        mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful()) {

                                    mRegProgress.dismiss();

                                    if (mAuth.getCurrentUser().isEmailVerified())
                                    {
                                        Intent mainIntent = new Intent(RegisterActivity.this, BottomNavigationActivity.class);
                                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(mainIntent);
                                        finish();
                                    }
                                    else
                                    {
                                        mAuth.getCurrentUser()
                                                .sendEmailVerification()
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                        if(task.isSuccessful())
                                                        {
                                                            Toast.makeText(RegisterActivity.this,"Email Verfication sent to : "+mAuth.getCurrentUser().getEmail(),Toast.LENGTH_SHORT).show();
                                                            Intent intent = new Intent(RegisterActivity.this,VerificationActivity.class);
                                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                        else
                                                        {
                                                            Toast.makeText(RegisterActivity.this,"Failed to sent verification email.Please wait and try again.",Toast.LENGTH_SHORT).show();
                                                            Intent intent = new Intent(RegisterActivity.this,VerificationActivity.class);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                    }
                                                });
                                    }

                                }

                            }
                        });


                    } else {

                        mRegProgress.hide();
                        if(FirebaseAuth.getInstance().getCurrentUser() != null && !FirebaseAuth.getInstance().getCurrentUser().isEmailVerified())
                        {
                            Toast.makeText(RegisterActivity.this,"You Already Registered with this email.Please Log in.",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        Toast.makeText(RegisterActivity.this, "Cannot Sign in. Please check the form and try again.", Toast.LENGTH_LONG).show();
                    }

                }
            });



    }
}