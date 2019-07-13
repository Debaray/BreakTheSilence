package com.example.scray.breakthesilencev3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class LoginActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private TextInputLayout mLoginEmail;
    private TextInputLayout mLoginPassword;
    private TextView error_mail_log,error_pass_log,forgotPass;

    private Button mLogin_btn;

    private ProgressDialog mLoginProgress;

    private FirebaseAuth mAuth;

    private DatabaseReference mUserDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

//        mToolbar = (Toolbar) findViewById(R.id.login_toolbar);
//       setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Login");


        mLoginProgress = new ProgressDialog(this);

        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users");


        mLoginEmail = (TextInputLayout) findViewById(R.id.login_email);
        mLoginPassword = (TextInputLayout) findViewById(R.id.login_password);
        mLogin_btn = (Button) findViewById(R.id.login_btn);
        error_mail_log = findViewById(R.id.error_email_log);
        error_pass_log = findViewById(R.id.error_password_log);
        forgotPass = findViewById(R.id.forgot_password);

        mLogin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mLoginEmail.getEditText().getText().toString().trim();
                String password = mLoginPassword.getEditText().getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


                if(TextUtils.isEmpty(email))
                {
                    error_mail_log.setVisibility(View.VISIBLE);
                    error_mail_log.setText("This field is required.");
                }
                else if(!email.matches(emailPattern))
                {
                    error_mail_log.setVisibility(View.VISIBLE);
                    error_mail_log.setText("Invalid Email Address");
                }
                else
                {
                    error_mail_log.setVisibility(View.INVISIBLE);
                }

                if(TextUtils.isEmpty(password))
                {
                    error_pass_log.setVisibility(View.VISIBLE);
                    error_pass_log.setText("This field is required.");

                }
                else if(password.length() < 6)
                {
                    error_pass_log.setVisibility(View.VISIBLE);
                    error_pass_log.setText("Password must be 6 characters.");
                }
                else
                {
                    error_pass_log.setVisibility(View.INVISIBLE);
                }

                if(email.matches(emailPattern) && password.length() >= 6){

                    mLoginProgress.setTitle("Logging In");
                    mLoginProgress.setMessage("Please wait while we check your credentials.");
                    mLoginProgress.setCanceledOnTouchOutside(false);
                    mLoginProgress.show();

                    loginUser(email, password);

                }

            }
        });
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(LoginActivity.this,ResetPasswordActivity.class);
                startActivity(i);
                finish();
            }
        });


    }

    private void loginUser(String email, String password) {


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    mLoginProgress.dismiss();

                    String current_user_id = mAuth.getCurrentUser().getUid();
                    String deviceToken = FirebaseInstanceId.getInstance().getToken();

                    mUserDatabase.child(current_user_id).child("device_token").setValue(deviceToken).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            if(mAuth.getCurrentUser().isEmailVerified())
                            {
                                Intent mainIntent = new Intent(LoginActivity.this, BottomNavigationActivity.class);
                                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(mainIntent);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this,"Please Verify you mail.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this,VerificationActivity.class);
                                startActivity(intent);
                                finish();
                            }



                        }
                    });

                } else {

                    mLoginProgress.hide();

                    String task_result = task.getException().getMessage().toString();

                    Toast.makeText(LoginActivity.this, "Error : " + task_result, Toast.LENGTH_LONG).show();

                }

            }
        });


    }
}
