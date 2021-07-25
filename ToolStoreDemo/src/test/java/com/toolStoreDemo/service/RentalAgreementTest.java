package com.toolStoreDemo.service;

import com.toolStoreDemo.model.products.Tool;
import com.toolStoreDemo.services.FirebaseInit;
import com.toolStoreDemo.services.RentalAgreementService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class RentalAgreementTest {
    RentalAgreementService rentalAgreementService;
    ArrayList<Tool> testTools = new ArrayList<>();

    @BeforeAll
    void setup() {
        // Initialize the connection to the firebase database
        FirebaseInit.init();

        // Initialize necessary services
        rentalAgreementService = new RentalAgreementService();

        // Add test data
        rentalAgreementService.insertTool("Ladder", "Werner","LADW", 1.99, true, true, false);
        rentalAgreementService.insertTool("Chainsaw", "Stihl","CHNS", 1.49, true, false, true);
        rentalAgreementService.insertTool("Jackhammer", "Rigid","JAKR", 2.99, true, false, false);
        rentalAgreementService.insertTool("Jackhammer", "DeWalt","JAKD", 2.99, true, false, false);
    }

    @AfterAll
    void teardown() {
        rentalAgreementService.deleteTool("LADW");
        rentalAgreementService.deleteTool("CHNS");
        rentalAgreementService.deleteTool("JAKR");
        rentalAgreementService.deleteTool("JAKD");
    }

    @Test
    void testRentalAgreement() {

    }
}
