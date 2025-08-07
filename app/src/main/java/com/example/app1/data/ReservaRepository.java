package com.example.app1.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.app1.domain.model.Reserva;
import com.example.app1.util.Resource;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Repositorio de reservas: recupera todas las reservas sin filtrar.
 */
public class ReservaRepository {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    /**
     * Devuelve un LiveData que emite la lista completa de reservas.
     * @return Lista de reservas.
     */
    public MutableLiveData<Resource<List<Reserva>>> getReservas() {

        MutableLiveData<Resource<List<Reserva>>> live = new MutableLiveData<>();

        live.postValue(Resource.loading());

        /**
         * Escucha los cambios en la colección "reservas" de Firestore.
         * Si hay un error, emite un valor de error.
         * Si no hay reservas, emite una lista vacía.
         */
        db.collection("reservas").addSnapshotListener((snap, e) -> {

                    if (e != null) {

                        live.postValue(Resource.error(e.getMessage()));

                        return;

                    }

                    if (snap == null || snap.getDocuments().isEmpty()) {

                        live.postValue(Resource.success(Collections.emptyList()));

                        return;

                    }

                    List<Reserva> list = new ArrayList<>();

                    for (DocumentSnapshot doc : snap.getDocuments()) {

                        Reserva reserva = doc.toObject(Reserva.class);

                        if (reserva != null){

                            list.add(reserva);

                        }

                    }

                    live.postValue(Resource.success(list));

                });

        return live;

    }
}