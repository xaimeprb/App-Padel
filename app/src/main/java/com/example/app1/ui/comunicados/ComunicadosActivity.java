package com.example.app1.ui.comunicados;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.app1.R;
import com.example.app1.domain.entities.Comunicado;
import com.example.app1.ui.base.BaseActivity;
import java.util.ArrayList;

public class ComunicadosActivity extends BaseActivity {

    private RecyclerView rvComunicados;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_comunicados;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.nav_comunicados;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rvComunicados = findViewById(R.id.rv_comunicados);
        rvComunicados.setLayoutManager(new LinearLayoutManager(this));
        ComunicadosAdapter adapter = new ComunicadosAdapter();
        rvComunicados.setAdapter(adapter);
        adapter.setData(generateMockComunicados());
    }

    private ArrayList<Comunicado> generateMockComunicados() {
        ArrayList<Comunicado> list = new ArrayList<>();
        list.add(new Comunicado("Corte de agua", "Mañana de 9h a 12h se interrumpirá el suministro."));
        list.add(new Comunicado("Barbacoa vecinal", "Domingo 10 de agosto a las 13h en la zona común."));
        list.add(new Comunicado("Limpieza piscina", "Viernes por la mañana en la piscina comunitaria."));
        return list;
    }
}
