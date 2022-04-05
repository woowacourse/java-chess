package chess.dao;

import chess.db.MySqlConnector;
import chess.dto.GameDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GameDao {

    private final Connection connection;

    public GameDao() {
        this(MySqlConnector.getConnection());
    }

    public GameDao(final Connection connection) {
        this.connection = connection;
    }

    public void save(final GameDto game) {
        final String sql = "INSERT INTO game (state, turn) values (?, ?)";

        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, game.getState());
            statement.setString(2, game.getTurn());
            statement.execute();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    public GameDto findById(final int id) {
        final String sql = "SELECT * FROM game WHERE id = ?";

        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            final ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }
            return new GameDto(resultSet.getInt("id"), resultSet.getString("state"), resultSet.getString("turn"));
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateById(final GameDto gameDto) {
        final String sql = "UPDATE game SET state = ?, turn = ? WHERE id =?";

        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, gameDto.getState());
            statement.setString(2, gameDto.getTurn());
            statement.setInt(3, gameDto.getId());
            statement.execute();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    public GameDto findByMaxId() {
        final String sql = "SELECT * FROM game WHERE id IN (SELECT MAX(id) FROM game)";

        try (final Statement statement = connection.createStatement();) {
            final ResultSet resultSet = statement.executeQuery(sql);

            if (!resultSet.next()) {
                return null;
            }
            return new GameDto(resultSet.getInt("id"), resultSet.getString("state"), resultSet.getString("turn"));
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer findMaxId() {
        final String sql = "SELECT MAX(id) AS id FROM game";

        try (final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery(sql);

            if (!resultSet.next()) {
                return null;
            }
            return resultSet.getInt("id");
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
