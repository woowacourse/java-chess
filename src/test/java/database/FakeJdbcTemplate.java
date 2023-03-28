package database;

import chess.dao.Transaction;

public class FakeJdbcTemplate implements Transaction {
    @Override
    public void batchTransaction(Runnable... transactions) {
        for (Runnable transaction : transactions) {
            transaction.run();
        }
    }
}
