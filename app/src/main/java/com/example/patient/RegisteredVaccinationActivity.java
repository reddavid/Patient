package com.example.patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class RegisteredVaccinationActivity extends AppCompatActivity {

    private NotificationHelper notificationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notificationHelper = new NotificationHelper(this);
        setContentView(R.layout.activity_registered_vaccination);

        TextView jelentkezokSzama = (TextView) findViewById(R.id.jelentkezok_szama);

        FirebaseFirestore.getInstance().collection("Patients").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    notificationHelper.send("Az oltásra jelentkezők száma: " + task.getResult().size());
                    jelentkezokSzama.setText(String.valueOf(task.getResult().size()));
                } else {
                    System.out.println(("Hiba lekérdezéskor: " + task.getException()));
                }
            }
        });
    }

    public void mainMenu(View view) {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
}