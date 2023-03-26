package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static final String DATABASE_CONNECTION_EXCEPTION_MESSAGE = "[ERROR] 데이터베이스 연결 오류";
    private static final String ALREADY_TRANSACTION_EXCEPTION_MESSAGE = "[ERROR] 이미 진행중인 트랜잭션이 있습니다.";
    private static final String INVALID_TRANSACTION_EXCEPTION_MESSAGE = "[ERROR] 진행중인 트랜잭션이 없습니다.";

    private static Connection txConnection;

    private ConnectionProvider() {
    }

    public static Connection getConnection() {
        if (isTransactionActive()) {
            return txConnection;
        }
        try {
            return DriverManager.getConnection(
                    DatabaseConfig.getUrl(),
                    DatabaseConfig.getUsername(),
                    DatabaseConfig.getPassword());
        } catch (SQLException e) {
            throw new IllegalStateException(DATABASE_CONNECTION_EXCEPTION_MESSAGE, e);
        }
    }

    public static boolean isTransactionActive() {
        return txConnection != null;
    }

    public static void startTransaction() {
        if (isTransactionActive()) {
            throw new IllegalStateException(ALREADY_TRANSACTION_EXCEPTION_MESSAGE);
        }
        txConnection = getConnection();
        try {
            txConnection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new IllegalStateException(DATABASE_CONNECTION_EXCEPTION_MESSAGE, e);
        }
    }

    public static void endTransaction() {
        if (!isTransactionActive()) {
            throw new IllegalStateException(INVALID_TRANSACTION_EXCEPTION_MESSAGE);
        }
        try {
            txConnection.close();
        } catch (SQLException e) {
            throw new IllegalStateException(DATABASE_CONNECTION_EXCEPTION_MESSAGE, e);
        } finally {
            txConnection = null;
        }
    }

    public static void commit() {
        if (!isTransactionActive()) {
            throw new IllegalStateException(INVALID_TRANSACTION_EXCEPTION_MESSAGE);
        }
        try {
            txConnection.commit();
        } catch (SQLException e) {
            throw new IllegalStateException(DATABASE_CONNECTION_EXCEPTION_MESSAGE, e);
        }
    }

    public static void rollback() {
        if (!isTransactionActive()) {
            throw new IllegalStateException(INVALID_TRANSACTION_EXCEPTION_MESSAGE);
        }
        try {
            txConnection.rollback();
        } catch (SQLException e) {
            throw new IllegalStateException(DATABASE_CONNECTION_EXCEPTION_MESSAGE, e);
        }
    }

    public static void close(Connection c) {
        if (c != txConnection) {
            closeConnection(c);
        }
    }

    private static void closeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            throw new IllegalStateException(DATABASE_CONNECTION_EXCEPTION_MESSAGE, e);
        }
    }
}
