package com.example.app1.ui.home;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app1.R;
import com.example.app1.domain.entities.Reserva;
import com.example.app1.ui.base.BaseActivity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class InicioActivity extends BaseActivity {

    private RecyclerView rv_reservas;


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_inicio;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.nav_home;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rv_reservas = findViewById(R.id.rv_reservas);
        rv_reservas.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ReservasAdapter adaptador = new ReservasAdapter();
        rv_reservas.setAdapter(adaptador);

        adaptador.setData(generateReservas());

    }

    /**
     * Genera reservas de prueba para la fecha seleccionada
     */
    public ArrayList<Reserva> generateReservas() {

        ArrayList<Reserva> reservas = new ArrayList<>();

        reservas.add(new Reserva(1, LocalDateTime.now(), "10:00-11:30", 1, 1));
        reservas.add(new Reserva(1, LocalDateTime.now(), "11:30-13:00", 0, 1));
        reservas.add(new Reserva(1, LocalDateTime.now(), "13:00-14:30", 1, 1));
        reservas.add(new Reserva(1, LocalDateTime.now(), "14:30-16:00", 0, 1));
        reservas.add(new Reserva(1, LocalDateTime.now(), "16:00-17:30", 1, 1));
        reservas.add(new Reserva(1, LocalDateTime.now(), "17:30-19:00", 0, 1));
        reservas.add(new Reserva(1, LocalDateTime.now(), "19:00-20:30", 1, 1));
        reservas.add(new Reserva(1, LocalDateTime.now(), "20:30-22:00", 0, 1));


        return reservas;
    }
}