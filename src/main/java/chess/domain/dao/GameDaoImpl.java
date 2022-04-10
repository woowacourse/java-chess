package chess.domain.dao;

import chess.dto.GameDto;
import chess.domain.entity.Game;
import chess.util.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDaoImpl implements GameDao {

    private final Connection connection;

    public GameDaoImpl() {
        this(DbConnection.getConnection());
    }

    public GameDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long save(GameDto gameDto) {
        final String sql = "INSERT INTO chess_game(game_state, game_turn) values (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, new String[]{"id"})) {
            statement.setString(1, gameDto.getState());
            statement.setString(2, gameDto.getTurn());
            statement.execute();

            Long id = null;
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            return id;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Game findGameById(Long id) {
        final String sql = "SELECT * from chess_game WHERE game_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return new Game(
                    rs.getLong("game_id"),
                    rs.getString("game_state"),
                    rs.getString("game_turn"));
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Game findGameByMaxId() {
        final String sql = "SELECT * FROM chess_game WHERE game_id IN (SELECT MAX(game_id) FROM chess_game)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            final ResultSet rs = statement.executeQuery(sql);
            if (!rs.next()) {
                return null;
            }
            return new Game(
                    rs.getLong("game_id"),
                    rs.getString("game_state"),
                    rs.getString("game_turn"));
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateByGame(GameDto gameDto) {
        final String sql = "UPDATE chess_game SET game_state = ?, game_turn = ? WHERE game_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, gameDto.getState());
            statement.setString(2, gameDto.getTurn());
            statement.setLong(3, gameDto.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
