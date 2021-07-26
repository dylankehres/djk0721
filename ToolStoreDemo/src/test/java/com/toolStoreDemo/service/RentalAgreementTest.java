package com.toolStoreDemo.service;

import com.toolStoreDemo.model.calendar.RecurrencePattern;
import com.toolStoreDemo.model.products.Tool;
import com.toolStoreDemo.services.CalendarService;
import com.toolStoreDemo.services.FirebaseInit;
import com.toolStoreDemo.services.RentalAgreementService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class RentalAgreementTest {
    RentalAgreementService rentalAgreementService;
    CalendarService calendarService;
    ArrayList<Tool> testTools = new ArrayList<>();
    ArrayList<RecurrencePattern> testHolidays = new ArrayList<>();

    @BeforeAll
    void setup() {
        // Initialize the connection to the firebase database
        FirebaseInit.init();

        // Initialize necessary services
        rentalAgreementService = new RentalAgreementService();
        calendarService = new CalendarService();

        // Add test data
        testTools.add(rentalAgreementService.insertTool("Ladder", "Werner","LADW", 1.99, true, true, false));
        testTools.add(rentalAgreementService.insertTool("Chainsaw", "Stihl","CHNS", 1.49, true, false, true));
        testTools.add(rentalAgreementService.insertTool("Jackhammer", "Rigid","JAKR", 2.99, true, false, false));
        testTools.add(rentalAgreementService.insertTool("Jackhammer", "DeWalt","JAKD", 2.99, true, false, false));

        // Add holidays
        testHolidays.add(calendarService.insertHolidayOnDate(4, 7, 2015, 5, true));
        testHolidays.add(calendarService.insertHolidayOnDayOfMonth(1, 1, 9, 2015, 5));
    }

    @AfterAll
    void teardown() {
        for(Tool tool : this.testTools) {
            rentalAgreementService.deleteTool(tool.getCode());
        }

        for(RecurrencePattern holiday : this.testHolidays) {
            calendarService.deleteRecurrenceAndEvents(holiday.getID());
        }
    }

    @Test
    void testRentalAgreement() {

    }
}
