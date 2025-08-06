package com.example.app1.ui.home;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app1.R;
import com.example.app1.domain.entities.Reserva;
import com.example.app1.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Pantalla de inicio que muestra la lista de reservas obtenidas del ViewModel.
 */
public class InicioActivity extends BaseActivity {

    private RecyclerView rvReservas;
    private ReservasAdapter adaptador; // Asegúrate de usar el mismo adapter que tus datos
    private InicioViewModel viewModel;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_inicio;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.nav_home;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Edge-to-edge layout handling
        EdgeToEdge.enable(this);
        ConstraintLayout root = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // RecyclerView setup
        rvReservas = findViewById(R.id.rv_reservas);
        rvReservas.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adaptador = new ReservasAdapter();
        rvReservas.setAdapter(adaptador);

        // ViewModel initialization
        viewModel = new ViewModelProvider(this).get(InicioViewModel.class);

        // Observa LiveData de reservas
        viewModel.getReservas().observe(this, new Observer<List<Reserva>>() {
            @Override
            public void onChanged(List<Reserva> lista) {
                // Convierte a ArrayList si tu adapter lo necesita
                adaptador.setData(new ArrayList<>(lista));
            }
        });
    }
}