package chess.database.dao;

import chess.domain.piece.info.Team;
import chess.domain.position.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLChessGameDao implements ChessGameDao {

    public int findRunningGameId(final Connection connection) {
        final String query = "SELECT game_id FROM game WHERE state='running'";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("game_id");
            }
            return -1;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Path> findAllHistoryById(final int gameId, final Connection connection) {
        final String query = "SELECT source, destination FROM moveHistory WHERE game_id=?";
        try (
            final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameId);

            final var resultSet = preparedStatement.executeQuery();
            List<Path> histories = new ArrayList<>();
            while (resultSet.next()) {
                String source = resultSet.getString("source");
                String destination = resultSet.getString("destination");
                histories.add(new Path(source, destination));
            }
            return histories;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertGame(final Connection connection) {
        final String query = "INSERT INTO game VALUES();";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertMoveHistory(final int gameId, final String source, final String destination,
        final Connection connection) {
        final String query = "INSERT INTO moveHistory(game_id, source, destination) VALUES(?,?,?)";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameId);
            preparedStatement.setString(2, source);
            preparedStatement.setString(3, destination);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStateToFinished(final int gameId, final Team team,
        final Connection connection) {
        final var query = "UPDATE game SET state = 'finished', winner=? WHERE game_id = ?";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, team.toString());
            preparedStatement.setInt(2, gameId);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int selectLastInsertedID(final Connection connection) {
        final String query = "SELECT LAST_INSERT_ID()";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("LAST_INSERT_ID()");
            }
            return -1;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
