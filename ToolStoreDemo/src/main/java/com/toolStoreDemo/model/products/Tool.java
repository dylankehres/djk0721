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

    // double fields
    /**
     * The amount of dollars that should be charged each day this tool is rented
     */
    @JsonProperty("dailyCharge")
    private double dailyCharge;

    // boolean fields
    /**
     * The client should be charged each weekday the tool is rented
     */
    @JsonProperty("weekdayCharge")
    private boolean weekdayCharge;

    /**
     * The client should be charged on weekends that the tool is rented
     */
    @JsonProperty("weekendCharge")
    private boolean weekendCharge;

    /**
     * The client should be charged each holiday the tool is rented
     */
    @JsonProperty("holidayCharge")
    private boolean holidayCharge;

    /**
     * The tool is out on rent and is not available
     */
    @JsonProperty("rented")
    private boolean rented;

    /**
     * Creates a default tool with out a type or brand that charges on every day
     */
    public Tool() {
        super();

        this.typeId = "";
        this.brandId = "";
        this.code = "";
        this.dailyCharge = 0.00;
        this.weekdayCharge = true;
        this.weekendCharge = true;
        this.holidayCharge = true;
        this.rented = false;
    }

    /**
     * Creates a tool with the given type, brand, code, and charges
     * @param typeId Foreign key of the ToolType
     * @param brandId Foreign key of the ToolBrand
     * @param code Tool's unique code
     * @param dailyCharge Amount of dollars to charge each day the tool is rented
     * @param weekdayCharge The client should be charged each weekday the tool is rented
     * @param weekendCharge The client should be charged on weekends that the tool is rented
     * @param holidayCharge The client should be charged each holiday the tool is rented
     */
    public Tool(String typeId, String brandId, String code, double dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        super();

        this.typeId = typeId;
        this.brandId = brandId;
        this.code = code;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
        this.rented = false;
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

    public double getDailyCharge() {
        return dailyCharge;
    }

    public void setDailyCharge(double dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    public void setWeekdayCharge(boolean weekdayCharge) {
        this.weekdayCharge = weekdayCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    public void setWeekendCharge(boolean weekendCharge) {
        this.weekendCharge = weekendCharge;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }

    public void setHolidayCharge(boolean holidayCharge) {
        this.holidayCharge = holidayCharge;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }
}
