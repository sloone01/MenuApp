package com.example.myapplicationhaha;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.rpc.Help;

import java.util.ArrayList;
import java.util.List;

public class ViewItemsList extends AppCompatActivity {

    ListView listView;
    String user;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items_list);
        SharedPreferences pref = getSharedPreferences("user", MODE_PRIVATE);
        user = pref.getString("username", "");
        ImageView exit = findViewById(R.id.exit);
        exit.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
        loadMenu();
    }

    private void loadMenu() {
        List<ItemLike> likeList = new ArrayList<>();
       firebaseFirestore.collection("likes")
                .whereEqualTo("user", user)
                .whereEqualTo("liked",true)
                .get().addOnCompleteListener(task -> {
                    for (QueryDocumentSnapshot snapshot : task.getResult()) {
                        likeList.add(snapshot.toObject(ItemLike.class));
                    }


                    ItemsAdapter adapter = new ItemsAdapter(this,
                            Helper.items(),firebaseFirestore,user,likeList);

                    listView = findViewById(R.id.list);
                    listView.setAdapter(adapter);


                });



    }
}