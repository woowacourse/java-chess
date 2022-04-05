package chess.dao;

import chess.domain.piece.Color;
import chess.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnDao {

    public void save(Color color, int gameNumber) {
        String sql = "insert into turn (game_number, color) values (?,?)";
        try (Connection connection = JdbcConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, gameNumber);
            statement.setString(2, color.name());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Color color, int gameNumber) {
        String sql = "update turn set color = ? where game_number = ?";
        try (Connection connection = JdbcConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, color.name());
            statement.setInt(2, gameNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Color findByGameNumber(int gameNumber) {
        String sql = "select * from turn where game_number = ?";
        try (Connection connection = JdbcConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, gameNumber);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return Color.nameOf(resultSet.getString("color"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void delete() {
        String sql = "delete from turn where 1";
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByGameNumber(int gameNumber) {
        String sql = "delete from turn where game_number = ?";
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, gameNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
