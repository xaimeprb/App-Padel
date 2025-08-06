package com.example.app1.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.app1.data.ReservaRepository;
import com.example.app1.domain.entities.Reserva;

import java.util.List;

public class InicioViewModel extends ViewModel {
    private final ReservaRepository repo = new ReservaRepository();
    private LiveData<List<Reserva>> reservas = repo.getReservas();

    /** @return LiveData con todas las reservas. */
    public LiveData<List<Reserva>> getReservas() {
        return reservas;
    }
}