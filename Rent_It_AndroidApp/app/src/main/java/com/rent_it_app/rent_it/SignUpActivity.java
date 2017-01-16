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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Mimi on 1/10/17.
 */

public class SignUpActivity extends BaseActivity {

    private static final String TAG = "SignUp";
    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mFirstNameField;
    private EditText mLastNameField;
    private EditText mUserNameField;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private String userId = null;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mEmailField = (EditText) findViewById(R.id.field_email);
        mPasswordField = (EditText) findViewById(R.id.field_password);
        mFirstNameField = (EditText) findViewById(R.id.field_first_name);
        mLastNameField = (EditText) findViewById(R.id.field_last_name);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

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


                hideProgressDialog();




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

                            //Toast.makeText(SignUpActivity.this, "We have sent you a verification email", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                            startActivity(intent);

                            userId = task.getResult().getUser().getUid();
                            Log.d(TAG, "createUserWithEmail:onComplete:getUid():" + userId);
                            Toast.makeText(SignUpActivity.this, userId, Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "createUserWithEmail:onComplete:userId:" + userId);
                        }else if(task.getException() instanceof FirebaseAuthUserCollisionException){
                            Toast.makeText(SignUpActivity.this, "Email Address already registered", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(SignUpActivity.this, R.string.auth_failed, Toast.LENGTH_SHORT).show();
                        }

                        hideProgressDialog();
                    }
                });

        Log.d(TAG, "createAccount:end:userId:" + userId);
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

        //FirebaseUser user = mAuth.getCurrentUser();
        //String userId = user.getUid();
        //String userId = mDatabase.push().getKey();
        User userData = new User(mFirstNameField.getText().toString(), mLastNameField.getText().toString());

// pushing user to 'users' node using the userId
        //Toast.makeText(SignUpActivity.this, userId, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "your user id is:" + userId);
        //mDatabase.child(userId).setValue(userData);
    }
}

