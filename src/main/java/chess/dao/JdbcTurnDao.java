package chess.dao;

import static chess.dao.JdbcGameStatusDao.*;

import chess.domain.piece.Team;
import chess.util.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTurnDao implements TurnDao {

    private final Connection connection = JdbcConnector.getConnection();

    @Override
    public void update(String nowTurn, String nextTurn) {
        String sql = "update turn set team = ? where team = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(PARAMETER_FIRST_INDEX, nextTurn);
            preparedStatement.setString(PARAMETER_SECOND_INDEX, nowTurn);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getTurn() {
        final String sql = "select * from turn";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("team");
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
        final String sql = "insert into turn (team) values (?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(PARAMETER_FIRST_INDEX, Team.WHITE.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void removeAll() {
        String sql = "truncate table turn";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
