package com.example.myapplicationhaha;

import java.io.Serializable;

public class ListData implements Serializable {
    String name, time;
    String ingredients, desc;
    int image;
    boolean liked;
    public ListData(String name, String time, String ingredients, String desc, int image) {
        this.name = name;
        this.time = time;
        this.ingredients = ingredients;
        this.desc = desc;
        this.image = image;
        liked = false;
    }
}