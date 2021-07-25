package com.toolStoreDemo.tables.products;

import com.toolStoreDemo.model.products.ToolBrand;

import java.util.ArrayList;

/**
 * Data Access Object for ToolBrand database records
 */
public interface ToolBrandDAO {
    ToolBrand insert(String id, ToolBrand toolBrand);

    ToolBrand insert(ToolBrand toolBrand);

    void deleteById(String id);

    ToolBrand update(ToolBrand toolBrand);

    ArrayList<ToolBrand> selectAll();

    ToolBrand selectById(String id);
}
