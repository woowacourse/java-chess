package chess.db.dao;

import static chess.util.DatabaseUtil.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommandBuilder {

    private static final String COMMAND_EXCEPTION_MESSAGE = "데이터 변경 작업에 실패하였습니다.";

    private final Connection connection = getConnection();
    private final PreparedStatement statement;
    private int parameterIndex = 1;

    public CommandBuilder(String sql) {
        try {
            this.statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException(COMMAND_EXCEPTION_MESSAGE);
        }
    }

    public CommandBuilder setString(Object parameter) {
        try {
            String stringValue = parameter.toString();
            statement.setString(parameterIndex++, stringValue);
            return this;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException(COMMAND_EXCEPTION_MESSAGE);
        }
    }

    public CommandBuilder setInt(int parameter) {
        try {
            statement.setInt(parameterIndex++, parameter);
            return this;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException(COMMAND_EXCEPTION_MESSAGE);
        }
    }

    public void execute() {
        try {
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException(COMMAND_EXCEPTION_MESSAGE);
        }
    }
}
