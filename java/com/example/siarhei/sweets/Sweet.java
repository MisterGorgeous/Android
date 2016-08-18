package com.example.siarhei.sweets;

/**
 * Created by Siarhei on 18.08.2016.
 */
public class Sweet{
    private String name;
    private int price;
    private int iconId;
    private String extraInfo;

    public Sweet(String name, int price, int iconId, String extraInfo) {
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

    public String getExtraInfo() {
        return extraInfo;
    }
}
