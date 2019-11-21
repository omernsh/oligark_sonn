package com.example.vale_yeni;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TeslimatBekleyenler extends AppCompatActivity {


    private FirebaseDatabase db;
    private DatabaseReference dbRef;
    private FirebaseUser fUser;
    private ArrayList<Veriler> chatLists = new ArrayList<>();
    private CustomAdapter_Tes_Bekleyen customAdapter;
    private String subject;
    private ListView listView;

    List<String> plaka_listesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teslimat_bekleyenler);

        listView = (ListView)findViewById(R.id.chatListView);




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String plaka=plaka_listesi.get(position);
                Intent i = new Intent(getApplicationContext(), TeslimatıTamamla.class);
                i.putExtra("plaka_key",plaka);
                startActivity(i);

            }
        });



        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("TESLİMAT_BEKLEYENLER");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                plaka_listesi = new ArrayList<>();

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                    String value1 = String.valueOf(dataSnapshot1.child("plaka_str").getValue());
                    plaka_listesi.add(value1);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });




        db = FirebaseDatabase.getInstance();
        customAdapter = new CustomAdapter_Tes_Bekleyen(getApplicationContext(),chatLists,fUser);
        listView.setAdapter(customAdapter);

        dbRef = db.getReference("TESLİMAT_BEKLEYENLER");
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





    }

}
