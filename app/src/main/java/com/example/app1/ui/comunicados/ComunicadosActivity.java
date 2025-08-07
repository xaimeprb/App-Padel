package com.example.app1.ui.comunicados;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.ViewModelProvider;

import com.example.app1.R;
import com.example.app1.domain.model.Comunicado;
import com.example.app1.ui.base.BaseActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Actividad que muestra la lista de comunicados de la urbanización.
 * Implementa estados de carga y vista vacía.
 */
public class ComunicadosActivity extends BaseActivity {

    private RecyclerView rvComunicados;
    private ComunicadosAdapter adapter;
    private ProgressBar progressBar;
    private TextView tvEmpty;
    private ComunicadosViewModel viewModel;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_comunicados;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.nav_comunicados;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Referencia a las vistas
        rvComunicados = findViewById(R.id.rv_comunicados);
        progressBar   = findViewById(R.id.progressBar);
        tvEmpty       = findViewById(R.id.tvEmpty);

        // Configura RecyclerView
        rvComunicados.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ComunicadosAdapter();
        rvComunicados.setAdapter(adapter);

        // Inicializa ViewModel
        viewModel = new ViewModelProvider(this)
                .get(ComunicadosViewModel.class);

        // Observa LiveData de comunicados con estados Resource
        viewModel.comunicadosResource.observe(this, res -> {
            switch (res.status) {
                case LOADING:
                    progressBar.setVisibility(View.VISIBLE);
                    tvEmpty.setVisibility(View.GONE);
                    rvComunicados.setVisibility(View.GONE);
                    break;

                case SUCCESS:
                    progressBar.setVisibility(View.GONE);
                    List<Comunicado> data = res.data;
                    if (data == null || data.isEmpty()) {
                        tvEmpty.setText(R.string.no_comunicados);
                        tvEmpty.setVisibility(View.VISIBLE);
                        rvComunicados.setVisibility(View.GONE);
                    } else {
                        tvEmpty.setVisibility(View.GONE);
                        rvComunicados.setVisibility(View.VISIBLE);
                        adapter.setData(new ArrayList<>(data));
                    }
                    break;

                case ERROR:
                    progressBar.setVisibility(View.GONE);
                    Snackbar.make(
                            findViewById(R.id.main),
                            res.message != null
                                    ? res.message
                                    : getString(R.string.error_cargando_comunicados),
                            Snackbar.LENGTH_LONG
                    ).show();
                    break;
            }
        });
    }
}
