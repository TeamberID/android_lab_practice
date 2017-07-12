package ru.startandroid.cooking;

import android.view.View;

import java.io.Serializable;

/**
 * Created by Asus on 10.07.2017.
 */

public class Dish  {


    private String name;
    private String difficult;
    private String time;
    private String recepie;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficult() {
        return difficult;
    }

    public void setDifficult(String difficult) {
        this.difficult = difficult;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRecepie() {
        return recepie;
    }

    public void setRecepie(String recepie) {
        this.recepie = recepie;
    }

    public Dish() {
        this.name = name;
        this.difficult = difficult;
        this.time = time;
        this.recepie = recepie;
    }
}
