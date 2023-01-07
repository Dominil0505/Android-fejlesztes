package com.example.currency_exchanger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentManager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        startService(new Intent(getApplication(), CurrencyServices.class));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        ImageButton btnHome = findViewById(R.id.homeImageButton);
        ImageButton btnSettings = findViewById(R.id.settingsImageButton);
        ImageButton btnActivity = findViewById(R.id.activityImageButton);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, HomeFragment.class, null)
                        .setReorderingAllowed(true).addToBackStack("name").commit();
                btnHome.setEnabled(false);
                btnSettings.setEnabled(true);
                btnActivity.setEnabled(true);
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, SettingsFragment.class, null)
                        .setReorderingAllowed(true).addToBackStack("name").commit();
                btnHome.setEnabled(true);
                btnSettings.setEnabled(false);
                btnActivity.setEnabled(true);
            }
        });

        btnActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, ActivityFragment.class, null)
                        .setReorderingAllowed(true).addToBackStack("name").commit();
                btnHome.setEnabled(true);
                btnSettings.setEnabled(true);
                btnActivity.setEnabled(false);
            }
        });
    }

}


