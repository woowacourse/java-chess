package chess.infra.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTemplate {

    private final ConnectionPool connectionPool;

    public JdbcTemplate(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public int save(String query, Object... parameters) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 1; i <= parameters.length; i++) {
                preparedStatement.setObject(i, parameters[i - 1]);
            }
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            throw new IllegalArgumentException("입력이 올바르지 않습니다.");
        } catch (SQLException ex) {
            throw new IllegalArgumentException("입력이 올바르지 않습니다.");
        }
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
