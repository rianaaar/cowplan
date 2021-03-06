package com.psbo.cowplan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.psbo.cowplan.Model.data_sapi;

import java.util.Calendar;

public class InputSapiActivity extends AppCompatActivity {

    // variable yang merefers ke Firebase Realtime Database
    private DatabaseReference database;

    // variable fields EditText dan Button
    private FirebaseAuth auth;
    private Button button,btn;
    private EditText nama_sapi;
    public String tanggal_birahi, tanggal_melahirkan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_date);
        Bundle bundle=getIntent().getExtras();
        String value = bundle.getString("date");
        tanggal_birahi = value;

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        tanggal_melahirkan = mYear + "/" + mMonth + "/" + mDay;

        // inisialisasi fields EditText dan Button
        nama_sapi = findViewById(R.id.namasapi);
        button = (Button) findViewById(R.id.Button);
        btn = (Button) findViewById(R.id.Buttonshow);

        auth = FirebaseAuth.getInstance();
        // mengambil referensi ke Firebase Database
        database = FirebaseDatabase.getInstance().getReference();
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userid = currentFirebaseUser.getUid();

        // kode yang dipanggil ketika tombol Submit diklik
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEmpty(nama_sapi.getText().toString())){
                    submitSapi(new data_sapi(nama_sapi.getText().toString(), tanggal_birahi, tanggal_melahirkan));
                     KembalikeCalendar();}
                else
                    Toast.makeText(InputSapiActivity.this,"Data tidak boleh kosong", Toast.LENGTH_LONG).show();

                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(
                        nama_sapi.getWindowToken(), 0);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lihatdata();
            }
        });

    }

    private boolean isEmpty(String s) {
        // Cek apakah ada fields yang kosong, sebelum disubmit
        return TextUtils.isEmpty(s);
    }

    private void submitSapi(data_sapi sapi) {
        /**
         * Ini adalah kode yang digunakan untuk mengirimkan data ke Firebase Realtime Database
         * dan juga kita set onSuccessListener yang berisi kode yang akan dijalankan
         * ketika data berhasil ditambahkan
         */
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userid = currentFirebaseUser.getUid();

        database.child("sapi").child(userid).push().setValue(sapi).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                nama_sapi.setText("");
                //Snackbar.make(findViewById(R.id.button), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }
    private void updateSapi(data_sapi sapi) {
        /**
         * Baris kode yang digunakan untuk mengupdate data barang
         * yang sudah dimasukkan di Firebase Realtime Database
         */
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, InputSapiActivity.class);
    }
    public void KembalikeCalendar(){
        Intent y = new Intent(InputSapiActivity.this, CalendarActivity.class);
        startActivity(y);
    }
    public void lihatdata(){
        Intent y = new Intent(InputSapiActivity.this, BacaSapiActivity.class);
        startActivity(y);
    }
}

