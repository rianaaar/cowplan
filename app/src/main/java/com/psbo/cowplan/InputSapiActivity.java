package com.psbo.cowplan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.psbo.cowplan.Model.data_sapi;

public class InputSapiActivity extends AppCompatActivity {

    // variable yang merefers ke Firebase Realtime Database
    private DatabaseReference database;

    // variable fields EditText dan Button
    private FirebaseAuth auth;
    private Button button;
    private EditText nama_sapi, tanggal_birahi, tanggal_melahirkan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_date);

        // inisialisasi fields EditText dan Button
        nama_sapi = findViewById(R.id.namasapi);
        //tanggal_birahi = findViewById(R.id.nama);
        //tanggal_melahirkan = findViewById(R.id.jurusan);
        button = (Button) findViewById(R.id.Button);
        auth = FirebaseAuth.getInstance();
        // mengambil referensi ke Firebase Database
        database = FirebaseDatabase.getInstance().getReference();
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userid = currentFirebaseUser.getUid();
        //String getUserID = auth.getCurrentUser().getUid();
        // kode yang dipanggil ketika tombol Submit diklik
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEmpty(nama_sapi.getText().toString()) && !isEmpty(tanggal_birahi.getText().toString()) && !isEmpty(tanggal_melahirkan.getText().toString()))
                    submitSapi(new data_sapi(nama_sapi.getText().toString(), tanggal_birahi.getText().toString(), tanggal_melahirkan.getText().toString()));
                else
                    Snackbar.make(findViewById(R.id.button), "Data Tidak boleh kosng", Snackbar.LENGTH_LONG).show();

                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(
                        nama_sapi.getWindowToken(), 0);
            }
        });

    }

    private boolean isEmpty(String s) {
        // Cek apakah ada fields yang kosong, sebelum disubmit
        return TextUtils.isEmpty(s);
    }

    private void updateBarang(data_sapi barang) {
        // kodingan untuk next tutorial ya :p
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
                tanggal_birahi.setText("");
                tanggal_melahirkan.setText("");
                Snackbar.make(findViewById(R.id.button), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, InputSapiActivity.class);
    }
}

