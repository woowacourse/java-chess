package chess.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class JdbcTemplate {

    private final Connection connection;

    public JdbcTemplate() {
        connection = ConnectionManager.getConection();
    }

    public void executeUpdate(String sql) {
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            setParameters(statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public abstract void setParameters(PreparedStatement statement) throws SQLException;
}
