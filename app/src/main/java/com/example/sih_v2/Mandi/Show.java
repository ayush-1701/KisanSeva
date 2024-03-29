package com.example.sih_v2.Mandi;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sih_v2.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Show extends AppCompatActivity {
    RecyclerView recycler;
    PostAdapter adapter;
    String commodity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        //recycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

        recycler.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        commodity = intent.getStringExtra("commo");

        FirebaseRecyclerOptions<post> options =
                new FirebaseRecyclerOptions.Builder<post>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Mandi").orderByChild("commodity").equalTo(commodity), post.class)
                        .build();
        //Toast.makeText(ShowShop.this,"RECYCLER CREATED",Toast.LENGTH_SHORT).show();

        adapter = new PostAdapter(options);
        recycler.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
        //  Toast.makeText(ShowShop.this,"Onstart Method ",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        // Toast.makeText(ShowShop.this,"STOP Method ",Toast.LENGTH_SHORT).show();
        super.onStop();
        adapter.stopListening();
    }
}