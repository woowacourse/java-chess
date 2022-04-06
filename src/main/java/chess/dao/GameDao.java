package chess.dao;

import static chess.dao.DBConnector.getConnection;

import chess.dto.GameDto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDao {

    public void save(GameDto gameDto) {
        final String sql = "insert into game (white_user_name, black_user_name, state) values (?, ?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, gameDto.getWhiteUserName());
            statement.setString(2, gameDto.getBlackUserName());
            statement.setString(3, gameDto.getState());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public int findGameIdByUserName(String whiteUserName, String blackUserName) {
        final String sql = "select id from game where white_user_name = (?) and black_user_name = (?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, whiteUserName);
            statement.setString(2, blackUserName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Integer.parseInt(resultSet.getString("id"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return 0;
    }

    public GameDto findById(int gameId) {
        final String sql = "select * from game where id = (?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, gameId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new GameDto(
                            resultSet.getString("white_user_name"),
                            resultSet.getString("black_user_name"),
                            resultSet.getString("state"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    public void update(String state, int gameId) {
        final String sql = "update game set state = (?) where id = (?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, state);
            statement.setInt(2, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteById(int gameId) {
        final String sql = "delete from game where id = (?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
