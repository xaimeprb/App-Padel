package com.example.app1.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.MenuItem;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app1.R;
import com.example.app1.ui.home.InicioActivity;
import com.example.app1.ui.comunicados.ComunicadosActivity;
import com.example.app1.ui.profile.PerfilActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public abstract class BaseActivity extends AppCompatActivity {

    protected BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        // Inflar layout hijo
        ViewGroup fl = findViewById(R.id.flContent);
        LayoutInflater.from(this).inflate(getLayoutResourceId(), fl, true);

        // Configurar BottomNavigationView
        bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_home && !(BaseActivity.this instanceof InicioActivity)) {
                    startActivity(new Intent(BaseActivity.this, InicioActivity.class));
                    finish();
                    return true;
                }
                if (id == R.id.nav_comunicados && !(BaseActivity.this instanceof ComunicadosActivity)) {
                    startActivity(new Intent(BaseActivity.this, ComunicadosActivity.class));
                    finish();
                    return true;
                }
                if (id == R.id.nav_profile && !(BaseActivity.this instanceof PerfilActivity)) {
                    startActivity(new Intent(BaseActivity.this, PerfilActivity.class));
                    finish();
                    return true;
                }
                return false;
            }
        });

        // Seleccionar el ítem activo
        bottomNav.setSelectedItemId(getNavigationMenuItemId());
    }

    /** Cada Activity hija devuelve su layout */
    @LayoutRes
    protected abstract int getLayoutResourceId();

    /** Cada Activity hija indica su ítem de menú */
    protected abstract int getNavigationMenuItemId();
}
