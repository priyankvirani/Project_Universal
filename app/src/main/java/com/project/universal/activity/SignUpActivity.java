package com.project.universal.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.project.universal.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {


    @BindView(R.id.btnSignUp)
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSignUp)
    void signUp() {
        Intent signInIntent = new Intent(SignUpActivity.this, NavigationDrawerActivity.class);
        startActivity(signInIntent);
        finish();

    }
}
