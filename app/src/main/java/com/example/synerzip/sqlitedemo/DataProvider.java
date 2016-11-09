package com.example.synerzip.sqlitedemo;

/**
 * Created by synerzip on 17/3/16.
 */
public class DataProvider {
    private String name;
    private int id;
    private String mob;
    private String email;
    public DataProvider(int id,String name,String mob,String email)
    {
        this.name=name;
        this.mob=mob;
        this.email=email;
        this.id=id;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
