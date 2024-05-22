package com.example.myapplicationhaha;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends ArrayAdapter<ListData> {

    FirebaseFirestore firebaseFirestore;
    String user;
    List<ItemLike> likeList;
    Context context;
    public ItemsAdapter(@NonNull Context context, ArrayList<ListData> dataArrayList, FirebaseFirestore instance, String user, List<ItemLike> likes) {
        super(context, R.layout.list_item, dataArrayList);
        firebaseFirestore = instance;
        this.user = user;
        this.likeList = likes;
        this.context = context;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ListData listData = getItem(position);
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }


        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        ImageView listImage = view.findViewById(R.id.listImage);
        ImageView like = view.findViewById(R.id.like);
        TextView listName = view.findViewById(R.id.listName);
        TextView listTime = view.findViewById(R.id.listTime);
        listImage.setImageResource(listData.image);
        listName.setText(listData.name);
        listTime.setText(listData.time);

        listData.liked= likeList.stream().anyMatch(x->x.item.equals(listData.name));

        if(listData.liked)
                like.setImageResource(R.drawable.like2);
        else
                like.setImageResource(R.drawable.like1);

        view.setOnClickListener(v->{
            Intent intent = new Intent(context, SingleView.class);
            intent.putExtra("meal",listData);
            context.startActivity(intent);
        });

        like.setOnClickListener(v->{
            listData.liked = !listData.liked;
            if(listData.liked)
                like.setImageResource(R.drawable.like2);
            else
                like.setImageResource(R.drawable.like1);

            firebaseFirestore.collection("likes")
                    .document(user+" "+listData.name)
                    .set(new ItemLike(user, listData.name,listData.liked))
                    .addOnSuccessListener(xx->{
                          }).addOnFailureListener(e->{});

        });

        return view;
    }
}