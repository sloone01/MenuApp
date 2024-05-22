package com.example.myapplicationhaha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginButton;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        username.setText("salim");
        password.setText("Omani@123");
        textView = findViewById(R.id.signupText);
        loginButton = findViewById(R.id.loginButton);
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        textView.setOnClickListener(v->startActivity(new Intent(this, Register.class)));
        loginButton.setOnClickListener(view -> {
            firebaseFirestore.collection("User")
                    .whereEqualTo("username", username.getText().toString().trim())
                    .whereEqualTo("password",password.getText().toString().trim())
                    .get().addOnCompleteListener(task -> {
                        User user = new User();
                        int i = 0;
                        for (QueryDocumentSnapshot snapshot : task.getResult()) {
                            user = snapshot.toObject(User.class);
                            i++;
                        }
                        if (i == 0) {
                            Toast.makeText(MainActivity.this, "Wrong Credentials",Toast.LENGTH_SHORT).show();
                        } else {
                            SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
                            editor.putString("username", user.getUsername());
                            editor.apply();
                            Toast.makeText(MainActivity.this, "Logged in Successfully",Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(this, ViewItemsList.class));
                        }
                    });
        });
    }
}