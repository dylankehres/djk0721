package com.toolStoreDemo.rental;

import com.toolStoreDemo.model.calendar.Event;
import com.toolStoreDemo.model.products.Tool;
import com.toolStoreDemo.model.products.ToolBrand;
import com.toolStoreDemo.model.products.ToolType;
import com.toolStoreDemo.model.transaction.Transaction;

import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RentalAgreement {
    private final Transaction transaction;
    private final Tool tool;
    private final ToolType toolType;
    private final ToolBrand toolBrand;
    private int chargeDays;
    private double preDiscountCharge;
    private double discountAmount;
    private double finalCharge;

    /**
     * Created a default rental agreement with a default transaction
     */
    public RentalAgreement() {
        this.transaction = new Transaction();
        this.tool = new Tool();
        this.toolType = new ToolType();
        this.toolBrand = new ToolBrand();
        this.chargeDays = 0;
        this.preDiscountCharge = 0;
        this.discountAmount = 0;
        this.finalCharge = 0;
    }

    /**
     * Creates a rental agreement for the given transaction data
     * @param transaction Transaction to create agreement for
     * @param tool Tool on the transaction
     * @param toolType Type of the tool on the transaction
     * @param toolBrand Brand of the tool on the transaction
     */
    public RentalAgreement(Transaction transaction, Tool tool, ToolType toolType, ToolBrand toolBrand) {
        this.transaction = transaction;
        this.tool = tool;
        this.toolType = toolType;
        this.toolBrand = toolBrand;
        this.chargeDays = 0;
        this.preDiscountCharge = 0;
        this.discountAmount = 0;
        this.finalCharge = 0;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Tool getTool() {
        return tool;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public ToolBrand getToolBrand() {
        return toolBrand;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public double getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getFinalCharge() {
        return finalCharge;
    }

    /**
     * Calculates the date that the tool should be returned
     * @return Date for tool return
     */
    private LocalDate calcDueDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(transaction.getCheckoutDate()));
        calendar.add(Calendar.DATE, transaction.getRentalDays());

        return LocalDate.ofEpochDay(transaction.getCheckoutDate()).plusDays(transaction.getRentalDays());
    }

    /**
     * Calculates the number of holiday days that should be charged for the tool
     * @param holidays List of Events that are holidays
     * @return List of LocalDates that should observe a holiday
     */
    private ArrayList<LocalDate> buildHolidayChargeDates(ArrayList<Event> holidays) {
        ArrayList<LocalDate> holidayChargeDates = new ArrayList<>();

        LocalDate date;
        for(Event holiday: holidays) {
            date = LocalDate.ofEpochDay(holiday.getDateTime());

            if(holiday.isObserveWeekday()) {
                if(date.getDayOfWeek() == DayOfWeek.SATURDAY) {
                    holidayChargeDates.add(date.minusDays(1));
                    continue;
                } else if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    holidayChargeDates.add(date.plusDays(1));
                    continue;
                }
            }

            holidayChargeDates.add(date);
        }

        return holidayChargeDates;
    }

    /**
     * Formats date as mm/dd/yy
     * @param date LocalDate to format
     */
    private String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        return date.format(formatter);
    }

    /**
     * Formats date as mm/dd/yy
     * @param dateTime Number of seconds since January 1, 1970
     */
    private String formatDate(long dateTime) {
        LocalDate date = LocalDate.ofEpochDay(dateTime);
        return formatDate(date);
    }

    /**
     * Formats the currency amount to $9,999.99
     * @param amount Amount of dollars
     * @return Formatted amount
     */
    private String formatCurrency(double amount) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(amount);
    }

    /**
     * Formats integer percentage to 99%
     * @param percentage Percentage to format
     * @return Formatted percentage
     */
    private String formatPercentage(int percentage) {
        return percentage + "%" ;
    }

    /**
     * Determines if the date falls on a weekend
     * @param date LocalDate to check
     * @return The date is a weekend
     */
    private boolean dateIsWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    /**
     * Calculates the total number of days to charge for the tool
     * @param holidays List of holidays on the store calendar
     * @return Number of days to charge for the tool
     */
    private int calcChargeDays(ArrayList<Event> holidays) {
        int chargeDays = 0;
        LocalDate rentalDate = LocalDate.ofEpochDay(transaction.getCheckoutDate());
        long daysBetween = ChronoUnit.DAYS.between(rentalDate, calcDueDate());

        ArrayList<LocalDate> holidayChargeDates = buildHolidayChargeDates(holidays);

        boolean isWeekend;
        boolean dayProcessed;
        for(int dayIterate = 0; dayIterate < daysBetween; dayIterate++) {
            dayProcessed = false;
            isWeekend = dateIsWeekend(rentalDate);

            // If the day is a holiday and we charge, continue on to check if the day of the week is charged
            if(holidayChargeDates.contains(rentalDate) && !tool.isHolidayCharge()) {
                dayProcessed = true;
            }

            if(!dayProcessed && tool.isWeekdayCharge() && !isWeekend) {
                chargeDays++;
                dayProcessed = true;
            }

            if(!dayProcessed && tool.isWeekendCharge() && isWeekend) {
                chargeDays++;
            }

            rentalDate = rentalDate.plusDays(1);
        }

        return chargeDays;
    }

    /**
     * Calculate the charge before applying the discount
     * @return Amount to charge in dollars
     */
    private double calcPreDiscountCharge() {
        return this.chargeDays * this.tool.getDailyCharge();
    }

    /**
     * Calculates the amount of money saved by the discount
     * @return Amount of money saved by the discount
     */
    private double calcDiscountAmount() {
        return this.preDiscountCharge * this.transaction.getDiscount() / 100;
    }

    /**
     * Calculates the amount the client will pay
     * @return Number of dollars the client will pay
     */
    private double calcFinalCharge() {
        return this.preDiscountCharge - this.discountAmount;
    }

    /**
     * Calculates all charges for the rental agreement
     * @param holidays List of holidays on the store calendar
     */
    public void buildAgreement(ArrayList<Event> holidays) {
        chargeDays = calcChargeDays(holidays);
        preDiscountCharge = calcPreDiscountCharge();
        discountAmount = calcDiscountAmount();
        finalCharge = calcFinalCharge();
    }

    /**
     * Prints values on the rental agreement
     */
    public void printAgreement() {
        System.out.println("Tool code: " + this.tool.getCode());
        System.out.println("Tool type: " + this.toolType.getTypeName());
        System.out.println("Tool brand: " + this.toolBrand.getBrandName());
        System.out.println("Rental days: " + this.transaction.getRentalDays());
        System.out.println("Check out date: " + formatDate(this.transaction.getCheckoutDate()));
        System.out.println("Due date: " + formatDate(calcDueDate()));
        System.out.println("Daily rental charge: " + formatCurrency(tool.getDailyCharge()));
        System.out.println("Charge days: " + chargeDays);
        System.out.println("Pre-discount charge: " + formatCurrency(preDiscountCharge));
        System.out.println("Discount percent: " + formatPercentage(transaction.getDiscount()));
        System.out.println("Discount amount: " + formatCurrency(discountAmount));
        System.out.println("Final charge: " + formatCurrency(finalCharge));
    }
}
