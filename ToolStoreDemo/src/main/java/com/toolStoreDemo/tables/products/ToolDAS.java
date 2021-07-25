package com.toolStoreDemo.tables.products;

import com.toolStoreDemo.model.products.Tool;
import com.toolStoreDemo.tables.base.FirebaseDAO;

/**
 * Data Access Service used to manage Tool database records
 */
public class ToolDAS extends FirebaseDAO<Tool> implements ToolDAO{
    // Table name in Firebase
    static final String collection = "tool";

    public ToolDAS() {
        super(collection, Tool.class);
    }
}
