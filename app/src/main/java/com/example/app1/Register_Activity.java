package com.example.app1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Register_Activity extends AppCompatActivity {

    private TextInputLayout tilNombre, tilApellido, tilPortal, tilPiso, tilNumeroTelefono, tilEmail, tilContrasena;
    private TextInputEditText tfNombre, tfApellido, tfPortal, tfPiso, tfNumeroTelefono, tfEmail, tfContrasena;
    private Button btnRegistrar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        tilNombre = findViewById(R.id.nombreLayout);
        tilApellido = findViewById(R.id.apellidoLayout);
        tilPortal = findViewById(R.id.portalLayout);
        tilPiso = findViewById(R.id.pisoLayout);
        tilNumeroTelefono = findViewById(R.id.telefonoLayout);
        tilEmail = findViewById(R.id.emailLayout);
        tilContrasena = findViewById(R.id.contrasenaLayout);

        tfNombre = findViewById(R.id.nombreEditText);
        tfApellido = findViewById(R.id.apellidosEditText);
        tfPortal = findViewById(R.id.portalEditText);
        tfPiso = findViewById(R.id.pisoEditText);
        tfNumeroTelefono = findViewById(R.id.telefonoEditText);
        tfEmail = findViewById(R.id.emailEditText);
        tfContrasena = findViewById(R.id.contrasenaEditText);

        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(v -> {

            String nombre = tfNombre.getText().toString().trim();
            String apellido = tfApellido.getText().toString().trim();
            String portal = tfPortal.getText().toString().trim();
            String piso = tfPiso.getText().toString().trim();
            String numeroTelefono = tfNumeroTelefono.getText().toString().trim();
            String email = tfEmail.getText().toString().trim();
            String contrasena = tfContrasena.getText().toString().trim();

            // Validaciones

            // Fichero Shared Preferences
            SharedPreferences sp = getSharedPreferences("MiPrimerArchivo",MODE_PRIVATE);

            SharedPreferences.Editor editor = sp.edit();

            editor.putString("nombre", nombre);
            editor.putString("apellido", apellido);
            editor.putString("portal", portal);
            editor.putString("piso", piso);
            editor.putString("numeroTelefono", numeroTelefono);
            editor.putString("email", email);
            editor.putString("contraseña", contrasena);

            editor.apply();

            // Enviar a la otra Activity

            Intent intent = new Intent(Register_Activity.this, MainActivity.class);

            intent.putExtra("email", email);
            intent.putExtra("contrasena", contrasena);
            startActivity(intent);

        });

    }
}
