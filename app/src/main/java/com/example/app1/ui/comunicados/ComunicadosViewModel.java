package com.example.app1.ui.comunicados;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.app1.data.ComunicadoRepository;
import com.example.app1.domain.entities.Comunicado;

import java.util.List;

public class ComunicadosViewModel extends ViewModel {
    private final ComunicadoRepository repo = new ComunicadoRepository();
    private LiveData<List<Comunicado>> comunicados = repo.getComunicados();

    /** @return LiveData con todos los comunicados. */
    public LiveData<List<Comunicado>> getComunicados() {
        return comunicados;
    }
}
