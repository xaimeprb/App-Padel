package com.example.app1.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.app1.domain.entities.Comunicado;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio de comunicados: recupera todos los comunicados sin filtrar.
 */
public class ComunicadoRepository {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    /**
     * Devuelve un LiveData que emite la lista completa de comunicados.
     */
    public LiveData<List<Comunicado>> getComunicados() {

        MutableLiveData<List<Comunicado>> live = new MutableLiveData<>();

        db.collection("comunicados").addSnapshotListener((snap, e) -> {

                    if (e != null) return;

                    List<Comunicado> list = new ArrayList<>();

                    if (snap != null) {

                        for (DocumentSnapshot doc : snap.getDocuments()) {

                            list.add(doc.toObject(Comunicado.class));

                        }

                    }

            live.postValue(list);

        });

        return live;

    }
}