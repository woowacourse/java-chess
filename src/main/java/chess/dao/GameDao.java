package chess.dao;

import chess.domain.board.Turn;
import chess.domain.piece.Color;
import chess.dto.GameDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GameDao {

    private final ConnectionConfig connectionConfig = new ConnectionConfig();

    public int create(final GameDto gameDto) {
        final String query = "INSERT INTO game(turn, is_running) VALUES (?, ?)";
        try (final var connection = connectionConfig.getConnection();
             final var preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, gameDto.getTurn());
            preparedStatement.setBoolean(2, gameDto.getIsRunning());
            preparedStatement.executeUpdate();
            final ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public List<Integer> findAllIdsByIsRunning(final GameDto gameDto) {
        String query = "SELECT id FROM game WHERE is_running = ?";
        List<Integer> ids = new ArrayList<>();
        try (final var connection = connectionConfig.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBoolean(1, gameDto.getIsRunning());
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ids.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ids;
    }

    public Turn findTurnById(final GameDto gameDto) {
        String query = "SELECT turn FROM game WHERE id = ?";
        try (final var connection = connectionConfig.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameDto.getId());
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Turn(Color.ofString(resultSet.getString("turn")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void updateTurn(final GameDto gameDto) {
        final String query = "UPDATE game SET turn = ? WHERE id = ?";
        try (final var connection = connectionConfig.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, gameDto.getTurn());
            preparedStatement.setInt(2, gameDto.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateIsRunning(final GameDto gameDto) {
        final String query = "UPDATE game SET is_running = ? WHERE id = ?";
        try (final var connection = connectionConfig.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBoolean(1, gameDto.getIsRunning());
            preparedStatement.setInt(2, gameDto.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(final int id) {
        final String query = "DELETE FROM game WHERE id = ?";
        try (final var connection = connectionConfig.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
