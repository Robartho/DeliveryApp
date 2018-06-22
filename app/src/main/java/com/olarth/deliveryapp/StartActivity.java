package com.olarth.deliveryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    private Button mSignUp, mSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        mSignUp = (Button)findViewById(R.id.SignUp);

        mSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent signupIntent = new Intent(StartActivity.this, SignUpActivity.class);
                startActivity(signupIntent);
            }
        });

        //sing In setting
        mSignIn = (Button)findViewById(R.id.SignIn);

        mSignIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent signinIntent = new Intent(StartActivity.this, SignInActivity.class);
                startActivity(signinIntent);
            }
        });
    }
}