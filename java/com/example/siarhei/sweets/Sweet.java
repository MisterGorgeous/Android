package com.example.siarhei.sweets;

import java.io.Serializable;


public class Sweet implements Serializable{

    private String name;
    private float price;
    private int iconId;
    private int extraInfo;
    private int tableIndex;



    public Sweet(String name, float price, int iconId, int extraInfo, int tableIndex) {
        this.name = name;
        this.price = price;
        this.iconId = iconId;
        this.extraInfo = extraInfo;
        this.tableIndex = tableIndex;
    }

    public float getPrice() {
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

    public int getTableIndex() {
        return tableIndex;
    }
}
