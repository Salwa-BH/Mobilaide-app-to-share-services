package com.example.mobilaide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
public class PubdemandActivity extends AppCompatActivity {
    Spinner spinner;
    EditText description;
    Button demanderService;
    DatabaseReference databaseMessages;
    ArrayList list = new ArrayList<String>();
    String spinner_chosen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pubdemand);
        // Database
        databaseMessages = FirebaseDatabase.getInstance().getReference("message");
        //Get type of category chosen
        final String activity = getIntent().getStringExtra("EXTRA_SESSION_ID");
        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(activity.toUpperCase());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Variables
        spinner = (Spinner) findViewById(R.id.spinner);
        description = (EditText) findViewById(R.id.description);
        demanderService = (Button) findViewById(R.id.demanderService);
        if(activity.equals("sante")){
            list_sante();
        }
        else if(activity.equals("education")){
            list_education();
        }
        else if(activity.equals("domicile")){
            list_domicile();
        }
        else if(activity.equals("courtage")){
            list_courtage();
        }
        else if(activity.equals("autre")){
            list_autre();
        }
        ArrayAdapter<String> m_adapterForSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        m_adapterForSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(m_adapterForSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View selectedItemView, int position, long id) {
                // On selecting a spinner item
                spinner_chosen = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        demanderService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String desc = description.getText().toString();
                //get mail as username in table
                //final String mail = getIntent().getStringExtra("MAIL");
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String mail = user.getEmail();
                // set date
                String pattern = "MM/dd/yyyy HH:mm:ss";
                // Create an instance of SimpleDateFormat used for formatting
                // the string representation of date according to the chosen pattern
                DateFormat df = new SimpleDateFormat(pattern);
                // Get the today date using Calendar object.
                Date today = Calendar.getInstance().getTime();
                // Using DateFormat format method we can create a string
                // representation of a date with the defined format.
                String todayAsString = df.format(today);
                //add message to database
                addMessage(mail,todayAsString.toString(), activity ,spinner_chosen,desc);
            }
        });

    }
    public void addMessage(String username,String date,String categorie,String type,String description){
        String id = databaseMessages.push().getKey();
        Message message = new Message(id,username,date,categorie,type,description);
        databaseMessages.child(id).setValue(message);
        Toast.makeText(this,"Le message est bien partagé", Toast.LENGTH_SHORT).show();
    }
    public void list_sante(){
        list.add("consultation médicale à domicile");
        list.add("consulation téléphonique");
        list.add("service de réeducation à domicile");
        list.add("service de réeducation au cabinet");
        list.add("coaching d'un psy au téléphone");
        list.add("coaching d'un psy au cabinet");
    }
    public void list_education(){
        list.add("séance pour autiste");
        list.add("séance pour trisomique");
        list.add("séance pour dyslexique");
        list.add("séance pour retard mental");
    }
    public void list_domicile(){
        list.add("Garde d'un enfant");
        list.add("Garde d'un adulte");
        list.add("Garde d'un agé");
        list.add("Aide ménagère");
    }
    public void list_transport(){
        list.add("Demander un déplacement");
    }
    public void list_courtage(){ list.add("Recevoir un colis"); list.add("Envoyer un colis");    }
    public void list_autre(){
        list.add("autre");
    }
}
