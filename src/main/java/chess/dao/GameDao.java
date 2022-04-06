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
        final Connection connection = getConnection();
        final String sql = "select id from game order by id DESC LIMIT 1";

        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return 0;
            }
            return resultSet.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public GameDto findGameState() {
        final Connection connection = getConnection();
        final String sql = "select * from game order by id DESC LIMIT 1";

        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new GameDto(resultSet.getString("state"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(final GameDto gameDto) {
        final Connection connection = getConnection();
        final String sql = "insert into game (state) values (?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, gameDto.getState());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(final GameDto gameDto, int gameId) {
        final Connection connection = getConnection();
        final String sql = "update game set state = (?) where id = (?)";

        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, gameDto.getState());
            statement.setInt(2, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        final Connection connection = getConnection();
        final String sql = "delete from game";

        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
