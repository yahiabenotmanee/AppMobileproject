package com.monstertechno.btcauthpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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

public class SignupPage extends AppCompatActivity {

    Button btn_signup;
    EditText Etextuser,Etextemail,Etextpassword;

    String textuser,textemail,textpassword;

    private FirebaseAuth mAuth;
    String Emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        Etextuser=findViewById(R.id.editText_User);
        Etextemail=findViewById(R.id.editText_EmailAddress);
        Etextpassword=findViewById(R.id.edit_TextPassword);
        btn_signup=findViewById(R.id.register);

        textuser =Etextuser.getText().toString();
        textemail=Etextemail.getText().toString();;
        textpassword=Etextpassword.getText().toString();

        // Initialize Firebase Auth
                mAuth = FirebaseAuth.getInstance();

        ////////////////////////////////    status bar color    /////////////////////////////////////
        getWindow().setStatusBarColor(ContextCompat.getColor(SignupPage.this,R.color.black));

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check conditions
                if (!TextUtils.isEmpty(textuser)){
                    if (!TextUtils.isEmpty(textemail)){
                        if (textemail.matches(Emailpattern)){
                                    if (!TextUtils.isEmpty(textpassword)){
                                        // Call methode
                                        SignUpUser();
//
                                    }else{Etextpassword.setError(" Password can't be empty !");}


                        }else{ Etextemail.setError(" Enter a valid email Address "); }

                    }else{ Etextemail.setError(" email can't be empty !"); }

                }else{ Etextuser.setError(" Full name can't be empty !"); }

            }
        });


    }
    //Siggnup user methode
    private void SignUpUser() {
        btn_signup.setVisibility(View.INVISIBLE);
        mAuth.createUserWithEmailAndPassword(textemail,textpassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(SignupPage.this, "SignUp successfuly ", Toast.LENGTH_SHORT).show();

                Intent intent =new Intent(SignupPage.this,LoginPage.class);
                startActivity(intent);
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignupPage.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                btn_signup.setVisibility(View.VISIBLE);
            }
        });

    }

}