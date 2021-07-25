package com.toolStoreDemo.model.products;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toolStoreDemo.model.BaseModel;

/**
 * Model representing ToolType database records
 */
public class ToolType extends BaseModel {
    /**
     * The name of a specific type of tool
     */
    @JsonProperty("typeName")
    private String typeName;

    /**
     * Created a default ToolType with a blank name
     */
    public ToolType() {
        super();

        this.typeName = "";
    }

    /**
     * Creates a ToolType with the given name
     *
     * @param typeName Name of the specific type of tool
     */
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
