package com.toolStoreDemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model representing Tool database records
 */
public class Tool extends BaseModel {
    // Foreign Key fields
    @JsonProperty("typeKey") private String typeKey;
    @JsonProperty("brandKey") private String brandKey;

    // String fields
    @JsonProperty("code") private String code;

    // double fields
    @JsonProperty("dailyCharge") private double dailyCharge;

    // boolean fields
    @JsonProperty("weekdayCharge") private boolean weekdayCharge;
    @JsonProperty("weekendCharge") private boolean weekendCharge;
    @JsonProperty("holidayCharge") private boolean holidayCharge;

    public Tool() {
        super();

        this.typeKey = "";
        this.brandKey = "";
        this.code = "";
        this.dailyCharge = 0.00;
        this.weekdayCharge = true;
        this.weekendCharge = true;
        this.holidayCharge = true;
    }

    public Tool(String typeKey, String brandKey, String code, double dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        super();

        this.typeKey = typeKey;
        this.brandKey = brandKey;
        this.code = code;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
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
}
