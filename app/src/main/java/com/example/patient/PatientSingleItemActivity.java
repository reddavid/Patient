package com.example.patient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;

public class PatientSingleItemActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;

    private Button torolGomb;
    private Button frissitGomb;

    private String id;

    private EditText vezeteknev;
    private EditText keresztnev;
    private EditText telefonszam;
    private EditText szuletese;
    private EditText email;
    private EditText cime;
    private EditText csaladiallapot;
    private Spinner neme;

    private EditText contact_vezeteknev;
    private EditText contact_keresztnev;
    private EditText contact_telefonszam;
    private Spinner contact_neme;

    private TextView hozzatartozoSzoveg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_single_item);

        torolGomb = findViewById(R.id.torles);
        torolGomb.setBackgroundColor(Color.WHITE);
        torolGomb.setTextColor(Color.BLACK);

        frissitGomb = findViewById(R.id.frissites);
        frissitGomb.setBackgroundColor(Color.WHITE);
        frissitGomb.setTextColor(Color.BLACK);

        id = getIntent().getStringExtra("ID");
        String pvezeteknev = getIntent().getStringExtra("VEZETEKNEV");
        String pkeresztnev = getIntent().getStringExtra("KERESZTNEV");
        String pcime = getIntent().getStringExtra("CIME");
        String pszuletese = getIntent().getStringExtra("SZULETESE");
        String pcsaladiallapot = getIntent().getStringExtra("CSALADIALLAPOT");
        String ptelefonszam = getIntent().getStringExtra("TELEFONSZAM");
        String pemail = getIntent().getStringExtra("EMAIL");
        String pneme = getIntent().getStringExtra("NEME");


        vezeteknev = this.findViewById(R.id.vezeteknev);
        keresztnev = this.findViewById(R.id.keresztnev);
        cime = this.findViewById(R.id.cime);
        szuletese = this.findViewById(R.id.szuletese);
        csaladiallapot = this.findViewById(R.id.csaladiallapot);
        telefonszam = this.findViewById(R.id.telefonszam);
        email = this.findViewById(R.id.email);
        neme = this.findViewById(R.id.neme);


        vezeteknev.setText(pvezeteknev);
        keresztnev.setText(pkeresztnev);
        cime.setText(pcime);
        szuletese.setText(pszuletese);
        csaladiallapot.setText(pcsaladiallapot);
        telefonszam.setText(ptelefonszam);
        email.setText(pemail);


        String[] nemek = {"Férfi", "Nő", "Egyéb"};
        ArrayAdapter neme_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, nemek);
        neme.setAdapter(neme_adapter);

        int selectionPosition = neme_adapter.getPosition(pneme);
        neme.setSelection(selectionPosition);

        neme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        String cvezeteknev = getIntent().getStringExtra("KONTAKT_VEZETEKNEV");
        String ckeresztnev = getIntent().getStringExtra("KONTAKT_KERESZTNEV");
        String ctelefonszam = getIntent().getStringExtra("KONTAKT_TELEFONSZAM");
        String cneme = getIntent().getStringExtra("KONTAKT_NEME");

        contact_vezeteknev = this.findViewById(R.id.hozzatartozo_vezeteknev);
        contact_keresztnev = this.findViewById(R.id.hozzatartozo_keresztnev);
        contact_telefonszam = this.findViewById(R.id.hozzatartozo_telefonszam);
        contact_neme = this.findViewById(R.id.hozzatartozo_neme);
        hozzatartozoSzoveg = this.findViewById(R.id.hozzatartozo_adatai_szoveg);

        if (cvezeteknev.trim().isEmpty() || ckeresztnev.trim().isEmpty() || ctelefonszam.trim().isEmpty()) {

        } else {
            contact_vezeteknev.setVisibility(View.VISIBLE);
            contact_keresztnev.setVisibility(View.VISIBLE);
            contact_telefonszam.setVisibility(View.VISIBLE);
            contact_neme.setVisibility(View.VISIBLE);

            hozzatartozoSzoveg.setVisibility(View.VISIBLE);

            contact_vezeteknev.setText(cvezeteknev);
            contact_keresztnev.setText(ckeresztnev);
            contact_telefonszam.setText(ctelefonszam);
            contact_neme.setAdapter(neme_adapter);


            int selectionPositionContact = neme_adapter.getPosition(cneme);
            contact_neme.setSelection(selectionPositionContact);

            contact_neme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    ((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }


        final Button frissites = findViewById(R.id.frissites);
        frissites.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                firebaseFirestore = FirebaseFirestore.getInstance();

                String p1 = vezeteknev.getText().toString().trim();
                String p2 = keresztnev.getText().toString().trim();
                String p3 = telefonszam.getText().toString().trim();

                String p4 = neme.getSelectedItem().toString().trim();

                String p5 = szuletese.getText().toString().trim();
                String p6 = email.getText().toString().trim();
                String p7 = cime.getText().toString().trim();
                String p8 = csaladiallapot.getText().toString().trim();


                Patient p = new Patient();

                if (cvezeteknev.trim().isEmpty() || ckeresztnev.trim().isEmpty() || ctelefonszam.trim().isEmpty()) {
                    p = new Patient(Arrays.asList(p1,p2), p3, p4, p5, p7, p8, p6, null);
                } else {
                    String c1 = contact_vezeteknev.getText().toString().trim();
                    String c2 = contact_keresztnev.getText().toString().trim();
                    String c3 = contact_telefonszam.getText().toString().trim();
                    String c4 = contact_neme.getSelectedItem().toString().trim();

                    p = new Patient(Arrays.asList(p1,p2), p3, p4, p5, p7, p8, p6, new Contact(Arrays.asList(c1,c2), c3, c4));
                }

                firebaseFirestore.collection("Patients").document(id).set(p).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        finish();
                        Toast.makeText(PatientSingleItemActivity.this, "Patient frissítve!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        final Button torles = findViewById(R.id.torles);
        torles.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                firebaseFirestore = FirebaseFirestore.getInstance();

                firebaseFirestore.collection("Patients").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        finish();
                        Toast.makeText(PatientSingleItemActivity.this, "Patient törölve!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

    public void listPatient(View view) {
        Intent intent = new Intent(this, PatientListActivity.class);
        startActivity(intent);
    }
}