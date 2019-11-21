package com.example.vale_yeni;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TeslimatıTamamla extends AppCompatActivity {


    private String[] para_birimleri={"TL","Dolar","Euro"};

    private Spinner para_birimleri_spinner;
    private ArrayAdapter<String> para_birimleri_adapter;

    private int ücretli_ücretsiz=0;

    final Context context = this;

    private String vale_ücreti_str,ücretsiz_str,ekstra_str,ekstra_ücreti_str,not_str;

    private DatabaseReference dbRef;
    private FirebaseDatabase db;



    private int vale_ücreti_int;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teslimati_tamamla);









        Bundle extras = getIntent().getExtras();
        final String plaka = extras.getString("plaka_key");



        Button teslimat_tamamla_button=(Button)findViewById(R.id.teslimat_tamamla_button);

        LinearLayout ekstra_gir_button=(LinearLayout)findViewById(R.id.ekstra_gir_button);
        LinearLayout not_gir_button=(LinearLayout)findViewById(R.id.not_gir_button);
        final LinearLayout toplam_layout=(LinearLayout)findViewById(R.id.toplam_layout);


        final LinearLayout ekstra_layout=(LinearLayout)findViewById(R.id.ekstra_layout);
        final LinearLayout not_layout=(LinearLayout)findViewById(R.id.not_layout);

        final EditText not_edit_text=(EditText)findViewById(R.id.not_edit_text);
        final EditText ekstra_ücret_edittext=(EditText)findViewById(R.id.ekstra_ücret_edittext);
        final EditText ekstra_edit_text=(EditText)findViewById(R.id.ekstra_edit_text);
        final EditText vale_ücreti=(EditText)findViewById(R.id.vale_ücreti);

        vale_ücreti_str=vale_ücreti.getText().toString();
        ücretsiz_str="0";
        ekstra_str=ekstra_edit_text.getText().toString();
        ekstra_ücreti_str=ekstra_ücret_edittext.getText().toString();
        not_str=not_edit_text.getText().toString();




        final TextView toplam_ücret_text_view=(TextView)findViewById(R.id.toplam_ücret_text_view);





        ekstra_gir_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ekstra_layout.setVisibility(View.VISIBLE);
                toplam_layout.setVisibility(View.VISIBLE);

            }
        });


        not_gir_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                not_layout.setVisibility(View.VISIBLE);

            }
        });


        vale_ücreti.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                String value = s.toString();

                if (value.matches("")){

                    toplam_ücret_text_view.setText("0 "+para_birimleri_spinner.getSelectedItem().toString());



                }
                else {

                    vale_ücreti_int=Integer.parseInt(value);
                    toplam_ücret_text_view.setText(value+" "+para_birimleri_spinner.getSelectedItem().toString());



                }



            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count){

            }

        });




        ekstra_ücret_edittext.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                String value = s.toString();
                vale_ücreti_str=vale_ücreti.getText().toString();


                if (value.matches("")){

                   toplam_ücret_text_view.setText(vale_ücreti_str+" "+para_birimleri_spinner.getSelectedItem().toString());



                }
                else {


                    if (ekstra_ücreti_str!=null){

                        int ekstra_ücreti_int=Integer.parseInt(value);
                        int toplma_ücret=ekstra_ücreti_int+vale_ücreti_int;
                        toplam_ücret_text_view.setText(String.valueOf(toplma_ücret)+" "+para_birimleri_spinner.getSelectedItem().toString());

                    }


                }


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count){

            }

        });











        final TextView ad_soyad_text_view=(TextView)findViewById(R.id.ad_soyad);
        final TextView plaka_text_view=(TextView)findViewById(R.id.plaka);
        final TextView park_yeri_text_view=(TextView)findViewById(R.id.park_yeri);
        final TextView tarih_text_view=(TextView)findViewById(R.id.tarih);
        final TextView tel_no_text_view=(TextView)findViewById(R.id.tel_no);
        final TextView kart_no_text_view=(TextView)findViewById(R.id.kart_no);




        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query applesQuery = ref.child("TESLİMAT_BEKLEYENLER").orderByChild("plaka_str").equalTo(plaka);

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                    String kart_no_str = String.valueOf(dataSnapshot1.child("kart_no_str").getValue());
                    String plaka_str = String.valueOf(dataSnapshot1.child("plaka_str").getValue());
                    String ad_soyad_str = String.valueOf(dataSnapshot1.child("ad_soyad_str").getValue());
                    String park_alanı_str = String.valueOf(dataSnapshot1.child("park_alanı_str").getValue());
                    String telefon_str = String.valueOf(dataSnapshot1.child("telefon_str").getValue());
                    String dateTime = String.valueOf(dataSnapshot1.child("dateTime").getValue());

                    ad_soyad_text_view.setText(ad_soyad_str);
                    kart_no_text_view.setText(kart_no_str);
                    plaka_text_view.setText(plaka_str);
                    park_yeri_text_view.setText(park_alanı_str);
                    tarih_text_view.setText(dateTime);
                    tel_no_text_view.setText(telefon_str);



                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        teslimat_tamamla_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query applesQuery = ref.child("TESLİMAT_BEKLEYENLER").orderByChild("plaka_str").equalTo(plaka);

                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                            showMyCustomAlertDialog();

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });


                vale_ücreti_str=vale_ücreti.getText().toString();
                ücretsiz_str="0";
                ekstra_str=ekstra_edit_text.getText().toString();
                ekstra_ücreti_str=ekstra_ücret_edittext.getText().toString();
                not_str=not_edit_text.getText().toString();





                long msTime = System.currentTimeMillis();
                Date curDateTime = new Date(msTime);
                SimpleDateFormat formatter = new SimpleDateFormat("dd'/'MM'/'y");
                SimpleDateFormat formatter1 = new SimpleDateFormat("dd'.'MM'.'y");

                String dateTime = formatter.format(curDateTime);

                String tarih_2 = formatter1.format(curDateTime);




                Date date = new Date();
                String tarih=DateFormat.getDateInstance(DateFormat.LONG).format(date);





                db = FirebaseDatabase.getInstance();
                dbRef = db.getReference("GEÇMİŞ KAYITLAR").child(tarih);






                vale_ücreti_str=vale_ücreti.getText().toString();
                ücretsiz_str="0";
                ekstra_str=ekstra_edit_text.getText().toString();
                ekstra_ücreti_str=ekstra_ücret_edittext.getText().toString();
                not_str=not_edit_text.getText().toString();



                final Veriler veriler = new Veriler(vale_ücreti_str,ekstra_ücreti_str,ekstra_str,not_str,ücretsiz_str,dateTime);
                dbRef.push().setValue(veriler);














            }
        });





        final Button ücretsiz_button=(Button)findViewById(R.id.ücretsiz_button);



        ücretsiz_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ücretli_ücretsiz==0){

                    ücretsiz_button.setBackgroundResource(R.drawable.turuncu_button_back);
                    ücretli_ücretsiz=1;
                }
                else {

                    ücretsiz_button.setBackgroundResource(R.drawable.border_button);
                    ücretli_ücretsiz=0;


                }

                toplam_ücret_text_view.setText("0 "+ para_birimleri_spinner.getSelectedItem().toString());





            }
        });













        para_birimleri_spinner = (Spinner) findViewById(R.id.para_birimi);

        para_birimleri_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, para_birimleri);

        para_birimleri_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        para_birimleri_spinner.setAdapter(para_birimleri_adapter);



        para_birimleri_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                ((TextView)parentView.getChildAt(0)).setTextColor(Color.WHITE);

                if (vale_ücreti_str.matches("")&&ekstra_ücreti_str.matches("")){

                    toplam_ücret_text_view.setText(para_birimleri_spinner.getSelectedItem().toString());

                }
                else if (vale_ücreti_str!=null){
                    toplam_ücret_text_view.setText(vale_ücreti.getText().toString()+" "+para_birimleri_spinner.getSelectedItem().toString());
                }
                else if(ekstra_ücreti_str!=null){


                    int ekstra_ücreti_int=Integer.parseInt(ekstra_edit_text.getText().toString());
                    int vale_ücreti_int=Integer.parseInt(vale_ücreti.getText().toString());

                    int toplma_ücret=ekstra_ücreti_int+vale_ücreti_int;
                    toplam_ücret_text_view.setText(String.valueOf(toplma_ücret)+" "+para_birimleri_spinner.getSelectedItem().toString());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });











    }


    public void showMyCustomAlertDialog() {

        // dialog nesnesi oluştur ve layout dosyasına bağlan
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.alert_dialog);

        // custom dialog elemanlarını tanımla - text, image ve button
        Button btnKaydet = (Button) dialog.findViewById(R.id.button_kaydet);
        TextView tvBaslik = (TextView) dialog.findViewById(R.id.textview_baslik);
        ImageView ivResim = (ImageView) dialog.findViewById(R.id.imageview_resim);

        // custom dialog elemanlarına değer ataması yap - text, image
        tvBaslik.setText("Teslimat tamamlandı!");

        // tamam butonunun tıklanma olayları
        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TeslimatıTamamla.this, AnaSayfa.class);
                startActivity(intent);



            }
        });

        dialog.show();
    }

}
