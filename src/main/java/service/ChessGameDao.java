package service;

import domain.Team;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChessGameDao {

    private final Connection connection;

    public ChessGameDao(final Connection connection) {
        this.connection = connection;
    }

    public int addGame(final Team currentTeam, final ChessGameStatus chessGameStatus) {
        final var query = "INSERT INTO game VALUES(null, ?, ?)";
        try (final var preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, currentTeam.name());
            preparedStatement.setString(2, chessGameStatus.name());

            preparedStatement.executeUpdate();

            try (final ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return (int) generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating game failed, no ID obtained.");
                }
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Team> findCurrentTeamById(final int id) {
        final var query = "SELECT current_team FROM game WHERE id = (?) AND status = 'RUNNING'";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            final var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(
                        Team.valueOf(resultSet.getString("current_team"))
                );
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    public Optional<ChessGameStatus> findStatus(final int gameId) {
        final var query = "SELECT status FROM game WHERE id = (?)";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameId);

            final var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(
                        ChessGameStatus.valueOf(resultSet.getString("status"))
                );
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    public void updateStatusById(final int id, final ChessGameStatus chessGameStatus) {
        final var query = "UPDATE game SET status = (?)" +
                "WHERE id = (?)";

        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, chessGameStatus.name());
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCurrentTeam(final int gameId, final Team currentTeam) {
        final var query = "UPDATE game SET current_team = (?)" +
                "WHERE id = (?)";

        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, currentTeam.name());
            preparedStatement.setInt(2, gameId);

            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> findRunningGames() {
        final var query = "SELECT id FROM game WHERE status = 'RUNNING'";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();

            final List<Integer> ids = new ArrayList<>();

            while (resultSet.next()) {
                ids.add(resultSet.getInt("id"));
            }
            return ids;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existRunningById(final int gameId) {
        final var query = "SELECT id FROM game " +
                "WHERE status = 'RUNNING' AND id = (?)";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameId);

            final var resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
