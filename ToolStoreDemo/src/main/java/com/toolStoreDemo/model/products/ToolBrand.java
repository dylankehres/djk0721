package com.toolStoreDemo.model.products;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toolStoreDemo.model.BaseModel;

/**
 * Model representing ToolBrand database records
 */
public class ToolBrand extends BaseModel {
    /**
     * The name of the brand that sells a tool
     */
    @JsonProperty("brandName")
    private String brandName;

    /**
     * Creates a default tool brand with a blank name
     */
    public ToolBrand() {
        super();

        this.brandName = "";
    }

    /**
     * Creates a tool brand with the given name
     *
     * @param brandName Name of the brand that sells a tool
     */
    public ToolBrand(String brandName) {
        super();

        this.brandName = brandName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

}
