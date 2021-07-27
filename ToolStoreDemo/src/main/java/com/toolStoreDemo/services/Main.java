package com.toolStoreDemo.services;

import com.toolStoreDemo.model.calendar.RecurrencePattern;
import com.toolStoreDemo.model.products.Tool;
import com.toolStoreDemo.rental.RentalAgreement;

public class Main {

    public static void main(String[] args) {
	    // Initialize the connection to the firebase database
        FirebaseInit.init();

        // Initialize services
        RentalAgreementService rentalAgreementService = new RentalAgreementService();
        CalendarService calendarService = new CalendarService();


        // Add holidays
        RecurrencePattern laborDay = calendarService.insertHolidayOnDayOfMonth(1, 1, 9, 2015, 6);
        RecurrencePattern forthOfJuly = calendarService.insertHolidayOnDate(4, 7, 2015, 6, true);

        // Add tool data
        Tool tool = rentalAgreementService.insertTool("Ladder", "Werner","LADW", 1.99, true, true, false);


        RentalAgreement rentalAgreement = new RentalAgreement();
        try {
            // Process rental agreement
            rentalAgreement = rentalAgreementService.rentTool("LADW", 2, 7, 2020, 3, 101);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // Clean up database
            calendarService.deleteRecurrenceAndEvents(laborDay.getID());
            calendarService.deleteRecurrenceAndEvents(forthOfJuly.getID());
            rentalAgreementService.deleteTool(tool.getCode());

            if(!rentalAgreement.getTransaction().getID().isEmpty()) {
                rentalAgreementService.deleteTransaction(rentalAgreement.getTransaction().getID());
            }
        }
    }
}
