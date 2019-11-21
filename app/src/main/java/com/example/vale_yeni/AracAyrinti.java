package com.example.vale_yeni;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AracAyrinti extends AppCompatActivity {


    private FirebaseDatabase db;
    private DatabaseReference dbRef;
    private FirebaseUser fUser;
    private ArrayList<Veriler> chatLists = new ArrayList<>();
    private CustomAdapter customAdapter;
    private String subject;
    private ListView listView;



    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    TextView kart_no,plaka,ad_soyad,telefon,park_alanı,tarih;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arac_ayrinti);




        Bundle extras = getIntent().getExtras();
        final String arac_ıd = extras.getString("arac_ıd_key");

        kart_no=(TextView) findViewById(R.id.kart_no);
        plaka=(TextView) findViewById(R.id.plaka);
        ad_soyad=(TextView) findViewById(R.id.ad_soyad);
        telefon=(TextView) findViewById(R.id.telefon);
        park_alanı=(TextView) findViewById(R.id.park_alanı);
        tarih=(TextView) findViewById(R.id.tarih);





        DatabaseReference oku= FirebaseDatabase.getInstance().getReference().child("ARACLAR").child(arac_ıd);


        ValueEventListener kontrol=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                String ad_soyad_str = String.valueOf(dataSnapshot.child("ad_soyad_str").getValue());
                String tarih_str = String.valueOf(dataSnapshot.child("dateTime").getValue());
                String kart_no_str = String.valueOf(dataSnapshot.child("kart_no_str").getValue());
                String park_alanı_str = String.valueOf(dataSnapshot.child("park_alanı_str").getValue());
                String plaka_str = String.valueOf(dataSnapshot.child("plaka_str").getValue());
                String telefon_str = String.valueOf(dataSnapshot.child("telefon_str").getValue());

                ad_soyad.setText(ad_soyad_str);
                tarih.setText(tarih_str);
                kart_no.setText(kart_no_str);
                park_alanı.setText(park_alanı_str);
                plaka.setText(plaka_str);
                telefon.setText(telefon_str);









            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        oku.addValueEventListener(kontrol);




        Button araci_teslim_et=(Button)findViewById(R.id.araci_teslim_et);



        araci_teslim_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AracAyrinti.this, KartTara.class);
                intent.putExtra("kart_no_key",kart_no.getText().toString());
                intent.putExtra("arac_ıd_key",arac_ıd);
                startActivity(intent);
            }
        });

    }



}
