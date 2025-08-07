package com.example.app1.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app1.data.ReservaRepository;
import com.example.app1.domain.model.Reserva;
import com.example.app1.util.Resource;

import java.util.List;

public class InicioViewModel extends ViewModel {
    private final ReservaRepository repo = new ReservaRepository();
    public LiveData<Resource<List<Reserva>>> reservasResource = repo.getReservas();
    private MutableLiveData<Resource<List<Reserva>>> reservas = repo.getReservas();

    /** @return LiveData con todas las reservas. */
    public MutableLiveData<Resource<List<Reserva>>> getReservas() {
        return reservas;
    }
}