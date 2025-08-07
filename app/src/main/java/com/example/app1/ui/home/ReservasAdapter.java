package com.example.app1.ui.home;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app1.R;
import com.example.app1.domain.model.Reserva;

import java.util.ArrayList;

public class ReservasAdapter extends RecyclerView.Adapter<ReservasAdapter.ReservasViewholder> {

    private ArrayList<Reserva> reservas = new ArrayList<>();

    @NonNull
    @Override
    public ReservasViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReservasViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reservas, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReservasViewholder holder, int position) {

        if (reservas.get(position).getEstado() == 1) {
            String cancelar = "Cancelar";
            holder. btn_reserva.setText(cancelar);
            holder.btn_reserva.setBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.red, null));
        } else if (reservas.get(position).getEstado() == 2){

            holder.btn_reserva.setBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.green, null));

        }

        holder.tv_hora.setText(reservas.get(position).getHorario());
    }

    public void setData(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return reservas.size();
    }

    class ReservasViewholder extends RecyclerView.ViewHolder {

        private TextView tv_hora;
        private Button btn_reserva;

        @SuppressLint("ResourceAsColor")
        public ReservasViewholder(@NonNull View itemView) {
            super(itemView);
            tv_hora = itemView.findViewById(R.id.tv_item_hora);
            btn_reserva = itemView.findViewById(R.id.btn_item_reserva);

            btn_reserva.setOnClickListener(v -> {

                int position = getAdapterPosition();
                Reserva reservaSeleccionada = reservas.get(position);

                if (reservaSeleccionada.getEstado() == 0) {
                    reservaSeleccionada.setEstado(1);
                    String cancelar = "Cancelar";
                    btn_reserva.setText(cancelar);
                    btn_reserva.setBackgroundColor(itemView.getContext().getResources().getColor(R.color.red, null));
                } else {
                    reservaSeleccionada.setEstado(0);
                    String cancelar = "Reservar";
                    btn_reserva.setText(cancelar);
                    btn_reserva.setBackgroundColor(itemView.getContext().getResources().getColor(R.color.green, null));
                }
            });
        }

    }
}
