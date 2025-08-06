package com.example.app1.ui.auth;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.Toast;

import com.example.app1.ui.home.InicioActivity;
import com.example.app1.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout emailLayout, passwordLayout;
    private TextInputEditText emailInput, passwordInput;
    private Button loginButton, registerButton, botonReservas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        botonReservas = findViewById(R.id.botonReservas);

        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, Register_Activity.class);

            startActivity(intent);

        });

        loginButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {

                Toast.makeText(LoginActivity.this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(LoginActivity.this, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show();

            }

        });

        botonReservas.setOnClickListener(v -> {

            Intent intent = new Intent(LoginActivity.this, InicioActivity.class);
            startActivity(intent);

        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();

        if (intent != null) {

            String email = intent.getStringExtra("email");
            String password = intent.getStringExtra("contrasena");

            emailInput.setText(email);
            passwordInput.setText(password);

        }

    }
}