package com.example.myapplicationhaha;

public class ItemLike {
    String user;
    String item;
    boolean liked;

    public ItemLike() {
    }

    public ItemLike(String user, String item,boolean liked) {
        this.user = user;
        this.item = item;
        this.liked = liked;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
