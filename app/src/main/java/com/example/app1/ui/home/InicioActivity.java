package com.example.app1.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app1.R;
import com.example.app1.domain.model.Reserva;
import com.example.app1.ui.base.BaseActivity;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Pantalla de inicio que muestra la lista de reservas obtenidas del ViewModel.
 * Contiene un ProgressBar que se muestra mientras se cargan los datos.
 * Si no hay reservas, se muestra un mensaje vacío.
 * Si hay reservas, se muestra la lista de reservas.
 * Si hay un error, se muestra un mensaje de error.
 */
public class InicioActivity extends BaseActivity {

    private RecyclerView rvReservas;
    private ReservasAdapter adaptador;
    private CircularProgressIndicator progressBar;
    private MaterialTextView tvEmpty;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_inicio;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.nav_home;
    }

    /**
     * Método que se llama cuando se crea la actividad.
     * @param savedInstanceState Estado previo si la Activity fue recreada.
     */
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Configuración de EdgeToEdge
        EdgeToEdge.enable(this);
        ConstraintLayout root = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar vistas de estado
        progressBar = findViewById(R.id.progressBar);
        tvEmpty     = findViewById(R.id.tvEmpty);

        // RecyclerView setup
        rvReservas = findViewById(R.id.rv_reservas);
        rvReservas.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adaptador = new ReservasAdapter();
        rvReservas.setAdapter(adaptador);

        // ViewModel initialization
        InicioViewModel viewModel = new ViewModelProvider(this).get(InicioViewModel.class);

        /**
         * Observador de cambios en la lista de reservas.
         * Actualiza la vista según el estado de la petición.
         * @param res Estado de la petición.
         */
        viewModel.reservasResource.observe(this, res -> {

            switch (res.status) {

                case LOADING:

                    progressBar.setVisibility(View.VISIBLE);
                    tvEmpty.setVisibility(View.GONE);
                    rvReservas.setVisibility(View.GONE);

                    break;
                case SUCCESS:

                    progressBar.setVisibility(View.GONE);
                    List<Reserva> data = res.data;

                    if (data == null || data.isEmpty()) {

                        tvEmpty.setText(R.string.no_reservas);
                        tvEmpty.setVisibility(View.VISIBLE);
                        rvReservas.setVisibility(View.GONE);

                    } else {

                        tvEmpty.setVisibility(View.GONE);
                        rvReservas.setVisibility(View.VISIBLE);
                        adaptador.setData(new ArrayList<>(data));

                    }

                    break;
                case ERROR:

                    progressBar.setVisibility(View.GONE);
                    Snackbar.make(root, res.message != null ? res.message : getString(R.string.error_cargando_reservas), Snackbar.LENGTH_LONG).show();

                    break;
            }
        });
    }
}