package repository;

import domain.ChessGameResult;
import domain.WinStatus;
import domain.player.Player;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessResultDao {

    private final Connection connection;

    public ChessResultDao(final Connection connection) {
        this.connection = connection;
    }

    public void create(final ChessGameResult chessGameResult, final int gameId) {
        final var query = "INSERT INTO result VALUES(null, ?," +
                "(SELECT black_player_id FROM game WHERE id = ?), " +
                "(SELECT white_player_id FROM game WHERE id = ?), " +
                "?, ?, ?)";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameId);
            preparedStatement.setInt(2, gameId);
            preparedStatement.setInt(3, gameId);
            preparedStatement.setDouble(4, chessGameResult.getBlackScore());
            preparedStatement.setDouble(5, chessGameResult.getWhiteScore());
            preparedStatement.setString(6, chessGameResult.getWinStatus().name());

            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<WinStatus> findBlackWinStatus(final Player player) {
        final var query = "SELECT win_status FROM result AS R " +
                "LEFT JOIN player AS P ON R.black_player_id = P.id " +
                "WHERE P.name = (?)";

        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, player.getName());

            final var resultSet = preparedStatement.executeQuery();

            final List<WinStatus> statuses = new ArrayList<>();

            while (resultSet.next()) {
                statuses.add(WinStatus.valueOf(resultSet.getString("win_status")));
            }
            return statuses;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<WinStatus> findWhiteWinStatus(final Player player) {
        final var query = "SELECT win_status FROM result AS R " +
                "LEFT JOIN player AS P ON R.white_player_id = P.id " +
                "WHERE P.name = (?)";

        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, player.getName());

            final var resultSet = preparedStatement.executeQuery();

            final List<WinStatus> statuses = new ArrayList<>();

            while (resultSet.next()) {
                statuses.add(WinStatus.valueOf(resultSet.getString("win_status")));
            }
            return statuses;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
