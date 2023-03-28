package common;

public interface JdbcContext {
    <T> T workWithTransaction(final TransactionStrategy<T> transactionStrategy);
}
