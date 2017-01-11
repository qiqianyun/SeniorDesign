package com.rent_it_app.rent_it;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Mizz on 1/10/17.
 */

public class ResetPasswordActivity extends BaseActivity  {

    private static final String TAG = "ResetPassword";
    private Button mSendButton;
    private EditText mEmailField;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        mEmailField = (EditText) findViewById(R.id.email_field);
        mSendButton = (Button) findViewById(R.id.btn_send_email);

    }


    FirebaseAuth auth = FirebaseAuth.getInstance();

    public void sendPasswordResetEmail(View view) {
        String email = mEmailField.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
            return;
        }

        showProgressDialog();

        if(isEmailValid(email)) {

            auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ResetPasswordActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ResetPasswordActivity.this, SignInActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(ResetPasswordActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
        }else{
            Toast.makeText(ResetPasswordActivity.this, "Please enter valid email address", Toast.LENGTH_SHORT).show();
        }
        hideProgressDialog();

    }

    public void backToSignIn(View view){
        finish();
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


}
