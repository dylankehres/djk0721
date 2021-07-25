package com.toolStoreDemo.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toolStoreDemo.model.BaseModel;

import java.util.Calendar;

/**
 * Model used to represent the rental of a tool
 */
public class Transaction extends BaseModel {
    /**
     * The foreign key for tool on the transaction
     */
    @JsonProperty("toolKey")
    private String toolKey;

    /**
     * Number of seconds since January 1, 1970 and the date the tool was rented
     */
    @JsonProperty("checkoutDate")
    private long checkoutDate;

    /**
     * Number of days the tool will be out on rental
     */
    @JsonProperty("checkoutDate")
    private int rentalDays;

    /**
     * Amount of the daily charge that should be discounted from 0 to 100
     */
    @JsonProperty("discount")
    private int discount;

    /**
     * Creates a default transaction on January 1, 1970 for a blank tool key for 0 days at full price
     */
    public Transaction() {
        super();

        this.toolKey = "";
        this.checkoutDate = Calendar.getInstance().getTime().getTime();
        this.rentalDays = 0;
        this.discount = 0;
    }

    /**
     * Creates a transaction on the given date for given number of days at a given discount
     * @param toolKey The primary key of the tool on the transaction
     * @param checkoutDate Number of seconds since January 1, 1970 and the date the tool was rented
     * @param rentalDays Number of days the tool will be out on rental
     * @param discount Amount of the daily charge that should be discounted from 0 to 100
     */
    public Transaction(String toolKey, long checkoutDate, int rentalDays, int discount) {
        super();

        this.toolKey = toolKey;
        this.checkoutDate = checkoutDate;
        this.rentalDays = rentalDays;
        this.discount = discount;
    }

    public String getToolKey() {
        return toolKey;
    }

    public void setToolKey(String toolKey) {
        this.toolKey = toolKey;
    }

    public long getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(long checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
