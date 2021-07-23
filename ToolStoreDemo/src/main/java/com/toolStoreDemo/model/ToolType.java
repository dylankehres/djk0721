package com.toolStoreDemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model representing ToolType database records
 */
public class ToolType extends BaseModel{
    @JsonProperty("typeName") private String typeName;

    public ToolType() {
        super();

        this.typeName = "";
    }

    public ToolType(String typeName) {
        super();

        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
