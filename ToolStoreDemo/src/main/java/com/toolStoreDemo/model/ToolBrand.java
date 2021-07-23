package com.toolStoreDemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model representing ToolBrand database records
 */
public class ToolBrand extends BaseModel {
    @JsonProperty("brandName") private String brandName;

    public ToolBrand() {
        super();

        this.brandName = "";
    }

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
