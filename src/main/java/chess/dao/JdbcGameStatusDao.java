package chess.dao;

import chess.domain.GameStatus;
import chess.util.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcGameStatusDao implements GameStatusDao {

    public static final int PARAMETER_FIRST_INDEX = 1;
    public static final int PARAMETER_SECOND_INDEX = 2;

    private final Connection connection = JdbcConnector.getConnection();

    @Override
    public void update(String nowStatus, String nextStatus) {
        String sql = "update game_status set status = ? where status = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(PARAMETER_FIRST_INDEX, nextStatus);
            preparedStatement.setString(PARAMETER_SECOND_INDEX, nowStatus);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getStatus() {
        final String sql = "select * from game_status";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void reset() {
        removeAll();
        final String sql = "insert into game_status (status) values (?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(PARAMETER_FIRST_INDEX, GameStatus.READY.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void removeAll() {
        String sql = "truncate table game_status";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }
    }
}
