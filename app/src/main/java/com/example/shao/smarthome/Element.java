package com.example.shao.smarthome;

/**
 * Created by Shao on 2018/3/25.
 */

public class Element {
    private String name;
    private int imageId;
    public Element(String name,int imageId){
        this.name=name;
        this.imageId=imageId;
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
}
