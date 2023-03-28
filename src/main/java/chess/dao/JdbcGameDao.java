package chess.dao;

import chess.dto.GameInfoDto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcGameDao implements GameDao {

    @Override
    public List<Integer> findAllPossibleId() {
        final var query = "SELECT game_id FROM game WHERE game_status != ? AND game_status != ?";
        try (final var connection = ConnectionProvider.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "END");
            preparedStatement.setString(2, "CATCH");

            final var resultSet = preparedStatement.executeQuery();
            List<Integer> ids = new ArrayList<>();
            while (resultSet.next()) {
                ids.add(Integer.parseInt(resultSet.getString("game_id")));
            }
            return ids;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> findAllImpossibleId() {
        final var query = "SELECT game_id FROM game WHERE game_status = ? OR game_status = ?";
        try (final var connection = ConnectionProvider.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "END");
            preparedStatement.setString(2, "CATCH");

            final var resultSet = preparedStatement.executeQuery();
            List<Integer> ids = new ArrayList<>();
            while (resultSet.next()) {
                ids.add(Integer.parseInt(resultSet.getString("game_id")));
            }
            return ids;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameInfoDto findById(int gameId) {
        final var gameQuery = "SELECT * FROM game WHERE game_id = ?";
        try (final var connection = ConnectionProvider.getConnection();
             final var preparedStatement = connection.prepareStatement(gameQuery)) {
            preparedStatement.setInt(1, gameId);

            final var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return GameInfoDto.create(resultSet.getString("game_status"), resultSet.getString("game_turn"));
            } else {
                return null;
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(int gameId, GameInfoDto gameInfoDto) {
        final var gameQuery = "INSERT INTO game VALUES(?, ?, ?)";
        try (final var connection = ConnectionProvider.getConnection();
             final var preparedStatement = connection.prepareStatement(gameQuery)) {
            preparedStatement.setInt(1, gameId);
            preparedStatement.setString(2, gameInfoDto.getStatus().name());
            preparedStatement.setString(3, gameInfoDto.getTurn().name());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateById(int gameId, GameInfoDto gameInfoDto) {
        deleteById(gameId);
        save(gameId, gameInfoDto);
    }

    @Override
    public void deleteById(int gameId) {
        final var gameQuery = "DELETE FROM game WHERE game_id = ?";
        try (final var connection = ConnectionProvider.getConnection();
             final var preparedStatement = connection.prepareStatement(gameQuery)) {
            preparedStatement.setInt(1, gameId);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll() {
        final var gameQuery = "DELETE FROM game";
        try (final var connection = ConnectionProvider.getConnection();
             final var preparedStatement = connection.prepareStatement(gameQuery)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
