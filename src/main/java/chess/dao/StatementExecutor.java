package chess.dao;

import static chess.util.DatabaseUtil.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatementExecutor {

    private static final String SQL_INIT_EXCEPTION_MESSAGE = "SQL문 생성 작업에 실패하였습니다.";
    private static final String QUERY_EXCEPTION_MESSAGE = "데이터 조회 작업에 실패하였습니다.";
    private static final String COMMAND_EXCEPTION_MESSAGE = "데이터 변경 작업에 실패하였습니다.";

    private final Connection connection = getConnection();
    private final PreparedStatement statement;
    private int parameterIndex = 1;

    public StatementExecutor(String sql) {
        try {
            this.statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new IllegalStateException(SQL_INIT_EXCEPTION_MESSAGE);
        }
    }

    public StatementExecutor(String sql, int retrieveOption) {
        try {
            this.statement = connection.prepareStatement(sql, retrieveOption);
        } catch (SQLException e) {
            throw new IllegalStateException(SQL_INIT_EXCEPTION_MESSAGE);
        }
    }

    public StatementExecutor setString(Object parameter) {
        try {
            String stringValue = parameter.toString();
            statement.setString(parameterIndex++, stringValue);
            return this;
        } catch (SQLException e) {
            throw new IllegalStateException(SQL_INIT_EXCEPTION_MESSAGE);
        }
    }

    public StatementExecutor setInt(int parameter) {
        try {
            statement.setInt(parameterIndex++, parameter);
            return this;
        } catch (SQLException e) {
            throw new IllegalStateException(SQL_INIT_EXCEPTION_MESSAGE);
        }
    }

    public ResultReader executeQuery() {
        try {
            return new ResultReader(statement.executeQuery(), connection);
        } catch (SQLException e) {
            throw new IllegalStateException(QUERY_EXCEPTION_MESSAGE);
        }
    }

    public void executeCommandAndClose() {
        try (connection) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(COMMAND_EXCEPTION_MESSAGE);
        }
    }

    public ResultReader executeCommandAndGetGeneratedKeys() {
        try {
            statement.executeUpdate();
            return new ResultReader(statement.getGeneratedKeys(), connection);
        } catch (SQLException e) {
            throw new IllegalStateException(COMMAND_EXCEPTION_MESSAGE);
        }
    }
}
