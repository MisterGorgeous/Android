package com.example.siarhei.sweets;

import java.io.Serializable;


public class Sweet implements Serializable{

    private String name;
    private float price;
    private int iconId;
    private int extraInfo;
    private int tableIndex;
    private int typeOfSweet;
    private int amount;


    public Sweet(String name, float price, int iconId, int extraInfo, int tableIndex, int typeOfSweet, int amount) {
        this.name = name;
        this.price = price;
        this.iconId = iconId;
        this.extraInfo = extraInfo;
        this.tableIndex = tableIndex;

        this.typeOfSweet = typeOfSweet;
        this.amount = amount;
    }

    public int getAmount() { return amount; }

    public void setAmount(int amount) { this.amount = amount; }

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

    public int getTypeOfSweet() {return typeOfSweet;}

    public void setPrice(float price) { this.price = price; }
}
