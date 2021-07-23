package com.toolStoreDemo.services;

import com.toolStoreDemo.model.Tool;
import com.toolStoreDemo.model.ToolBrand;
import com.toolStoreDemo.model.ToolType;
import com.toolStoreDemo.tables.products.*;

public class Main {

    public static void main(String[] args) {
	    // Initialize the connection to the firebase database
        FirebaseInit.init();

        // Initialize the necessary data access objects
        ToolDAO toolDAO = new ToolDAS();
        ToolTypeDAO toolTypeDAO = new ToolTypeDAS();
        ToolBrandDAO toolBrandDAO = new ToolBrandDAS();

        // Add a new tool brand to the database
        ToolBrand toolBrand = new ToolBrand("Werner");
        toolBrand = toolBrandDAO.insert(toolBrand);

        // Add a new tool type to the database
        ToolType toolType = new ToolType("Ladder");
        toolType = toolTypeDAO.insert(toolType);

        // Add a new tool to the database
        Tool tool = new Tool(toolBrand.getID(), toolType.getID(), "LADW", 1.99, true, true, false);
        tool = toolDAO.insert(tool);
    }
}
