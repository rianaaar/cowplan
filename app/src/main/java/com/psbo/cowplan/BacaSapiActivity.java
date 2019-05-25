package com.psbo.cowplan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import com.psbo.cowplan.AdapterSapiRecycleView;
import com.psbo.cowplan.Model.data_sapi;


public class BacaSapiActivity extends AppCompatActivity implements AdapterSapiRecycleView.FirebaseDataListener {

    /**
     * Mendefinisikan variable yang akan dipakai
     */
    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<data_sapi> daftarSapi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Mengeset layout
         */
        setContentView(R.layout.activity_baca_data);

        /**
         * Inisialisasi RecyclerView & komponennya
         */
        rvView = (RecyclerView) findViewById(R.id.rv_main);
        //rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        /**
         * Inisialisasi dan mengambil Firebase Database Reference
         */
        database = FirebaseDatabase.getInstance().getReference();

        /**
         * Mengambil data barang dari Firebase Realtime DB
         */
        database.child("sapi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                /**
                 * Saat ada data baru, masukkan datanya ke ArrayList
                 */
                daftarSapi = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    /**
                     * Mapping data pada DataSnapshot ke dalam object Sapi
                     * Dan juga menyimpan primary key pada object Sapi
                     * untuk keperluan Edit dan Delete data
                     */
                    data_sapi sapi = noteDataSnapshot.getValue(data_sapi.class);
                    sapi.setKey(noteDataSnapshot.getKey());

                    /**
                     * Menambahkan object Sapi yang sudah dimapping
                     * ke dalam ArrayList
                     */
                    daftarSapi.add(sapi);
                }

                /**
                 * Inisialisasi adapter dan data sapi dalam bentuk ArrayList
                 * dan mengeset Adapter ke dalam RecyclerView
                 */
                adapter = new AdapterSapiRecycleView(daftarSapi, BacaSapiActivity.this);
                rvView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                /**
                 * Kode ini akan dipanggil ketika ada error dan
                 * pengambilan data gagal dan memprint error nya
                 * ke LogCat
                 */
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, BacaSapiActivity.class);
    }

    @Override
    public void onDeleteData(data_sapi sapi, final int position) {
        /**
         * Kode ini akan dipanggil ketika method onDeleteData
         * dipanggil dari adapter lewat interface.
         * Yang kemudian akan mendelete data di Firebase Realtime DB
         * berdasarkan key barang.
         * Jika sukses akan memunculkan Toast
         */
        if(database!=null){database.child("sapi").child(sapi.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(BacaSapiActivity.this,"success delete", Toast.LENGTH_LONG).show();
            }
        });

        }
    }

}
