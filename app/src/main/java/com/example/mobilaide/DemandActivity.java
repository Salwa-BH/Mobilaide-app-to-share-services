package com.example.mobilaide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class DemandActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    ListView listView;
    Button publishDemand, actualiser;

    private static CustomAdapter adapter;
    ArrayList<Message> arrayList=new ArrayList<Message>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demand);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Liste des publications");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final String activity = getIntent().getStringExtra("EXTRA_SESSION_ID");

        listView = (ListView) findViewById(R.id.listviewtxt);


        databaseReference= FirebaseDatabase.getInstance().getReference("message");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                    Message message = messageSnapshot.getValue(Message.class);
                    if(activity.equals(message.getCategorie()))
                        arrayList.add(message);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        adapter = new CustomAdapter(this, arrayList);
        listView.setAdapter(adapter);


        actualiser=findViewById(R.id.actualiser);
        actualiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getBaseContext(), ChatActivity.class);
                        Message message =arrayList.get(position);
                        intent.putExtra("EMAIL",message.getUsername());
                        startActivity(intent);
                    }
                });
            }
        });

        Button demanderService = (Button) findViewById(R.id.publishDemand);
        demanderService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), PubdemandActivity.class);
                intent.putExtra("EXTRA_SESSION_ID",activity);
                startActivity(intent);
            }
        });
    }


}
