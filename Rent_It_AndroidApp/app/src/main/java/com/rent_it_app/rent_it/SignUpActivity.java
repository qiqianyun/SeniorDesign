package com.rent_it_app.rent_it;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

/**
 * Created by Mimi on 1/10/17.
 */

public class SignUpActivity extends BaseActivity {

    private static final String TAG = "SignUp";
    private EditText mEmailField;
    private EditText mPasswordField;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mEmailField = (EditText) findViewById(R.id.field_email);
        mPasswordField = (EditText) findViewById(R.id.field_password);
        mAuth = FirebaseAuth.getInstance();

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

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                            Toast.makeText(SignUpActivity.this, "We have sent you a verification email", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                            startActivity(intent);


                        }else if(task.getException() instanceof FirebaseAuthUserCollisionException){
                            Toast.makeText(SignUpActivity.this, "Email Address already registered", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(SignUpActivity.this, R.string.auth_failed, Toast.LENGTH_SHORT).show();
                        }

                        hideProgressDialog();

                    }
                });

    }

    /*private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }*/

    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
        if (user != null) {
            //user logged in
            /*Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(intent);*/
        } else{
            /*Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
            startActivity(intent);*/
        }
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        }else if(!isEmailValid(email)){
            mEmailField.setError("Enter Valid Email Address");
            valid = false;
        } else {
            mEmailField.setError(null);
        }



        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required");
            valid = false;
        } else if(password.length() < 5) {
            mPasswordField.setError("5 or more characters required");
            valid = false;
        }else {
            mPasswordField.setError(null);
        }

        return valid;
    }


    public void backToSignIn(View view){
        finish();
    }

    public void signUpUser(View view){
        createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
    }
}

