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
import java.util.ArrayList;

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

    public Tool insertTool(String typeName, String brandName, String code, double dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        // Add a new tool brand to the database
        ToolBrand toolBrand = new ToolBrand(brandName);
        toolBrand = toolBrandDAO.insert(toolBrand);

        // Add a new tool type to the database
        ToolType toolType = new ToolType(typeName);
        toolType = toolTypeDAO.insert(toolType);

        // Add a new tool to the database
        Tool tool = new Tool(toolType.getID(), toolBrand.getID(), code, dailyCharge, weekdayCharge, weekendCharge, holidayCharge);
        tool = toolDAO.insert(tool);

        return tool;
    }

    public RentalAgreement rentTool(String toolCode, int day, int month, int year, int rentalDays, int discount) {
        RentalAgreement rentalAgreement = new RentalAgreement();
        Tool tool = toolDAO.selectByCode(toolCode);

        if(tool != null) {
            ToolType toolType = toolTypeDAO.selectById(tool.getTypeKey());
            ToolBrand toolBrand = toolBrandDAO.selectById(tool.getBrandKey());

            LocalDate rentalDate = LocalDate.of(year, month, day);
            Transaction transaction = new Transaction(tool.getID(), rentalDate.toEpochDay(), rentalDays, discount);
            transactionDAO.insert(transaction);


            rentalAgreement = new RentalAgreement(transaction, tool, toolType, toolBrand);
            rentalAgreement.buildAgreement(eventDAO.selectAllHolidays());
            rentalAgreement.printAgreement();
        }

        return rentalAgreement;
    }

    public void deleteTool(String toolCode) {
        Tool tool = toolDAO.selectByCode(toolCode);

        if(tool != null && toolDAO.deleteById(tool.getID())) {

            if(toolDAO.selectByTypeKey(tool.getTypeKey()).isEmpty()) {
                toolTypeDAO.deleteById(tool.getTypeKey());
            }

            if(toolDAO.selectByBrandKey(tool.getBrandKey()).isEmpty()) {
                toolBrandDAO.deleteById(tool.getBrandKey());
            }
        }
    }

    public void deleteTransaction(String transactionId) {
        transactionDAO.deleteById(transactionId);
    }

}
