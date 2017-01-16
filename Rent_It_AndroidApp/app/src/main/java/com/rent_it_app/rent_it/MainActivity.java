package com.rent_it_app.rent_it;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends BaseActivity {

    private TextView myStatusText;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Button mSignOutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myStatusText = (TextView)findViewById(R.id.greetingMessage);
        mSignOutButton = (Button) findViewById(R.id.sign_out_button);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        myStatusText.setText(getString(R.string.firebase_status_fmt, user.getDisplayName()));

    }

    public void logOut(View view) {
        mAuth.signOut();
        Toast.makeText(MainActivity.this, "Successfully signed out", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
        //if you want to pass parameter
        //intent.putExtra("EXTRA_SESSION_ID", sessionId);
        startActivity(intent);
    }


}
