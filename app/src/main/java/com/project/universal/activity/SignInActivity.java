package com.project.universal.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.project.universal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignInActivity extends AppCompatActivity {
    /*  Login welcome form  */
    @Nullable
    @BindView(R.id.llLoginBaseForm)
    LinearLayout llLoginBaseForm;

    @Nullable
    @BindView(R.id.txtLoginFormText)
    TextView txtLoginFormText;

    @Nullable
    @BindView(R.id.btnLoginFormEmail)
    Button btnLoginFormEmail;

    @Nullable
    @BindView(R.id.btnLoginFormFacebook)
    Button btnLoginFormFacebook;

    @Nullable
    @BindView(R.id.txtLoginFormRegistration)
    TextView txtLoginFormRegistration;

    @Nullable
    @BindView(R.id.btnLoginFormSkip)
    TextView btnLoginFormSkip;

    /* Registration form */
    @Nullable
    @BindView(R.id.llLoginRegistrationForm)
    LinearLayout llLoginRegistrationForm;

    @Nullable
    @BindView(R.id.txtLoginRegistrationText)
    TextView txtLoginRegistrationText;

    @Nullable
    @BindView(R.id.imgLoginRegistrationCloseButton)
    ImageButton imgLoginRegistrationCloseButton;

    @Nullable
    @BindView(R.id.tilLoginRegistrationEmailWrapper)
    TextInputLayout tilLoginRegistrationEmailWrapper;

    @Nullable
    @BindView(R.id.atxLoginRegistrationEmailTextAuto)
    AutoCompleteTextView atxLoginRegistrationEmailTextAuto;

    @Nullable
    @BindView(R.id.atxLoginRegistrationPasswordWrapper)
    TextInputLayout atxLoginRegistrationPasswordWrapper;

    @Nullable
    @BindView(R.id.rgLoginRegistrationSex)
    RadioGroup rgLoginRegistrationSex;

    @Nullable
    @BindView(R.id.rdLoginRegistrationSexMan)
    RadioButton rdLoginRegistrationSexMan;

    @Nullable
    @BindView(R.id.rdLoginRegistrationSexWoman)
    RadioButton rdLoginRegistrationSexWoman;

    @Nullable
    @BindView(R.id.btnLoginRegistrationConfirm)
    Button btnLoginRegistrationConfirm;

    /* Login with email form */
    @Nullable
    @BindView(R.id.llLoginEmailForm)
    LinearLayout llLoginEmailForm;

    @Nullable
    @BindView(R.id.txtLoginEmailText)
    TextView txtLoginEmailText;

    @Nullable
    @BindView(R.id.imgLoginEmailClose)
    ImageButton imgLoginEmailClose;

    @Nullable
    @BindView(R.id.tilLoginEmailEmailWrapper)
    TextInputLayout tilLoginEmailEmailWrapper;

    @Nullable
    @BindView(R.id.tilLoginEmailPasswordWrapper)
    TextInputLayout tilLoginEmailPasswordWrapper;

    @Nullable
    @BindView(R.id.txtLoginEmailForgottenPassword)
    TextView txtLoginEmailForgottenPassword;

    @Nullable
    @BindView(R.id.btnLoginEmailConfirm)
    Button btnLoginEmailConfirm;

    /* Forgotten password form */

    @Nullable
    @BindView(R.id.llLoginEmailForgottenForm)
    LinearLayout llLoginEmailForgottenForm;

    @Nullable
    @BindView(R.id.txtLoginEmailForgottenText)
    TextView txtLoginEmailForgottenText;

    @Nullable
    @BindView(R.id.imgLoginEmailForgottenBackButton)
    ImageButton imgLoginEmailForgottenBackButton;

    @Nullable
    @BindView(R.id.tilLoginEmailForgottenEmailWrapper)
    TextInputLayout tilLoginEmailForgottenEmailWrapper;

    @Nullable
    @BindView(R.id.btnLoginEmailForgottenConfirm)
    Button btnLoginEmailForgottenConfirm;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ButterKnife.bind(this);


    }


}
