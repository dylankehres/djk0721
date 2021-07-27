package com.toolStoreDemo.services;

import com.toolStoreDemo.model.products.Tool;
import com.toolStoreDemo.model.products.ToolBrand;
import com.toolStoreDemo.model.products.ToolType;
import com.toolStoreDemo.model.transaction.Transaction;
import com.toolStoreDemo.rental.RentalAgreement;
import com.toolStoreDemo.tables.calendar.EventDAO;
import com.toolStoreDemo.tables.calendar.EventDAS;
import com.toolStoreDemo.tables.products.*;
import com.toolStoreDemo.tables.transactions.TransactionDAO;
import com.toolStoreDemo.tables.transactions.TransactionDAS;

import java.time.LocalDate;

public class RentalAgreementService {
    private final ToolDAO toolDAO;
    private final ToolTypeDAO toolTypeDAO;
    private final ToolBrandDAO toolBrandDAO;
    private final TransactionDAO transactionDAO;
    private final EventDAO eventDAO;

    public RentalAgreementService() {
        // Initialize the necessary data access objects
        toolDAO = new ToolDAS();
        toolTypeDAO = new ToolTypeDAS();
        toolBrandDAO = new ToolBrandDAS();
        transactionDAO = new TransactionDAS();
        eventDAO = new EventDAS();
    }

    /**
     * Adds a tool with the given data to the database if it does not already exist
     * @param typeName Type of tool
     * @param brandName Brand name of tool
     * @param code Unique tool code
     * @param dailyCharge Amount to charge per day
     * @param weekdayCharge Charge for this tool on weekdays
     * @param weekendCharge Charge for this tool on weekends
     * @param holidayCharge Charge for this tool on holidays
     * @return Tool that was created
     */
    public Tool insertTool(String typeName, String brandName, String code, double dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        // Add a new tool brand to the database
        ToolBrand toolBrand = new ToolBrand(brandName);
        toolBrand = toolBrandDAO.insert(toolBrand);

        // Add a new tool type to the database
        ToolType toolType = new ToolType(typeName, dailyCharge, weekdayCharge, weekendCharge, holidayCharge);
        toolType = toolTypeDAO.insert(toolType);

        // Add a new tool to the database
        Tool tool = new Tool(toolType.getID(), toolBrand.getID(), code);
        tool = toolDAO.insert(tool);

        return tool;
    }

    /**
     * Create a rental agreement for a tool and print the agreement
     * @param toolCode Unique code for tool to rent
     * @param day Day to begin rental
     * @param month Month to begin rental
     * @param year Year to begin rental (yyyy)
     * @param rentalDays Number of days to rent out the tool
     * @param discount Amount to discount from the tool's daily charge
     * @return RentalAgreement for the tool rental
     */
    public RentalAgreement rentTool(String toolCode, int day, int month, int year, int rentalDays, int discount) throws Exception {
        if(rentalDays < 1) {
            throw new Exception("\nPlease enter a number of rental days that is greater than 0.\n");
        }

        if(discount < 0 || discount > 100) {
            throw new Exception("\nPlease enter a discount percentage that is between 0 and 100.\n");
        }

        RentalAgreement rentalAgreement = new RentalAgreement();
        Tool tool = toolDAO.selectByCode(toolCode);

        if(tool != null) {
            ToolType toolType = toolTypeDAO.selectById(tool.getTypeId());
            ToolBrand toolBrand = toolBrandDAO.selectById(tool.getBrandId());

            LocalDate rentalDate = LocalDate.of(year, month, day);
            Transaction transaction = new Transaction(tool.getID(), rentalDate.toEpochDay(), rentalDays, discount);
            transactionDAO.insert(transaction);

            rentalAgreement = new RentalAgreement(transaction, tool, toolType, toolBrand);
            rentalAgreement.buildAgreement(eventDAO.selectAllHolidays());
            rentalAgreement.printAgreement();
        }

        return rentalAgreement;
    }

    /**
     * Deletes tool and related type/brand if no longer used
     * @param toolCode Unique code of the tool to be deleted
     */
    public void deleteTool(String toolCode) {
        Tool tool = toolDAO.selectByCode(toolCode);

        if(tool != null && toolDAO.deleteById(tool.getID())) {

            if(toolDAO.selectByTypeId(tool.getTypeId()).isEmpty()) {
                toolTypeDAO.deleteById(tool.getTypeId());
            }

            if(toolDAO.selectByBrandId(tool.getBrandId()).isEmpty()) {
                toolBrandDAO.deleteById(tool.getBrandId());
            }
        }
    }

    /**
     * Delete a transaction with the given Id
     * @param transactionId Primary key of the transaction
     */
    public void deleteTransaction(String transactionId) {
        transactionDAO.deleteById(transactionId);
    }

}
