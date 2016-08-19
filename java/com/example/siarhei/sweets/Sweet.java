package com.example.siarhei.sweets;

import java.io.Serializable;


public class Sweet implements Serializable{

    private String name;
    private int price;
    private int iconId;
    private int extraInfo;

    public Sweet(String name, int price, int iconId, int extraInfo) {
        this.name = name;
        this.price = price;
        this.iconId = iconId;
        this.extraInfo = extraInfo;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getIconId() {
        return iconId;
    }

    public int getExtraInfo() {
        return extraInfo;
    }
}
