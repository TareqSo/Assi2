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

public class RegistrationActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        Button btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String inputName = editTextName.getText().toString().trim();
        String inputAge = editTextAge.getText().toString().trim();
        String inputEmail = editTextEmail.getText().toString().trim();
        String inputPassword = editTextPassword.getText().toString().trim();

        // Validate input fields
        if (inputName.isEmpty() || inputAge.isEmpty() || inputEmail.isEmpty() || inputPassword.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new User object with the entered information
        User newUser = new User(inputName, inputPassword, Integer.parseInt(inputAge), inputEmail);

        // Save user data using SharedPreferences
        SharedPreferences.Editor editor = getSharedPreferences("UserData", MODE_PRIVATE).edit();
        editor.putString("name", newUser.getName());
        editor.putString("password", newUser.getPassword());
        editor.putInt("age", newUser.getAge());
        editor.putString("email", newUser.getEmail());
        editor.apply();

        // Display a success message
        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();

        // Optionally, you can navigate to another activity after registration
      //  Intent intent = new Intent(RegistrationActivity.this, AnotherActivity.class);
       // startActivity(intent);
        finish(); // Close the registration activity to prevent going back to it using the back button
    }
}
