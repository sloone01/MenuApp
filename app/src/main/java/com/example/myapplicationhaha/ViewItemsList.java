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

        ArrayList<ListData> dataArrayList = new ArrayList<ListData>();
        dataArrayList.add(new ListData("Black Bean Potatoes", "40 mins", "BLack Beans, Sweet potatoes, Olive oil, Cherry tomatoes, Corn, kernels, Cilantro, Red onion, Clove, garlic, Lime, Salt, Black pepper, Red chili, flakes ", "Preheat oven to 400 degrees, Line a baking tray with parchment paper, Place in oven and bake for 40 minutes, In a bowl combine black beans, tomato, corn, cilantro, red onion, and garlic, Drizzle with lime juice and remaining oil, Sprinkle with salt, pepper, and chili flakes, Mix to combine, For the guacamole, In a small bowl, mash avocado, lime juice, and salt until well combined, For the sour cream, In a separate bowl mix coconut yogurt, lime juice, and salt, Cut sweet potatoes in half and top with black bean mixture, Top with guacamole and drizzle with vegan sour cream.", R.drawable.pic1));
        dataArrayList.add(new ListData("Chickpea Tuna Salad", "35 mins", "Chickpeas, Celery stalk, Small red onion, Capers, Dijon mustard, Vegan mayonnaise, Salt, Black pepper, Butter lettuce leaves, Large tomato, Handful of baby greens or sprouts,Soft sandwich bread, Nori sheet", "In a large bowl, add chickpeas and mash with a fork, Add chopped nori, celery, onion, capers, and mustard,mayonnaise, Mix until combined, On top of four bread slices, arrange lettuce, tomato, and baby greens, Divide chickpea salad onto each, Top with remaining slices of bread", R.drawable.pic2));
        dataArrayList.add(new ListData("Diner Style Pancakes", "20 mins", "Vegan milk, Apple cider vinegar, Canola oil, Vanilla extract, Flour, Baking soda, Baking powder, Salt, Sugar, Vegan chocolate chips, Fresh fruit", "Into a medium bowl, Add milk and vinegar, Mix well and set aside to thicken for 5 minutes, Whisk oil and vanilla into the milk mixture, Add flour, baking soda, baking powder, salt, and sugar, and combine, Add milk mixture to the flour mixture and whisk, Heat a frying pan over medium-high heat, Spray with non-stick cooking spray, Ladle the batter into pan and sprinkle on chocolate and fruit, After 2 minutes, flip and cook 2 minutes on the other side, Repeat process with remaining batter and serve immediately", R.drawable.pic3));
        dataArrayList.add(new ListData("Banana Oatmeal Porridge", "30 mins", "Rolled oats,Unsweetened almond milk, Cinnamon, Ground nutmeg, Salt, Ripe bananas, Brown sugar, Vanilla extract", "Add one and half cup of water, Into a medium-size saucepan over medium-high heat, Add oats, milk, cinnamon, nutmeg, and salt, and stir to combine, Bring the mixture to a boil, Reduce to a simmer and cook, Stirring regularly about 10 minutes, Add mashed bananas, brown sugar, and vanilla, Stir until combined and sugar has fully dissolved, another 2 to 3 minutes, Top with chopped walnuts, sliced banana, and a sprinkle of cinnamon", R.drawable.pic4));
        dataArrayList.add(new ListData("French Bread Pizza", "35 mins", "French bread, Pizza sauce, Shredded vegan mozzarella cheese, Bell pepper, Sweet onion, Pitted, Green olives, Dried oregano", "Preheat the oven to 400 degrees, Slice French bread in half horizontally, Place both pieces on a rimmed baking sheet, Spread pizza sauce evenly over both pieces of bread, Spread cheese over both halves of bread., Add bell pepper, onion, and green olives evenly across top, Sprinkle on remaining cheese and oregano, Bake for 12 to 15 minutes, Rotating baking sheet front to back halfway through, Until bread is crisp and cheese is melted", R.drawable.pic5));
        dataArrayList.add(new ListData("Tofu Egg Salad", "50 mins", "vegan mayonnaise, Yeast, Dijon mustard, Black salt, Ground turmeric, Extra firm tofu, Freshly chopped chives, Salt, Black pepper", "In a small mixing bowl, whisk mayonnaise, nutritional yeast, mustard, black salt, and turmeric, Add tofu to bowl, Mash with a fork to combine until an egg salad consistency is reached, Sprinkle with chopped chives, salt, and pepper and gently fold together", R.drawable.pic6));
        dataArrayList.add(new ListData("Sushi Rolls", "45 mins", "Sushi rice, Small beet, Rice vinegar,Sugar, Salt, Avocado, Yellow carrots, Persian cucumber, Block baked teriyaki tofu, Toasted nori sheets", "In a medium saucepan, Add rice and cups water, Cook according to package directions, Once cooked, transfer to a large bowl, With a spoon, incorporate beets, vinegar, sugar, and salt until sticky, Set aside to let cool, To form hand roll, Place nori in one hand, With damp hands, Spread cooked sushi rice evenly over half of nori in a square, Place small amount of fillings in a diagonal line across rice, Gently roll sushi into a cone shape", R.drawable.pic7));


        firebaseFirestore.collection("likes")
                .whereEqualTo("user", user)
                .whereEqualTo("liked",true)
                .get().addOnCompleteListener(task -> {
                    for (QueryDocumentSnapshot snapshot : task.getResult()) {
                        likeList.add(snapshot.toObject(ItemLike.class));
                    }


                    ItemsAdapter adapter = new ItemsAdapter(this,
                            dataArrayList,firebaseFirestore,user,likeList);

                    listView = findViewById(R.id.list);
                    listView.setAdapter(adapter);


                });



    }
}