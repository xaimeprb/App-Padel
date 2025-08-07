package com.example.app1.ui.comunicados;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.app1.data.ComunicadoRepository;
import com.example.app1.domain.model.Comunicado;
import com.example.app1.util.Resource;

import java.util.List;

/**
 * ViewModel para ComunicadosActivity, expone un Resource que gestiona Loading/Success/Error.
 */
public class ComunicadosViewModel extends ViewModel {
    private final ComunicadoRepository repo = new ComunicadoRepository();

    /**
     * LiveData que emite Resource<List<Comunicado>>:
     * - LOADING mientras se carga,
     * - SUCCESS con la lista,
     * - ERROR con el mensaje.
     */
    public final LiveData<Resource<List<Comunicado>>> comunicadosResource = repo.getComunicadosWithResource();
}