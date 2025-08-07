package com.example.app1.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.app1.R;
import com.example.app1.domain.model.Usuario;
import com.example.app1.session.SessionManager;
import com.example.app1.ui.home.InicioActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

/**
 * Pantalla de registro de un nuevo usuario.
 */
public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText tfNombre, tfApellido, tfPortal, tfPiso, tfNumeroTelefono, tfEmail, tfContrasena;
    private SessionManager session;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.register_activity);

        // Inicializar SessionManager
        session = SessionManager.getInstance(this);

        TextInputLayout tilNombre, tilApellido, tilPortal, tilPiso, tilNumeroTelefono, tilEmail, tilContrasena;

        // Vincular vistas
        tilNombre         = findViewById(R.id.nombreLayout);
        tilApellido       = findViewById(R.id.apellidoLayout);
        tilPortal         = findViewById(R.id.portalLayout);
        tilPiso           = findViewById(R.id.pisoLayout);
        tilNumeroTelefono = findViewById(R.id.telefonoLayout);
        tilEmail          = findViewById(R.id.emailLayout);
        tilContrasena     = findViewById(R.id.contrasenaLayout);

        tfNombre          = findViewById(R.id.nombreEditText);
        tfApellido        = findViewById(R.id.apellidosEditText);
        tfPortal          = findViewById(R.id.portalEditText);
        tfPiso            = findViewById(R.id.pisoEditText);
        tfNumeroTelefono  = findViewById(R.id.telefonoEditText);
        tfEmail           = findViewById(R.id.emailEditText);
        tfContrasena      = findViewById(R.id.contrasenaEditText);

        Button btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(v -> {
            // Obtener valores
            String nombre   = Objects.requireNonNull(tfNombre.getText()).toString().trim();
            String apellido = Objects.requireNonNull(tfApellido.getText()).toString().trim();
            String portal   = Objects.requireNonNull(tfPortal.getText()).toString().trim();
            String piso     = Objects.requireNonNull(tfPiso.getText()).toString().trim();
            String telefono = Objects.requireNonNull(tfNumeroTelefono.getText()).toString().trim();
            String email    = Objects.requireNonNull(tfEmail.getText()).toString().trim();
            String pwd      = Objects.requireNonNull(tfContrasena.getText()).toString().trim();

            // Validaciones básicas
            if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(apellido) || TextUtils.isEmpty(portal) || TextUtils.isEmpty(piso) || TextUtils.isEmpty(telefono) ||TextUtils.isEmpty(email) || TextUtils.isEmpty(pwd)) {

                Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_SHORT).show();

                return;

            }

            // Crear Usuario y guardar en sesión
            Usuario u = new Usuario();
            u.setNombre(nombre);
            u.setApellido(apellido);
            u.setPortal(portal);
            u.setPiso(piso);
            u.setEmail(email);
            u.setContrasena(pwd);

            // Nota: número de teléfono no se persiste en Usuario
            session.updateUser(u);

            // Abrir pantalla de inicio
            startActivity(new Intent(RegisterActivity.this, InicioActivity.class));
            finish();

        });
    }
}