package chess.db.dao;

import static chess.util.DatabaseUtil.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryBuilder {

    private static final String QUERY_EXCEPTION_MESSAGE = "데이터 조회 작업에 실패하였습니다.";

    private final Connection connection = getConnection();
    private final PreparedStatement statement;
    private int parameterIndex = 1;

    public QueryBuilder(String sql) {
        try {
            this.statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new IllegalStateException(QUERY_EXCEPTION_MESSAGE);
        }
    }

    public QueryBuilder setString(Object parameter) {
        try {
            String stringValue = parameter.toString();
            statement.setString(parameterIndex++, stringValue);
            return this;
        } catch (SQLException e) {
            throw new IllegalStateException(QUERY_EXCEPTION_MESSAGE);
        }
    }

    public QueryBuilder setInt(int parameter) {
        try {
            statement.setInt(parameterIndex++, parameter);
            return this;
        } catch (SQLException e) {
            throw new IllegalStateException(QUERY_EXCEPTION_MESSAGE);
        }
    }
    public QueryReader execute() {
        try {
            return new QueryReader(statement.executeQuery(), connection);
        } catch (SQLException e) {
            throw new IllegalStateException(QUERY_EXCEPTION_MESSAGE);
        }
    }
}
