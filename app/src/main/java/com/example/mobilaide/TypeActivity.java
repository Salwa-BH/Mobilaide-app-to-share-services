package com.example.mobilaide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class TypeActivity extends AppCompatActivity {

    Button offrir, demander;

    /*DatabaseReference databaseReference;
    ArrayList<Message> arrayList=new ArrayList<Message>();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        offrir = (Button) findViewById(R.id.offrir);
        demander = (Button) findViewById(R.id.demander);
        final String activity = getIntent().getStringExtra("EXTRA_SESSION_ID");
        final String mail = getIntent().getStringExtra("MAIL");
        String demande_intent = getIntent().getStringExtra("DEMANDE");
        String offre_intent = getIntent().getStringExtra("OFFRE");

        demander.setText(demande_intent);
        offrir.setText(offre_intent);


        offrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), OfferActivity.class);
                intent.putExtra("EXTRA_SESSION_ID", activity);
                intent.putExtra("MAIL",mail);

                startActivity(intent);
            }
        });

        demander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), DemandActivity.class);

                /*Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)arrayList);
                intent.putExtra("BUNDLE",args);
                args.putSerializable("ARRAYLIST",(Serializable)arrayList);
                intent.putExtra("BUNDLE",args);*/

                intent.putExtra("EXTRA_SESSION_ID", activity);
                intent.putExtra("MAIL",mail);

                startActivity(intent);
            }
        });




    }
}
