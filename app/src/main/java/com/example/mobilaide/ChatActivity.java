package com.example.mobilaide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ChatActivity extends AppCompatActivity {

    DatabaseReference reference;
    ImageButton btn_send;
    EditText txt_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //final String activity = getIntent().getStringExtra("EMAIL");
        final String activity = "amal@gmail.com";
        getSupportActionBar().setTitle(activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Variables
        btn_send = findViewById(R.id.btn_send);
        txt_send = findViewById(R.id.txt_send);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = txt_send.getText().toString();
                if (!msg.equals("")){

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String sender = user.getEmail();
                    sendMessage(activity,sender,msg);
                } else {
                    Toast.makeText(ChatActivity.this,"Vous ne pouvez pas envoyer un message vide",Toast.LENGTH_SHORT).show();
                }
                txt_send.setText("");
            }
        });


    }

    private void sendMessage(String sender, String receiver, String message){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        HashMap<String,Object>  hashMap = new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("receiver",receiver);
        hashMap.put("message",message);

        reference.child("Chats").push().setValue(hashMap);
    }
}
