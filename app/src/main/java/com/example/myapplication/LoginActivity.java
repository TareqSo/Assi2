package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String inputUsername = editTextUsername.getText().toString().trim();
        String inputPassword = editTextPassword.getText().toString().trim();

        // Retrieve stored user data for comparison
        SharedPreferences preferences = getSharedPreferences("UserData", MODE_PRIVATE);
        String storedUsername = preferences.getString("name", "");
        String storedPassword = preferences.getString("password", "");

        // Check login credentials
        if (inputUsername.equals(storedUsername) && inputPassword.equals(storedPassword)) {
            Intent intent = new Intent(this, CatFactActivity.class);
            startActivity(intent);

        } else {
            // Display an error message for unsuccessful login
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }
}
