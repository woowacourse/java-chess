package chess.db.dao;

import chess.db.DBConnection;
import chess.domain.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnDao {
    public void insert(final Team team) {
        final String query = "INSERT INTO turn (team) VALUES(?)";
        try (final Connection connection = DBConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, team.name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Team select() {
        final String query = "SELECT * FROM turn ORDER BY id DESC LIMIT 1";
        final Team team = Team.EMPTY;
        try (final Connection connection = DBConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Team.valueOf(resultSet.getString("team"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return team;
    }

    public void delete() {
        final String query = "DELETE FROM turn";
        try (final Connection connection = DBConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
