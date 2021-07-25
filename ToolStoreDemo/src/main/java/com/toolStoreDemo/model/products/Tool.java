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
    @JsonProperty("typeKey")
    private String typeKey;

    /**
     * The foreign key for this tool's ToolBrand
     */
    @JsonProperty("brandKey")
    private String brandKey;

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

        this.typeKey = "";
        this.brandKey = "";
        this.code = "";
        this.dailyCharge = 0.00;
        this.weekdayCharge = true;
        this.weekendCharge = true;
        this.holidayCharge = true;
        this.rented = false;
    }

    /**
     * Creates a tool with the given type, brand, code, and charges
     * @param typeKey Foreign key of the ToolType
     * @param brandKey Foreign key of the ToolBrand
     * @param code Tool's unique code
     * @param dailyCharge Amount of dollars to charge each day the tool is rented
     * @param weekdayCharge The client should be charged each weekday the tool is rented
     * @param weekendCharge The client should be charged on weekends that the tool is rented
     * @param holidayCharge The client should be charged each holiday the tool is rented
     */
    public Tool(String typeKey, String brandKey, String code, double dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        super();

        this.typeKey = typeKey;
        this.brandKey = brandKey;
        this.code = code;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
        this.rented = false;
    }

    public String getTypeKey() {
        return typeKey;
    }

    public void setTypeKey(String typeKey) {
        this.typeKey = typeKey;
    }

    public String getBrandKey() {
        return brandKey;
    }

    public void setBrandKey(String brandKey) {
        this.brandKey = brandKey;
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
