package com.toolStoreDemo.tables.products;

import com.toolStoreDemo.model.ToolType;

import java.util.ArrayList;

/**
 * Data Access Object for ToolType database records
 */
public interface ToolTypeDAO {
    ToolType insert(String id, ToolType toolType);

    ToolType insert(ToolType toolType);

    void deleteById(String id);

    ToolType update(ToolType tool);

    ArrayList<ToolType> selectAll();

    ToolType selectById(String id);
}
