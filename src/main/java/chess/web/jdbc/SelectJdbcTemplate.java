package chess.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public abstract class SelectJdbcTemplate {

    private final Connection connection;

    public SelectJdbcTemplate() {
        connection = ConnectionManager.getConection();
    }

    public Object executeQuery(String sql) {
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            setParameters(statement);

            final ResultSet resultSet = statement.executeQuery();

            return mapRow(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public abstract void setParameters(PreparedStatement statement) throws SQLException;

    public abstract Object mapRow(ResultSet resultSet) throws SQLException;
}
