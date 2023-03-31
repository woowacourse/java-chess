package chess.dao;

public interface Transaction {
    void batchTransaction(Runnable... transactions);
}
