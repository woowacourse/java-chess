package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatementExecutor {

    private static final String SQL_INIT_EXCEPTION_MESSAGE = "SQL문 생성 작업에 실패하였습니다.";
    private static final String QUERY_EXCEPTION_MESSAGE = "데이터 조회 작업에 실패하였습니다.";
    private static final String COMMAND_EXCEPTION_MESSAGE = "데이터 변경 작업에 실패하였습니다.";

    private final PreparedStatement statement;
    private int parameterIndex = 1;

    public StatementExecutor(String sql) {
        try {
            final Connection connection = ConnectionManager.getConnection();
            this.statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new IllegalStateException(SQL_INIT_EXCEPTION_MESSAGE);
        }
    }

    public StatementExecutor(String sql, int retrieveOption) {
        try {
            final Connection connection = ConnectionManager.getConnection();
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
            return ResultReader.ofQuery(statement);
        } catch (SQLException e) {
            throw new IllegalStateException(QUERY_EXCEPTION_MESSAGE);
        }
    }

    public void executeCommand() {
        try (statement) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(COMMAND_EXCEPTION_MESSAGE);
        }
    }

    public ResultReader executeCommandAndGetGeneratedKeys() {
        try {
            statement.executeUpdate();
            return ResultReader.ofGeneratedKey(statement);
        } catch (SQLException e) {
            throw new IllegalStateException(COMMAND_EXCEPTION_MESSAGE);
        }
    }
}
