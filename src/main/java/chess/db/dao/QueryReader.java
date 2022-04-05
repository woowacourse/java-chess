package chess.db.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryReader implements AutoCloseable {

    private static final String READ_RESULT_EXCEPTION_MESSAGE = "잘못된 방식으로 조회 결과를 작업하였습니다.";

    private final ResultSet resultSet;
    private final Connection connection;

    public QueryReader(ResultSet resultSet, Connection connection) {
        this.resultSet = resultSet;
        this.connection = connection;
    }

    public boolean nextRow() {
        try {
            return resultSet.next();
        } catch (SQLException e) {
            throw new IllegalStateException(READ_RESULT_EXCEPTION_MESSAGE);
        }
    }

    public String readStringAt(String columnLabel) {
        try {
            return resultSet.getString(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(READ_RESULT_EXCEPTION_MESSAGE);
        }
    }

    public String readStringAndClose(String columnLabel) {
        try (connection) {
            return resultSet.getString(columnLabel);
        } catch (SQLException e) {
            throw new IllegalStateException(READ_RESULT_EXCEPTION_MESSAGE);
        }
    }

    public int readCountResultAndClose() {
        try (connection) {
            nextRow();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new IllegalStateException(READ_RESULT_EXCEPTION_MESSAGE);
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException(READ_RESULT_EXCEPTION_MESSAGE);
        }
    }
}