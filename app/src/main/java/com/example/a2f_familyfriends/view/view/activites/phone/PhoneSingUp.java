package com.example.a2f_familyfriends.view.view.activites.phone;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.example.a2f_familyfriends.R;
import com.example.a2f_familyfriends.view.view.activites.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

import pl.droidsonroids.gif.GifImageView;

public class PhoneSingUp extends AppCompatActivity {

    private FirebaseAuth auth;

    TextView SignUpTXT , VerifyingTXT , ErrorTXT ;
    GifImageView VerifySuccess ;
    Button VerifyBTN , ContinueBTN ;
    CountryCodePicker ccp;
    EditText NumberEnt;
    PinView VerifyPIN;
    String VerifyCodeBySystem , Code , CountryCodeS ,UserEnteredNumber , PhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_sing_up);

        auth = FirebaseAuth.getInstance();

        SignUpTXT = findViewById(R.id.SingUpTXT);

        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        NumberEnt = (EditText) findViewById(R.id.editText_carrierNumber);

        VerifyingTXT = findViewById(R.id.verifyingTXT);
        ErrorTXT = findViewById(R.id.ErrorTXT);

        VerifyPIN = findViewById(R.id.VerificationPIN);

        VerifySuccess = findViewById(R.id.VerifySuccess);

        VerifyBTN = findViewById(R.id.VerifyBTN);
        VerifyBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountryCodeS = ccp.getSelectedCountryCode();
                UserEnteredNumber = NumberEnt.getText().toString();
                PhoneNumber = "+" + CountryCodeS + UserEnteredNumber;
                if (PhoneNumber.length() < 13){
                    ErrorTXT.setVisibility(View.VISIBLE);
                    VerifyingTXT.setVisibility(View.INVISIBLE);
                }else {
                    if (PhoneNumber.length() == 13) {
                        ErrorTXT.setVisibility(View.GONE);
                        VerifyingTXT.setVisibility(View.VISIBLE);
                        sendVerificationCode(PhoneNumber);
                    }
                }
            }
        });



        ContinueBTN = findViewById(R.id.ContinueBTN);
        ContinueBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PhoneSingUp.this, MainActivity.class));
                finish();
            }
        });

    }

    private void sendVerificationCode(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(PhoneSingUp.this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            VerifyCodeBySystem = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            Code = phoneAuthCredential.getSmsCode();
            if (Code != null){
                VerifyPIN.setText(Code);
                VerifyCode(Code);
                VerifySuccess.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(PhoneSingUp.this,e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    private void VerifyCode(String code) {
        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(VerifyCodeBySystem,code);
        signInWithPhoneAuthCredential(phoneAuthCredential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential phoneAuthCredential) {
        auth.signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                        } else {

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                            }
                        }
                    }
                });
    }


}