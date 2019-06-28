package chess.dao;

import chess.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcTemplate {
    public void executeUpdate(String sql, PreparedStatementSetter pss) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            pss.setParameters(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public <T> List<T> executeQuery(String sql, RowMapper rm) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return rm.mapRow(resultSet);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
