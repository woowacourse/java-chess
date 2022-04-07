package chess.dao;

import chess.util.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameStatusDaoImpl implements GameStatusDao {

    private final Connection connection = JdbcConnector.getConnection();

    @Override
    public void update(String nowStatus, String nextStatus) {
        String sql = "update game_status set value = ? where value = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nextStatus);
            preparedStatement.setString(2, nowStatus);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getStatus() {
        final String sql = "select * from game_status";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            return resultSet.getString("value");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void reset() {
        removeAll("game_status");
        final String sql = "insert into game_status value READY";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void removeAll(String name) {
        String sql = "truncate table " + name;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }
    }
}
