package com.wira_fkom.crud_firebase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DBReadActivity extends AppCompatActivity {
    /**
     * Mendefinisikan variable yang akan dipakai
     */
    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Dosen> daftarDosen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Mengeset layout
         */
        setContentView(R.layout.activity_dbcreate);
        /**
         * Inisialisasi RecyclerView & komponennya
         */
        rvView = findViewById(R.id.rv_main);

        // Check if rvView is not null
        if (rvView != null) {
            rvView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(this);
            rvView.setLayoutManager(layoutManager);

            /**
             * Inisialisasi dan mengambil Firebase Database Reference
             */
            database = FirebaseDatabase.getInstance().getReference();

            /**
             * Mengambil data dari Firebase Realtime DB
             */
            database.child("dosen").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    /**
                     * Saat ada data baru, masukkan datanya ke ArrayList
                     */
                    daftarDosen = new ArrayList<>();
                    for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                        /**
                         * Mapping data pada DataSnapshot ke dalam object Dosen
                         * Dan juga menyimpan primary key pada object Dosen
                         * untuk keperluan Edit dan Delete data
                         */
                        Dosen dosen = noteDataSnapshot.getValue(Dosen.class);
                        dosen.setKey(noteDataSnapshot.getKey());
                        /**
                         * Menambahkan object Dosen yang sudah dimapping
                         * ke dalam ArrayList
                         */
                        daftarDosen.add(dosen);
                    }
                    /**
                     * Inisialisasi adapter dan data Dosen dalam bentuk ArrayList
                     * dan mengeset Adapter ke dalam RecyclerView
                     */
                    adapter = new AdapterDosenRecyclerView(daftarDosen, DBReadActivity.this);
                    rvView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    /**
                     * Kode ini akan dipanggil ketika ada error dan
                     * pengambilan data gagal dan memprint error nya
                     * ke LogCat
                     */
                    System.out.println(databaseError.getDetails() + " " + databaseError.getMessage());
                }
            });
        } else {
            throw new RuntimeException("RecyclerView not found in the layout");
        }
    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, DBReadActivity.class);
    }
}
