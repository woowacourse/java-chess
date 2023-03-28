package common;

import java.util.function.Supplier;

public interface JdbcContext {

    <T> T workWithTransaction(final TransactionStrategy<T> transactionStrategy);

    <T> void makeTransactionUnit(final Supplier<T> supplier);
}
