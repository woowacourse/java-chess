package chess.infra.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {

    private final ConnectionPool connectionPool;

    public JdbcTemplate(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public void executeUpdate(String query, Object... parameters) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            for (int i = 1; i <= parameters.length; i++) {
                preparedStatement.setObject(i, parameters[i - 1]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalArgumentException("입력이 올바르지 않습니다.");
        }
    }

    public <T> T query(String query, RowMapper<T> rowMapper, Object... parameters) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            for (int i = 1; i <= parameters.length; i++) {
                preparedStatement.setObject(i, parameters[i - 1]);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            return rowMapper.mapRow(resultSet);
        } catch (SQLException e) {
            throw new IllegalArgumentException("입력이 올바르지 않습니다.");
        }
    }

    private Connection getConnection() {
        return connectionPool.getConnection();
    }
}
