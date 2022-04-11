package chess.dao;

import chess.dto.TurnFindResponse;
import chess.dto.TurnSaveRequest;
import chess.dto.TurnUpdateRequest;
import chess.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnDao {

    public void save(TurnSaveRequest turnSaveRequest) {
        String sql = "insert into turn (game_number, color) values (?,?)";
        try (Connection connection = JdbcConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, turnSaveRequest.getGameNumber());
            statement.setString(2, turnSaveRequest.getColor().name());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(TurnUpdateRequest turnUpdateRequest) {
        String sql = "update turn set color = ? where game_number = ?";
        try (Connection connection = JdbcConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, turnUpdateRequest.getColor().name());
            statement.setInt(2, turnUpdateRequest.getGameNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TurnFindResponse findByGameNumber(int gameNumber) {
        String sql = "select * from turn where game_number = ?";
        try (Connection connection = JdbcConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, gameNumber);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new TurnFindResponse(resultSet.getString("color"));
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
