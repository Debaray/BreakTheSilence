package com.example.scray.breakthesilencev3;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VerificationActivity extends AppCompatActivity {

    private FirebaseUser firebaseUser;
    private TextView textView;
    private Button verification_btn,refresh_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        getSupportActionBar().setTitle("Verification Mail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        textView = findViewById(R.id.textView);
        verification_btn = findViewById(R.id.verification_btn);
        refresh_btn = findViewById(R.id.refresh_btn);

        textView.setText("Did you get verification mail?We sent a verification mail to "+firebaseUser.getEmail()+ ". Please verify your mail id.");
        verification_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verification_btn.setEnabled(false);
                firebaseUser.sendEmailVerification()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                verification_btn.setEnabled(true);
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(VerificationActivity.this,"Email verfication sent to : "+FirebaseAuth.getInstance().getCurrentUser().getEmail()+".Please check your mail.",Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(VerificationActivity.this,"Failed to sent verification email.Please wait and try again.",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
        refresh_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().getCurrentUser().reload()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(firebaseUser.isEmailVerified())
                                {
                                    Intent intent = new Intent(VerificationActivity.this,BottomNavigationActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(VerificationActivity.this,"Please Verify your mail.",Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });


    }
}
