package com.toolStoreDemo.tables.transactions;

import com.toolStoreDemo.model.transaction.Transaction;

import java.util.ArrayList;

/**
 * Data Access Object for Transaction database records
 */
public interface TransactionDAO {
    Transaction insert(String id, Transaction transaction);

    Transaction insert(Transaction transaction);

    boolean deleteById(String id);

    Transaction update(Transaction transaction);

    ArrayList<Transaction> selectAll();

    Transaction selectById(String id);
}
