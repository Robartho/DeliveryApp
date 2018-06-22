package com.olarth.deliveryapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private TextInputLayout mLoginEmail, mLoginPassword;

    private ProgressDialog msigninprogress;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();

        Toolbar mToolbar = findViewById(R.id.signin_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Login");

        msigninprogress = new ProgressDialog(this);


        mLoginEmail = findViewById(R.id.loginemail);
        mLoginPassword = findViewById(R.id.loginpassword);

        Button mSignInBtn = findViewById(R.id.SignInBtn);

        mSignInBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String email = mLoginEmail.getEditText().getText().toString();
                String password = mLoginPassword.getEditText().getText().toString();

                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password))
                {
                    msigninprogress.setTitle("Enter User!");
                    msigninprogress.setMessage("Loading User!");
                    msigninprogress.setCanceledOnTouchOutside(false);
                    msigninprogress.show();


                    signInUser(email, password);
                }
            }
        });

    }

    private void signInUser(String email, String password)
    {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    msigninprogress.dismiss();

                    Intent mainIntent = new Intent(SignInActivity.this, MainActivity.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mainIntent);
                    finish();
                }else {
                    msigninprogress.hide();
                    Toast.makeText(SignInActivity.this, "Unable To Sign In", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
