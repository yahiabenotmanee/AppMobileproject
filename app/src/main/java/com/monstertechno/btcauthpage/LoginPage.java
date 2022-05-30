package com.monstertechno.btcauthpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {

    Button login;
    EditText email,pass;
    String Emailtext,passwordtxt;

    private FirebaseAuth mAuth;
    String Emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        login=findViewById(R.id.login);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.password);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Emailtext=email.getText().toString().trim();
                passwordtxt=pass.getText().toString().trim();

                if (!TextUtils.isEmpty(Emailtext)){
                    if (Emailtext.matches(Emailpattern)){
                        if (!TextUtils.isEmpty(passwordtxt)){

                            SignInUser();


                        }else {pass.setError(" Password  can't be empty !");}

                    }else {email.setError(" Enter a valid address please  !");}

                }else {email.setError(" Email can't be empty !");}

            }
        });
    }
    private void SignInUser() {

        login.setVisibility(View.INVISIBLE);
        mAuth.signInWithEmailAndPassword(Emailtext,passwordtxt).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(LoginPage.this, "Login Successfuly ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginPage.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(LoginPage.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
              ;
                login.setVisibility(View.VISIBLE);
            }
        });
    }

}