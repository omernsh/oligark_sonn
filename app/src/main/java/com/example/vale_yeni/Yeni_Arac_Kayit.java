package com.example.vale_yeni;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.text.SimpleDateFormat;
import java.util.Date;


public class Yeni_Arac_Kayit extends AppCompatActivity {

    Button b_1,b_2,b_3,b_4,b_5,b_6,b_7,b_8,b_9,b_10,b_11,kaydet_button,kart_no_button;
    Context context=this;
    private int int_1,int_2,int_3,int_4,int_5,int_6,int_7,int_8,int_9,int_10,int_11=0;
    private TextView tarih_textview;
    private EditText kart_no_edittext,plaka_edittext,ad_soyad_edittext,telefon_edittext;

    Spinner park_alını_edittext;
    String kart_no;


    private DatabaseReference dbRef;
    private FirebaseDatabase db;


    public static final String ACCOUNT_SID = "AC7264e06f7e9a2422e32b47f58aa2b369";
    public static final String AUTH_TOKEN = "462e4bb294d64d4b365eec8d809a5e3f";









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yeni_arac_kayit);

        Bundle extras = getIntent().getExtras();













        TextView tarih_text_view=(TextView)findViewById(R.id.tarih);

        long msTime = System.currentTimeMillis();
        Date curDateTime = new Date(msTime);
        SimpleDateFormat formatter = new SimpleDateFormat("dd'/'MM'/'y hh:mm");
        String dateTime = formatter.format(curDateTime);

        tarih_text_view.setText(dateTime);





        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.setThreadPolicy( new StrictMode.ThreadPolicy.Builder().permitAll().build() );
        }

        b_1=(Button)findViewById(R.id.b_1);
        b_2=(Button)findViewById(R.id.b_2);
        b_3=(Button)findViewById(R.id.b_3);
        b_4=(Button)findViewById(R.id.b_4);
        b_5=(Button)findViewById(R.id.b_5);
        b_6=(Button)findViewById(R.id.b_6);
        b_7=(Button)findViewById(R.id.b_7);
        b_8=(Button)findViewById(R.id.b_8);
        b_9=(Button)findViewById(R.id.b_9);
        b_10=(Button)findViewById(R.id.b_10);
        b_11=(Button)findViewById(R.id.b_11);

        kaydet_button=(Button)findViewById(R.id.kaydet_button);

        tarih_textview=(TextView)findViewById(R.id.tarih);
        kart_no_button=(Button)findViewById(R.id.kart_no_button);
        plaka_edittext=(EditText)findViewById(R.id.plaka_edittext);
        ad_soyad_edittext=(EditText)findViewById(R.id.ad_soyad_edittext);
        telefon_edittext=(EditText)findViewById(R.id.telefon_edittext);

        if (extras!=null){

            kart_no = extras.getString("kart_no_key");
            kart_no_button.setText("Kart no: "+kart_no);


        }




      final Spinner park_alanı_edittext = (Spinner) findViewById(R.id.park_alanı_edittext);




        park_alanı_edittext.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                ((TextView)parentView.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });








        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference("ARACLAR");


        kaydet_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                long msTime = System.currentTimeMillis();
                Date curDateTime = new Date(msTime);
                SimpleDateFormat formatter = new SimpleDateFormat("dd'/'MM'/'y hh:mm");
                String dateTime = formatter.format(curDateTime);
                String kart_no_str= kart_no_button.getText().toString();
                String plaka_str= plaka_edittext.getText().toString();
                String ad_soyad_str= ad_soyad_edittext.getText().toString();
                String telefon_str= telefon_edittext.getText().toString();

                String park_alanı_str = String.valueOf(park_alanı_edittext.getSelectedItem());



                final Veriler veriler = new Veriler(String.valueOf(kart_no),plaka_str,ad_soyad_str,telefon_str,park_alanı_str,dateTime);
                dbRef.push().setValue(veriler);

                Toast.makeText(getApplicationContext(), "KAYIT ALINDI...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Yeni_Arac_Kayit.this, AnaSayfa.class);
                startActivity(intent);


            }

        });

        kart_no_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Yeni_Arac_Kayit.this, KartTara_2.class);
                intent.putExtra("yeni_kayıt_key",1);
                startActivity(intent);

            }
        });







        b_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (int_1==0){

                    b_1.setBackgroundResource(R.drawable.hasarli_check_button);
                    int_1=1;
                }
                else {

                    b_1.setBackgroundResource(R.drawable.saglam_check_button);
                    int_1=0;

                }
            }
        });



        b_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (int_2==0){

                    b_2.setBackgroundResource(R.drawable.hasarli_check_button);
                    int_2=1;
                }
                else {

                    b_2.setBackgroundResource(R.drawable.saglam_check_button);
                    int_2=0;

                }
            }
        });





        b_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (int_3==0){

                    b_3.setBackgroundResource(R.drawable.hasarli_check_button);
                    int_3=1;
                }
                else {

                    b_3.setBackgroundResource(R.drawable.saglam_check_button);
                    int_3=0;

                }
            }
        });



        b_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (int_4==0){

                    b_4.setBackgroundResource(R.drawable.hasarli_check_button);
                    int_4=1;
                }
                else {

                    b_4.setBackgroundResource(R.drawable.saglam_check_button);
                    int_4=0;

                }
            }
        });



        b_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (int_5==0){

                    b_5.setBackgroundResource(R.drawable.hasarli_check_button);
                    int_5=1;
                }
                else {

                    b_5.setBackgroundResource(R.drawable.saglam_check_button);
                    int_5=0;

                }
            }
        });


        b_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (int_6==0){

                    b_6.setBackgroundResource(R.drawable.hasarli_check_button);
                    int_6=1;
                }
                else {

                    b_6.setBackgroundResource(R.drawable.saglam_check_button);
                    int_6=0;

                }
            }
        });


        b_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (int_7==0){

                    b_7.setBackgroundResource(R.drawable.hasarli_check_button);
                    int_7=1;
                }
                else {

                    b_7.setBackgroundResource(R.drawable.saglam_check_button);
                    int_7=0;

                }
            }
        });


        b_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (int_8==0){

                    b_8.setBackgroundResource(R.drawable.hasarli_check_button);
                    int_8=1;
                }
                else {

                    b_8.setBackgroundResource(R.drawable.saglam_check_button);
                    int_8=0;

                }
            }
        });


        b_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (int_9==0){

                    b_9.setBackgroundResource(R.drawable.hasarli_check_button);
                    int_9=1;
                }
                else {

                    b_9.setBackgroundResource(R.drawable.saglam_check_button);
                    int_9=0;

                }
            }
        });


        b_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (int_10==0){

                    b_10.setBackgroundResource(R.drawable.hasarli_check_button);
                    int_10=1;
                }
                else {

                    b_10.setBackgroundResource(R.drawable.saglam_check_button);
                    int_10=0;

                }
            }
        });


        b_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (int_11==0){

                    b_11.setBackgroundResource(R.drawable.hasarli_check_button);
                    int_11=1;
                }
                else {

                    b_11.setBackgroundResource(R.drawable.saglam_check_button);
                    int_11=0;

                }
            }
        });

    }


   /* private void sendMessage() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+12562089908"),
                new com.twilio.type.PhoneNumber("+905318367781"),
                "Hello there!")
                .setMediaUrl(
                        Arrays.asList(URI.create("https://demo.twilio.com/owl.png")))
                .create();

        System.out.println(message.getSid());
    }

    */






}
