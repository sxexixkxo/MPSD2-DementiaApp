package com.example.mpsd2groupassignment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {


    private EditText editTextEmail, editTextPassword,editTextcPassword;
    public Button UserRegisterBtn;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    LinearLayout login;

    EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        //init Views
        name = findViewById(R.id.name);

        editTextEmail = findViewById(R.id.emailRegister);
        editTextPassword = findViewById(R.id.passwordRegister);
        editTextcPassword= findViewById(R.id.confirmPassword);
        UserRegisterBtn= findViewById(R.id.button_register);
        progressBar = findViewById(R.id.progressbars);
        progressBar.setVisibility(View.GONE);
        login = findViewById(R.id.login);
        //Firebase authentication
        mAuth = FirebaseAuth.getInstance();


        UserRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check email and password.Then sign in
                //startActivity(new Intent(getApplicationContext(),MainActivity.class));
                //Toast.makeText(RegistrationActivity.this, "Server Error", Toast.LENGTH_SHORT).show();

                registerUser();
            }
        });
        //Login

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Login screen

                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

//        if (mAuth.getCurrentUser() != null) {
//
//        }
    }


    private void registerUser() {

        // get text from fields
        final String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString().trim();
        String cpassword = editTextcPassword.getText().toString().trim();
        //check email
        if (email.isEmpty()) {
            editTextEmail.setError("It's empty");
            editTextEmail.requestFocus();
            return;
        }



        //check email. Is it valid
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Not a valid email");
            editTextEmail.requestFocus();
            return;
        }

        // check password
        if (password.isEmpty()) {
            editTextPassword.setError("Its empty");
            editTextPassword.requestFocus();
            return;
        }

        //Check password lenght
        if (password.length() < 6) {
            editTextPassword.setError("Less length");
            editTextPassword.requestFocus();
            return;
        }
        //match password
        if(!password.equals(cpassword)){
            editTextcPassword.setError("Password Donot Match");
            editTextcPassword.requestFocus();
            return;
        }




        //show loading
        progressBar.setVisibility(View.VISIBLE);

        //create user in database
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {


                            //add email in user model class
                            FirebaseUser usernameinfirebase = mAuth.getCurrentUser();

                            //get registered email from database
                            String UserID=usernameinfirebase.getEmail();
                            String resultemail = UserID.replace(".","");
                            FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("name").setValue(name.getText().toString());
                            FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("email").setValue(editTextEmail.getText().toString());;
                            FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).child("image").setValue("No").addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {


                                        SharedPreferences preferences = getSharedPreferences("AUTHENTICATION_FILE_NAME", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = preferences.edit();
                                        editor.putString("email",email);
                                        editor.putString("password",password);
                                        editor.commit();



                                        //move to main screen
                                        Toast.makeText(RegistrationActivity.this, "Registration Success", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                                        finish();


                                    }
                                }
                            });

                        } else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }









}
