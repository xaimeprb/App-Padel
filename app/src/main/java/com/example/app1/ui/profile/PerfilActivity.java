package com.example.app1.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import com.example.app1.R;
import com.example.app1.ui.auth.LoginActivity;
import com.example.app1.ui.base.BaseActivity;

/**
 * Actividad de perfil donde el residente puede ver su información personal y cerrar sesión.
 */

public class PerfilActivity extends BaseActivity {

    private TextView tvNombre, tvEmail;
    private Switch swNotificaciones;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_profile;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.nav_profile;
    }

    /**
     * Inicializa vistas, carga datos mock y configura el botón de logout.
     * @param savedInstanceState Estado de la instancia de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tvNombre = findViewById(R.id.tvNombre);
        tvEmail = findViewById(R.id.tvEmail);
        swNotificaciones = findViewById(R.id.swNotificaciones);
        Button btnLogout = findViewById(R.id.btnLogout);

        // TODO: Recoger de FireBase los datos del usuario

        tvNombre.setText("Juan Pérez");
        tvEmail.setText("juan.perez@urbanizacion.com");
        swNotificaciones.setChecked(true);

        btnLogout.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}