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
     * Created a default ToolType with a blank name
     */
    public ToolType() {
        super();

        this.typeName = "";
        this.dailyCharge = 0.00;
        this.weekdayCharge = true;
        this.weekendCharge = true;
        this.holidayCharge = true;
    }

    /**
     * Creates a ToolType with the given name
     *
     * @param typeName Name of the specific type of tool
     * @param dailyCharge Amount of dollars to charge each day the tool is rented
     * @param weekdayCharge The client should be charged each weekday the tool is rented
     * @param weekendCharge The client should be charged on weekends that the tool is rented
     * @param holidayCharge The client should be charged each holiday the tool is rented
     */
    public ToolType(String typeName, double dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        super();

        this.typeName = typeName;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
