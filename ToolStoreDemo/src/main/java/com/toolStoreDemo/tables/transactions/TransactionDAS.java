package com.toolStoreDemo.tables.transactions;

import com.toolStoreDemo.model.transaction.Transaction;
import com.toolStoreDemo.tables.base.FirebaseDAO;

/**
 * Data Access Service used to manage Transaction database records in Firebase
 */
public class TransactionDAS extends FirebaseDAO<Transaction> implements TransactionDAO {
    // Table name in Firebase
    static final String collection = "transaction";

    public TransactionDAS() {
        super(collection, Transaction.class);
    }
}
