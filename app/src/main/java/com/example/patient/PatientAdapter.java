package com.example.patient;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

public class PatientAdapter extends FirestoreRecyclerAdapter<Patient, PatientAdapter.PatientHolder> {


    public PatientAdapter(@NonNull FirestoreRecyclerOptions<Patient> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PatientHolder holder, int position, @NonNull Patient model) {
        holder.vezeteknev.setText(model.getName().get(0));
        holder.keresztnev.setText(model.getName().get(1));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentSnapshot snapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());
                Intent intent = new Intent(v.getContext(), PatientSingleItemActivity.class);


                intent.putExtra("ID", snapshot.getId());
                intent.putExtra("VEZETEKNEV", model.getName().get(0));
                intent.putExtra("KERESZTNEV", model.getName().get(1));
                intent.putExtra("CIME", model.getAddress());
                intent.putExtra("SZULETESE", model.getBirthDate());
                intent.putExtra("CSALADIALLAPOT", model.getMaritalStatus());
                intent.putExtra("TELEFONSZAM", model.getTelecom());
                intent.putExtra("NEME", model.getGender());
                intent.putExtra("EMAIL", model.getEmail());

                if(model.getContact() != null) {
                    intent.putExtra("KONTAKT_VEZETEKNEV", model.getContact().getName().get(0));
                    intent.putExtra("KONTAKT_KERESZTNEV",  model.getContact().getName().get(1));
                    intent.putExtra("KONTAKT_TELEFONSZAM",  model.getContact().getTelecom());
                    intent.putExtra("KONTAKT_NEME",  model.getContact().getGender());
                } else {
                    intent.putExtra("KONTAKT_VEZETEKNEV", "");
                    intent.putExtra("KONTAKT_KERESZTNEV",  "");
                    intent.putExtra("KONTAKT_TELEFONSZAM",  "");
                    intent.putExtra("KONTAKT_NEME",  "");
                }



                v.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public PatientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_list_item, parent, false);
        return new PatientHolder(v);
    }

    class PatientHolder extends RecyclerView.ViewHolder {

        private TextView vezeteknev;
        private TextView keresztnev;

        public PatientHolder(@NonNull View itemView) {
            super(itemView);
            vezeteknev = itemView.findViewById(R.id.vezeteknev);
            keresztnev  = itemView.findViewById(R.id.keresztnev);

        }
    }
}
