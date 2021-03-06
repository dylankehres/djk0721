package com.toolStoreDemo.tables.products;

import com.toolStoreDemo.model.products.Tool;

import java.util.ArrayList;

/**
 * Data Access Object for Tool database records
 */
public interface ToolDAO {
    Tool insert(String id, Tool tool);

    Tool insert(Tool tool);

    boolean deleteById(String id);

    Tool update(Tool tool);

    ArrayList<Tool> selectAll();

    Tool selectById(String id);

    Tool selectByCode(String toolCode);

    ArrayList<Tool> selectByBrandId(String brandId);

    ArrayList<Tool> selectByTypeId(String typeId);
}
