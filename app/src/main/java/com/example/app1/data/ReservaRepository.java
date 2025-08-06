package com.example.app1.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.app1.domain.entities.Reserva;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio de reservas: recupera todas las reservas sin filtrar.
 */
public class ReservaRepository {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    /**
     * Devuelve un LiveData que emite la lista completa de reservas.
     * @return LiveData con la lista de reservas.
     */
    public LiveData<List<Reserva>> getReservas() {

        MutableLiveData<List<Reserva>> live = new MutableLiveData<>();

        db.collection("reservas").addSnapshotListener((snap, e) -> {

                    if (e != null) return;

                    List<Reserva> list = new ArrayList<>();

                    for (DocumentSnapshot doc : snap.getDocuments()) {

                        list.add(doc.toObject(Reserva.class));

                    }

                    live.postValue(list);

                });

        return live;
    }
}