package common;

import java.sql.Connection;
import java.sql.SQLException;

public interface TransactionStrategy<T> {

    T execute(final Connection connection) throws SQLException;
}
