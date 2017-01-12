package com.rent_it_app.rent_it;

/**
 * Created by Mizz on 1/10/17.
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends BaseActivity implements
        View.OnClickListener {

    private static final String TAG = "EmailPassword";

    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mResetButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        mEmailField = (EditText) findViewById(R.id.field_email);
        mPasswordField = (EditText) findViewById(R.id.field_password);
        mResetButton = (Button) findViewById(R.id.btn_forgot_password);

        findViewById(R.id.email_sign_in_button).setOnClickListener(this);
        findViewById(R.id.sign_out_button).setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();
        //if user haven't varified her email address, send her another email and sign her out
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                    // Only send if email is not confirmed.
                    if (!user.isEmailVerified()) {
                        user.sendEmailVerification()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(SignInActivity.this, "Please varify your email address", Toast.LENGTH_SHORT).show();
                                            Log.d(TAG, "Email sent");
                                        }
                                    }
                                });
                        mAuth.signOut();
                    }
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }

                updateUI(user);

            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());


                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(SignInActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }


                        hideProgressDialog();



                    }
                });

    }

   /* private void signOut() {
        mAuth.signOut();
        updateUI(null);
        mPasswordField.setText("");
        findViewById(R.id.myStatus).setVisibility(View.VISIBLE);

    }*/

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required");
            valid = false;
        } /*else if(!isEmailValid(email)){
            mEmailField.setError("Enter Valid Email Address");
            valid = false;
        }*/ else {
            mEmailField.setError(null);
        }


        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }


    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
        if (user != null) {
            //if logged in
            /*findViewById(R.id.email_password_buttons).setVisibility(View.GONE);
            findViewById(R.id.email_password_fields).setVisibility(View.GONE);
            findViewById(R.id.btn_forgot_password).setVisibility(View.GONE);
            findViewById(R.id.btn_sign_up).setVisibility(View.GONE);
            findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);*/
            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
            startActivity(intent);
        } else {

            findViewById(R.id.email_password_buttons).setVisibility(View.VISIBLE);
            findViewById(R.id.email_password_fields).setVisibility(View.VISIBLE);
            findViewById(R.id.btn_forgot_password).setVisibility(View.VISIBLE);
            findViewById(R.id.btn_sign_up).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_button).setVisibility(View.GONE);

            //findViewById(R.id.myStatus).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.email_sign_in_button) {
            signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
        } /*else if (i == R.id.sign_out_button) {
            signOut();
        }*/
    }

    //Added - pressing forgot button will take to another activity
    public void goToRestPasswordActivity(View view) {
        Intent intent = new Intent(SignInActivity.this, ResetPasswordActivity.class);
        //for debugging
        //Intent intent = new Intent(SignInActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void goToSignUpActivity(View view) {
        Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

}