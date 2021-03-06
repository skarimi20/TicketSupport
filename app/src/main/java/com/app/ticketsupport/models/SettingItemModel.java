package com.app.ticketsupport.models;

import android.graphics.drawable.Drawable;

public class SettingItemModel {

    public SettingItemModel(String name, Drawable drawable) {
        this.name = name;
        this.drawable = drawable;
    }

    private String name;
    private Drawable drawable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
