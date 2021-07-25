package com.toolStoreDemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseModel {
    /**
     * Primary key of the model in the database
     */
    @JsonProperty("id")
    private String id;

    /**
     * This model should be accessible by default
     */
    @JsonProperty("active")
    private boolean active;

    /**
     * Creates a default model that is active with a blank id
     */
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
