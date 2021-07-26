package com.toolStoreDemo.services;

public class Main {

    public static void main(String[] args) {
	    // Initialize the connection to the firebase database
        FirebaseInit.init();

        RentalAgreementService rentalAgreementService = new RentalAgreementService();

        rentalAgreementService.insertTool("Ladder", "Werner","LADW", 2.00, true, true, false);
        rentalAgreementService.rentTool("LADW", 3, 9, 2015, 5, 50);
        rentalAgreementService.deleteTool("LADW");
    }
}
