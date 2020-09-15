package com.example.mobilaide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button sante, education, domicile, courtage, autre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Catégories de services");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sante = findViewById(R.id.sante);
        education = findViewById(R.id.education);
        domicile = findViewById(R.id.domicile);
        courtage = findViewById(R.id.courtage);
        autre = findViewById(R.id.autre);


        sante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), TypeActivity.class);
                final String mail = getIntent().getStringExtra("MAIL");
                intent.putExtra("MAIL",mail);

                intent.putExtra("EXTRA_SESSION_ID", "sante");
                intent.putExtra("DEMANDE", "Consulter un spécialiste");
                intent.putExtra("OFFRE", "Offrir un service en tant que spécialiste");
                startActivity(intent);
            }
        });

        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), TypeActivity.class);
                final String mail = getIntent().getStringExtra("MAIL");
                intent.putExtra("MAIL",mail);

                intent.putExtra("EXTRA_SESSION_ID", "education");
                intent.putExtra("DEMANDE", "Demander un soutien");
                intent.putExtra("OFFRE", "Offrir un soutien");
                startActivity(intent);
            }
        });

        domicile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), TypeActivity.class);
                final String mail = getIntent().getStringExtra("MAIL");
                intent.putExtra("MAIL",mail);

                intent.putExtra("EXTRA_SESSION_ID", "domicile");
                intent.putExtra("DEMANDE", "Demander une garde ou une aide physique");
                intent.putExtra("OFFRE", "Offrir une aide");
                startActivity(intent);
            }
        });

        courtage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), TypeActivity.class);
                final String mail = getIntent().getStringExtra("MAIL");
                intent.putExtra("MAIL",mail);

                intent.putExtra("EXTRA_SESSION_ID", "courtage");
                intent.putExtra("DEMANDE", "Envoyer ou récupérer un colis");
                intent.putExtra("OFFRE", "Livrer un colis");
                startActivity(intent);
            }
        });

        autre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), TypeActivity.class);
                final String mail = getIntent().getStringExtra("MAIL");
                intent.putExtra("MAIL",mail);

                intent.putExtra("EXTRA_SESSION_ID", "autre");
                intent.putExtra("DEMANDE", "Demander un service particulier");
                intent.putExtra("OFFRE", "Offrir une aide particulière");
                startActivity(intent);
            }
        });
    }
}
