package com.example.app1.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.app1.domain.model.Comunicado;
import com.example.app1.util.Resource;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio de comunicados: recupera todos los comunicados y gestiona estados.
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
                    Comunicado c = doc.toObject(Comunicado.class);
                    if (c != null) list.add(c);
                }
            }
            live.postValue(list);
        });
        return live;
    }

    /**
     * Devuelve un LiveData que emite Resource<List<Comunicado>> con estados LOADING, SUCCESS y ERROR.
     */
    public LiveData<Resource<List<Comunicado>>> getComunicadosWithResource() {
        MutableLiveData<Resource<List<Comunicado>>> live = new MutableLiveData<>();
        // Emitir estado de carga inicial
        live.postValue(Resource.loading());
        db.collection("comunicados").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot snap, FirebaseFirestoreException e) {
                if (e != null) {
                    // Error al cargar
                    live.postValue(Resource.error(e.getMessage()));
                    return;
                }
                List<Comunicado> list = new ArrayList<>();
                if (snap != null) {
                    for (DocumentSnapshot doc : snap.getDocuments()) {
                        Comunicado c = doc.toObject(Comunicado.class);
                        if (c != null) list.add(c);
                    }
                }
                // Emitir éxito (incluso si vacío)
                live.postValue(Resource.success(list));
            }
        });
        return live;
    }
}
