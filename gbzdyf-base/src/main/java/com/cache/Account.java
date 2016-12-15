package com.cache;

/**
 * Created by y on 2016/12/15.
 */
public class Account {

    private int id;
    private String name;

    private Account() {}

    public Account(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
