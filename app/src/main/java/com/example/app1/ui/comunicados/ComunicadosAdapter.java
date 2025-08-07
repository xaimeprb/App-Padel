package com.example.app1.ui.comunicados;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.app1.R;
import com.example.app1.domain.model.Comunicado;
import java.util.ArrayList;
import java.util.List;

/**
 * Adaptador para el RecyclerView de comunicados.
 */
public class ComunicadosAdapter extends RecyclerView.Adapter<ComunicadosAdapter.VH> {
    private ArrayList<Comunicado> data = new ArrayList<>();

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Comunicado> items) {
        this.data.clear();
        this.data.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comunicado, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Comunicado c = data.get(position);
        holder.titulo.setText(c.getTitulo());
        holder.descripcion.setText(c.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * ViewHolder personalizado para el RecyclerView.
     */
    static class VH extends RecyclerView.ViewHolder {
        TextView titulo, descripcion;

        VH(@NonNull View v) {
            super(v);
            titulo      = v.findViewById(R.id.tvTitulo);
            descripcion = v.findViewById(R.id.tvDescripcion);
        }
    }
}