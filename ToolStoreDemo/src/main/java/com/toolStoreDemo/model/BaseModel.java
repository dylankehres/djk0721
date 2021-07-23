package com.toolStoreDemo.model;

public class BaseModel {
    private String id;
    private boolean active;

    public BaseModel() {
        this.id = "";
        this.active = true;
    }

    public String getID() {
        return this.id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
