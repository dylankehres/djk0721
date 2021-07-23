package com.toolStoreDemo.tables.base;

import com.toolStoreDemo.model.BaseModel;

import java.util.ArrayList;

/**
 * Base interface for any Data Access Object implementation
 */
public interface BaseDAO<T extends BaseModel> {
    T insert(String id, T model);

    T insert(T model);

    void deleteById(String id);

    T update(T model);

    ArrayList<T> selectAll();

    T selectById(String id);
}
