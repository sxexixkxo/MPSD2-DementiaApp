package com.example.mpsd2groupassignment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText Email;
    private EditText Password;
    private Button Login;
    private EditText passwordresetemail;
    private ProgressBar progressBar;

    private FirebaseAuth auth;
    private ProgressDialog processDialog;
    LinearLayout register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        //init Views
        Email = (EditText) findViewById(R.id.emailSignIn);
        Password = (EditText) findViewById(R.id.password);
        Login = (Button) findViewById(R.id.Login);
        register = findViewById(R.id.login);
        passwordresetemail = findViewById(R.id.emailSignIn);
        progressBar = (ProgressBar) findViewById(R.id.progressbars);
        progressBar.setVisibility(View.GONE);
        processDialog = new ProgressDialog(this);

        //Firebase authentication
        auth = FirebaseAuth.getInstance();



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check email and password.Then sign in
                //Toast.makeText(LoginActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                validateAndSignIn(Email.getText().toString(), Password.getText().toString());

            }

        });


        //register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //goto register screen
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
            }
        });
    }

    public void resetpasword(){
        final String resetemail = passwordresetemail.getText().toString();

        //check email
        if (resetemail.isEmpty()) {
            passwordresetemail.setError("It's empty");
            passwordresetemail.requestFocus();
            return;
        }
        //show loading
        progressBar.setVisibility(View.VISIBLE);

        //send link on email
        auth.sendPasswordResetEmail(resetemail)

                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }

                        progressBar.setVisibility(View.GONE);
                    }
                });
    }




    public void validateAndSignIn(String userEmail, String userPassword){

        //show loading
        processDialog.setMessage("Please Wait...");
        processDialog.show();

        //Sign in user
        auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){





                    SharedPreferences preferences = getSharedPreferences("AUTHENTICATION_FILE_NAME", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("email",userEmail);
                    editor.putString("password",userPassword);
                    editor.commit();

                    UpdateToken();
                    processDialog.dismiss();


                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();


                    if(userEmail.equals("admin@admin.com")){
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();

                    }else{
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();

                    }

                }
                else{

                    Toast.makeText(LoginActivity.this,"Login Failed", Toast.LENGTH_SHORT).show();
                    processDialog.dismiss();


                }
            }
        });
    }


    private void UpdateToken(){
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        // String refreshToken= FirebaseInstanceId.getInstance().getToken();


    }

}
