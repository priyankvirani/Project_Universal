package com.project.universal.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.project.universal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity {

    @BindView(R.id.btnSignIn)
    Button btnSignIn;

    @BindView(R.id.btnSignUp)
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.btnSignIn)
    void signIn() {
        Intent signInIntent = new Intent(SignInActivity.this, NavigationDrawerActivity.class);
        startActivity(signInIntent);
        finish();

    }

    @OnClick(R.id.btnSignUp)
    void signUp() {
        Intent signInIntent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(signInIntent);
        finish();

    }
}
