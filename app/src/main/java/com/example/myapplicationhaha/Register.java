package com.example.myapplicationhaha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class Register extends AppCompatActivity {

    EditText username,email,name,password,re_password;
    TextView textView;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.username);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        re_password = findViewById(R.id.re_password);

        textView = findViewById(R.id.signupText);
        textView.setOnClickListener(v->startActivity(new Intent(this, MainActivity.class)));
        register = findViewById(R.id.register);
        register.setOnClickListener(v->registerUser());


    }

    private void registerUser() {

        if(name.getText().toString().isEmpty() || username.getText().toString().isEmpty() || email.getText().toString().isEmpty() ||
                password.getText().toString().isEmpty() || re_password.getText().toString().isEmpty())
        {
            Toast.makeText(Register.this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!password.getText().toString().equals(re_password.getText().toString()))
        {
            Toast.makeText(Register.this, "Passwords are not match", Toast.LENGTH_SHORT).show();
            return;
        }
        User user = new User(username.getText().toString().trim(),name.getText().toString().trim(),username.getText().toString().trim(),email.getText().toString().trim(),password.getText().toString());
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("User")
                .document(user.getEmail())
                .set(user)
                .addOnSuccessListener(v->{
                    Toast.makeText(Register.this,"Signed Up Successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this,MainActivity.class));
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }
}