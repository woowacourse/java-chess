package chess.dao;

import chess.db.MySqlConnector;
import chess.dto.GameDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameDao {

    private final Connection connection;

    public GameDao() {
        this(MySqlConnector.getConnection());
    }

    public GameDao(final Connection connection) {
        this.connection = connection;
    }

    public void save(final GameDto game) {
        final String sql = "INSERT INTO game (id, state, turn) values (?, ?, ?)";

        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, game.getId());
            statement.setString(2, game.getState());
            statement.setString(3, game.getTurn());
            statement.execute();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    public GameDto findById(final String id) {
        final String sql = "SELECT * FROM game WHERE id = ?";

        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            final ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }
            return new GameDto(resultSet.getString("id"), resultSet.getString("state"), resultSet.getString("turn"));
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<GameDto> findAll() {
        final String sql = "SELECT * FROM game";

        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            final ResultSet resultSet = statement.executeQuery();
            final List<GameDto> games = new ArrayList<>();

            while (resultSet.next()) {
                games.add(new GameDto(resultSet.getString("id"), resultSet.getString("state"),
                        resultSet.getString("turn")));
            }
            return games;
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
            statement.setString(3, gameDto.getId());
            statement.execute();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }
}
