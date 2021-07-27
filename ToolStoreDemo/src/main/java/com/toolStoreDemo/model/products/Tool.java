package com.toolStoreDemo.model.products;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toolStoreDemo.model.BaseModel;

/**
 * Model representing Tool database records
 */
public class Tool extends BaseModel {
    // Foreign Key fields
    /**
     * The foreign key for this tool's ToolType
     */
    @JsonProperty("typeId")
    private String typeId;

    /**
     * The foreign key for this tool's ToolBrand
     */
    @JsonProperty("brandId")
    private String brandId;

    // String fields
    /**
     * This tool's unique tool code
     */
    @JsonProperty("code")
    private String code;

    /**
     * Creates a default tool with out a type or brand that charges on every day
     */
    public Tool() {
        super();

        this.typeId = "";
        this.brandId = "";
        this.code = "";
    }

    /**
     * Creates a tool with the given type, brand, code, and charges
     * @param typeId Foreign key of the ToolType
     * @param brandId Foreign key of the ToolBrand
     * @param code Tool's unique code
     */
    public Tool(String typeId, String brandId, String code) {
        super();

        this.typeId = typeId;
        this.brandId = brandId;
        this.code = code;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
