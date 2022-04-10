package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultReader implements AutoCloseable {

    private static final String READ_RESULT_EXCEPTION_MESSAGE = "잘못된 방식으로 조회 결과를 작업하였습니다.";
    private static final String RESOURCE_CLOSE_FAILURE_EXCEPTION_MESSAGE = "자원 해제 과정에 문제가 발생하였습니다.";

    private final Statement statement;
    private final ResultSet resultSet;

    private ResultReader(Statement statement, ResultSet resultSet) {
        this.statement = statement;
        this.resultSet = resultSet;
    }

    public static ResultReader ofQuery(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        return new ResultReader(statement, resultSet);
    }

    public static ResultReader ofGeneratedKey(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.getGeneratedKeys();
        return new ResultReader(statement, resultSet);
    }

    public boolean hasNextRow() {
        try {
            return resultSet.next();
        } catch (SQLException e) {
            throw new IllegalStateException(READ_RESULT_EXCEPTION_MESSAGE);
        }
    }

    public ResultReader nextRow() {
        try {
            resultSet.next();
            return this;
        } catch (SQLException e) {
            throw new IllegalStateException(READ_RESULT_EXCEPTION_MESSAGE);
        }
    }

    public String readStringAt(String columnLabel) {
        try {
            return resultSet.getString(columnLabel);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException(READ_RESULT_EXCEPTION_MESSAGE);
        }
    }

    public int readFirstColumn() {
        try {
            nextRow();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new IllegalStateException(READ_RESULT_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public void close() {
        try {
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new IllegalStateException(RESOURCE_CLOSE_FAILURE_EXCEPTION_MESSAGE);
        }
    }
}