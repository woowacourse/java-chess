package chess.dao;

import chess.dto.GameDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDao {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public int findGameId() {
        final String sql = "select id from game order by id DESC LIMIT 1";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return 0;
                }
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public GameDto findGameState() {
        final String sql = "select * from game order by id DESC LIMIT 1";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }
                return new GameDto(resultSet.getString("state"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void save(final GameDto gameDto) {
        final String sql = "insert into game (state) values (?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, gameDto.getState());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void update(final GameDto gameDto, int gameId) {
        final String sql = "update game set state = (?) where id = (?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, gameDto.getState());
            statement.setInt(2, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void delete() {
        final String sql = "delete from game";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
