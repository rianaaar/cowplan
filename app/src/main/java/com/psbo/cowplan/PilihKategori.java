package com.psbo.cowplan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import static android.text.TextUtils.isEmpty;

public class PilihKategori extends AppCompatActivity {
    private EditText nama_sapi, tanggal_birahi, tanggal_melahirkan;
    private FirebaseAuth auth;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_date);

        button = (Button) findViewById(R.id.Button);
        auth = FirebaseAuth.getInstance(); //Mendapakan Instance Firebase Autentifikasi

        //Inisialisasi ID (EditText)
        nama_sapi = findViewById(R.id.namasapi);
        //tanggal_birahi = findViewById(R.id.nama);
        //tanggal_melahirkan = findViewById(R.id.jurusan);
        input_sapi();

    }

    public void input_sapi(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Mendapatkan UserID dari pengguna yang Terautentikasi
                String getUserID = auth.getCurrentUser().getUid();

                //Mendapatkan Instance dari Database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference getReference;

                //Menyimpan Data yang diinputkan User kedalam Variable
                String getNama_sapi = nama_sapi.getText().toString();
                String getTanggal_birahi = tanggal_birahi.getText().toString();
                String getTanggal_melahirkan = tanggal_melahirkan.getText().toString();

                getReference = database.getReference(); // Mendapatkan Referensi dari Database

                // Mengecek apakah ada data yang kosong
                if(isEmpty(getNama_sapi) && isEmpty(getTanggal_birahi) && isEmpty(getTanggal_melahirkan)){
                    //Jika Ada, maka akan menampilkan pesan singkan seperti berikut ini.
                    Toast.makeText(PilihKategori.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }
                //open_MainActivity();
            }
        });
    }

}

