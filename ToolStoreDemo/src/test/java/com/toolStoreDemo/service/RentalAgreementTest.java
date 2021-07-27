package com.toolStoreDemo.service;

import com.toolStoreDemo.model.calendar.RecurrencePattern;
import com.toolStoreDemo.model.products.Tool;
import com.toolStoreDemo.model.transaction.Transaction;
import com.toolStoreDemo.rental.RentalAgreement;
import com.toolStoreDemo.services.CalendarService;
import com.toolStoreDemo.services.FirebaseInit;
import com.toolStoreDemo.services.RentalAgreementService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RentalAgreementTest {
    RentalAgreementService rentalAgreementService;
    CalendarService calendarService;
    ArrayList<Tool> testTools = new ArrayList<>();
    ArrayList<RecurrencePattern> testHolidays = new ArrayList<>();
    ArrayList<RentalAgreement> testRentals = new ArrayList<>();

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
        testHolidays.add(calendarService.insertHolidayOnDate(4, 7, 2015, 6, true));
        testHolidays.add(calendarService.insertHolidayOnDayOfMonth(1, 1, 9, 2015, 6));
    }

    @AfterAll
    void teardown() {
        for(Tool tool : this.testTools) {
            rentalAgreementService.deleteTool(tool.getCode());
        }

        for(RentalAgreement rental : this.testRentals) {
            rentalAgreementService.deleteTransaction(rental.getTransaction().getID());
        }

        for(RecurrencePattern holiday : this.testHolidays) {
            calendarService.deleteRecurrenceAndEvents(holiday.getID());
        }
    }

    @Test
    void test1() {
        RentalAgreement rentalAgreement = rentalAgreementService.rentTool("JAKR", 3, 9, 2015, 5, 101);
        testRentals.add(rentalAgreement);

        assertEquals(2, rentalAgreement.getChargeDays());
        assertEquals(5.98, rentalAgreement.getPreDiscountCharge());
        assertEquals(5.98, rentalAgreement.getDiscountAmount());
        assertEquals(0, rentalAgreement.getFinalCharge());
    }

    @Test
    void test2() {
        RentalAgreement rentalAgreement = rentalAgreementService.rentTool("LADW", 2, 7, 2020, 3, 10);
        testRentals.add(rentalAgreement);

        assertEquals(2, rentalAgreement.getChargeDays());
        assertEquals(3.98, rentalAgreement.getPreDiscountCharge());
        assertEquals(3.98 * 10 / 100, rentalAgreement.getDiscountAmount());
        assertEquals(3.98 - (3.98 * 10 / 100), rentalAgreement.getFinalCharge());
    }

    @Test
    void test3() {
        RentalAgreement rentalAgreement = rentalAgreementService.rentTool("CHNS", 2, 7, 2015, 5, 25);
        testRentals.add(rentalAgreement);

        assertEquals(3, rentalAgreement.getChargeDays());
        assertEquals(4.47, rentalAgreement.getPreDiscountCharge());
        assertEquals(1.1175, rentalAgreement.getDiscountAmount());
        assertEquals(3.3525, rentalAgreement.getFinalCharge());
    }

    @Test
    void test4() {
        RentalAgreement rentalAgreement = rentalAgreementService.rentTool("JAKD", 3, 9, 2015, 6, 0);
        testRentals.add(rentalAgreement);

        assertEquals(3, rentalAgreement.getChargeDays());
        assertEquals(8.97, rentalAgreement.getPreDiscountCharge());
        assertEquals(0, rentalAgreement.getDiscountAmount());
        assertEquals(8.97, rentalAgreement.getFinalCharge());
    }

    @Test
    void test5() {
        RentalAgreement rentalAgreement = rentalAgreementService.rentTool("JAKR", 2, 7, 2015, 9, 0);
        testRentals.add(rentalAgreement);

        assertEquals(6, rentalAgreement.getChargeDays());
        assertEquals(17.94, rentalAgreement.getPreDiscountCharge());
        assertEquals(0, rentalAgreement.getDiscountAmount());
        assertEquals(17.94, rentalAgreement.getFinalCharge());
    }

    @Test
    void test6() {
        RentalAgreement rentalAgreement = rentalAgreementService.rentTool("JAKR", 2, 7, 2020, 4, 50);
        testRentals.add(rentalAgreement);

        assertEquals(1, rentalAgreement.getChargeDays());
        assertEquals(2.99, rentalAgreement.getPreDiscountCharge());
        assertEquals(1.495, rentalAgreement.getDiscountAmount());
        assertEquals(1.495, rentalAgreement.getFinalCharge());
    }
}
