package com.example.a15017523.p05_ndpsongs;

import java.io.Serializable;

/**
 * Created by 15031795 on 23/5/2017.
 */

public class Song implements Serializable {
    private int id;
    private String singers;
    private int stars;
    private String title;
    private int year;

    public Song(int id, String title, String singers, int year, int stars) {
        this.id = id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int getId() {
        return this.id;
    }

    public Song setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public Song setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSingers() {
        return this.singers;
    }

    public Song setSingers(String singers) {
        this.singers = singers;
        return this;
    }

    public int getYear() {
        return this.year;
    }

    public Song setYear(int year) {
        this.year = year;
        return this;
    }

    public int getStars() {
        return this.stars;
    }

    public Song setStars(int stars) {
        this.stars = stars;
        return this;
    }
}
