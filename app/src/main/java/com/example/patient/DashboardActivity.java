package com.example.patient;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashboardActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void jelentkezokSzam(View view) {
        Intent intent = new Intent(this, RegisteredVaccinationActivity.class);
        startActivity(intent);
    }

    public void betegFelvetel(View view) {
            Intent intent = new Intent(this, PatientAddActivity.class);
            startActivity(intent);
    }


    public void betegekListazasa(View view) {
            Intent intent = new Intent(this, PatientListActivity.class);
            startActivity(intent);
    }
}