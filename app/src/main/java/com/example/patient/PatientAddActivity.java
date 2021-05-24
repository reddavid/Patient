package com.example.patient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;

public class PatientAddActivity extends AppCompatActivity {

    private Button felvetelGomb;
    private String[] neme = {"Férfi", "Nő", "Egyéb"};

    private EditText add_vezeteknev;
    private EditText add_keresztnev;
    private EditText add_telefonszam;
    private Spinner add_neme;
    private EditText add_szuletese;
    private EditText add_email;
    private EditText add_cime;
    private EditText add_csaladiallapot;

    private CheckBox hozzatartozoCheckBox;

    private EditText add_contact_vezeteknev;
    private EditText add_contact_keresztnev;
    private EditText add_contact_telefonszam;
    private Spinner add_contact_neme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_add);

        felvetelGomb = findViewById(R.id.felvetelGomb);
        felvetelGomb.setBackgroundColor(Color.WHITE);
        felvetelGomb.setTextColor(Color.BLACK);


        add_vezeteknev = findViewById(R.id.add_vezeteknev);
        add_keresztnev = findViewById(R.id.add_keresztnev);
        add_telefonszam = findViewById(R.id.add_telefonszam);

        add_neme = findViewById(R.id.add_neme);
        ArrayAdapter add_neme_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, neme);
        add_neme_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        add_neme.setAdapter(add_neme_adapter);

        add_neme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

         add_szuletese = findViewById(R.id.add_szuletese);
         add_email = findViewById(R.id.add_email);
         add_cime = findViewById(R.id.add_cime);
         add_csaladiallapot = findViewById(R.id.add_csaladiallapot);


        hozzatartozoCheckBox = findViewById(R.id.hozzatartozoCheckBox);

        add_contact_vezeteknev = findViewById(R.id.add_contatc_vezeteknev);
        add_contact_keresztnev = findViewById(R.id.add_contact_keresztnev);
        add_contact_telefonszam = findViewById(R.id.add_contact_telefonszam);

        add_contact_neme = findViewById(R.id.add_contact_neme);
        ArrayAdapter add_contact_neme_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, neme);
        add_contact_neme_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        add_contact_neme.setAdapter(add_contact_neme_adapter);
        add_contact_neme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        hozzatartozoCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((hozzatartozoCheckBox)).isChecked()){
                    add_contact_vezeteknev.setVisibility(View.VISIBLE);
                    add_contact_keresztnev.setVisibility(View.VISIBLE);
                    add_contact_telefonszam.setVisibility(View.VISIBLE);
                    add_contact_neme.setVisibility(View.VISIBLE);
                } else {
                    add_contact_vezeteknev.setVisibility(View.GONE);
                    add_contact_keresztnev.setVisibility(View.GONE);
                    add_contact_telefonszam.setVisibility(View.GONE);
                    add_contact_neme.setVisibility(View.GONE);
                }
            }
        });

    }

    public void savePatient(View view) {
        String vezeteknev = add_vezeteknev.getText().toString().trim();
        String keresztnev = add_keresztnev.getText().toString().trim();
        String telefonszam = add_telefonszam.getText().toString().trim();

        String neme = add_neme.getSelectedItem().toString().trim();

        String szuletese = add_szuletese.getText().toString().trim();
        String email = add_email.getText().toString().trim();
        String cime = add_cime.getText().toString().trim();
        String csaladiallapot = add_csaladiallapot.getText().toString().trim();

        String contact_vezeteknev = add_contact_vezeteknev.getText().toString().trim();
        String contact_keresztnev = add_contact_keresztnev.getText().toString().trim();
        String contact_telefonszam = add_contact_telefonszam.getText().toString().trim();
        String contact_neme = add_contact_neme.getSelectedItem().toString().trim();

        if (vezeteknev.trim().isEmpty() || keresztnev.trim().isEmpty() || email.trim().isEmpty()) {
            Toast.makeText(this, "Kérem töltsön ki minden mezőt!", Toast.LENGTH_LONG).show();
            return;
        }

        CollectionReference patientRef = FirebaseFirestore.getInstance().collection("Patients");
        // ADD

        if(((hozzatartozoCheckBox)).isChecked()) {
            patientRef.add(new Patient(Arrays.asList(vezeteknev, keresztnev), telefonszam, neme, szuletese, cime, csaladiallapot, email, new Contact(Arrays.asList(contact_vezeteknev, contact_keresztnev), contact_telefonszam, contact_neme)));
        } else {
            patientRef.add(new Patient(Arrays.asList(vezeteknev, keresztnev), telefonszam, neme, szuletese, cime, csaladiallapot, email, null));
        }
        Toast.makeText(this, "Új beteg felvéve!", Toast.LENGTH_LONG).show();
        finish();
    }

    public void mainMenu(View view) {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
}