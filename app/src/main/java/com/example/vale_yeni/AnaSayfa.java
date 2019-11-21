package com.example.vale_yeni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AnaSayfa extends AppCompatActivity {


    private FirebaseDatabase db;
    private DatabaseReference dbRef;
    private FirebaseUser fUser;
    private ArrayList<Veriler> chatLists = new ArrayList<>();
    private CustomAdapter customAdapter;
    private String subject;
    private ListView listView;

    List<String> ad_soyad_listesi;
    List<String> tarih_listesi;

    List<String> kart_no_listesi;
    List<String> park_alanı_listesi;

    List<String> plaka_listesi;
    List<String> telefon_listesi;

    List<String> ıd_listesi,teslimat_bekleyen_listesi;

    int tes_bek_say_int;

    private TextView teslimat_bekleyen_sayisi_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ana_sayfa);

        teslimat_bekleyen_sayisi_tv=(TextView)findViewById(R.id.teslimat_bek_ar_sayisi);

        ImageView yeni_arac_kayit_button=(ImageView) findViewById(R.id.yeni_arac_kayit_button);

        final ImageView teslimat_bekleyen=(ImageView) findViewById(R.id.teslimat_bekleyen);


        yeni_arac_kayit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnaSayfa.this,Yeni_Arac_Kayit.class);
                startActivity(intent);
            }
        });


        teslimat_bekleyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AnaSayfa.this,TeslimatBekleyenler.class);
                startActivity(intent);
            }
        });

        listView = (ListView)findViewById(R.id.chatListView);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String arac_ıd=ıd_listesi.get(position);
                Intent i = new Intent(getApplicationContext(), AracAyrinti.class);
                i.putExtra("arac_ıd_key",arac_ıd);
                startActivity(i);

            }
        });



        db = FirebaseDatabase.getInstance();
        customAdapter = new CustomAdapter(getApplicationContext(),chatLists,fUser);
        listView.setAdapter(customAdapter);

        dbRef = db.getReference("ARACLAR");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                chatLists.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()){

                    Veriler veriler = ds.getValue(Veriler.class);
                    chatLists.add(veriler);

                }
                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ARACLAR");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ıd_listesi = new ArrayList<>();

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){


                    String value1 = String.valueOf(dataSnapshot1.getKey());
                    ıd_listesi.add(value1);




                }





                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                    String ad_soyad_listesi_degerler = String.valueOf(dataSnapshot1.child("ad_soyad_str"));
                    String tarih_listesi_degerler = String.valueOf(dataSnapshot1.child("dateTime"));
                    String kart_no_listesi_degerler = String.valueOf(dataSnapshot1.child("kart_no_str"));
                    String park_alanı_listesi_degerler = String.valueOf(dataSnapshot1.child("park_alanı_str"));
                    String plaka_listesi_degerler = String.valueOf(dataSnapshot1.child("plaka_str"));
                    String telefon_listesi_degerler = String.valueOf(dataSnapshot1.child("telefon_str"));

                    String ıd_listesi_degerler = String.valueOf(dataSnapshot1.getKey());

                    ad_soyad_listesi = new ArrayList<>();
                    tarih_listesi = new ArrayList<>();
                    kart_no_listesi = new ArrayList<>();
                    park_alanı_listesi = new ArrayList<>();
                    plaka_listesi = new ArrayList<>();
                    telefon_listesi = new ArrayList<>();
                   // ıd_listesi = new ArrayList<>();

                    ad_soyad_listesi.add(ad_soyad_listesi_degerler);
                    tarih_listesi.add(tarih_listesi_degerler);
                    kart_no_listesi.add(kart_no_listesi_degerler);
                    park_alanı_listesi.add(park_alanı_listesi_degerler);
                    plaka_listesi.add(park_alanı_listesi_degerler);
                    telefon_listesi.add(telefon_listesi_degerler);
                   // ıd_listesi.add(ıd_listesi_degerler);




                }






            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });



        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("TESLİMAT_BEKLEYENLER");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                teslimat_bekleyen_listesi = new ArrayList<>();

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){


                    String value1 = String.valueOf(dataSnapshot1.getKey());
                    teslimat_bekleyen_listesi.add(value1);



                    tes_bek_say_int = teslimat_bekleyen_listesi.size();

                    if (tes_bek_say_int!=0){
                        teslimat_bekleyen_sayisi_tv.setVisibility(View.VISIBLE);

                        teslimat_bekleyen_sayisi_tv.setText(String.valueOf(tes_bek_say_int));
                    }



                }





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


    }






}
