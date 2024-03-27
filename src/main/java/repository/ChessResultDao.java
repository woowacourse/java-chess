package repository;

import domain.ChessGameResult;
import domain.player.PlayerName;

import java.sql.Connection;
import java.sql.SQLException;

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

    public int countWin(final PlayerName name) {
        final var query = "SELECT count(*) AS win_count FROM result AS R " +
                "LEFT JOIN player AS BP ON R.black_player_id = BP.id " +
                "LEFT JOIN player AS WP ON R.white_player_id = WP.id " +
                "WHERE (BP.name = (?) AND R.win_status = 'BLACK_WIN') " +
                "OR (WP.name = (?) AND R.win_status = 'WHITE_WIN')";


        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name.getName());
            preparedStatement.setString(2, name.getName());

            final var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("win_count");
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public int countLose(final PlayerName name) {
        final var query = "SELECT count(*) AS lose_count FROM result AS R " +
                "LEFT JOIN player AS BP ON R.black_player_id = BP.id " +
                "LEFT JOIN player AS WP ON R.white_player_id = WP.id " +
                "WHERE (BP.name = (?) AND R.win_status = 'WHITE_WIN') " +
                "OR (WP.name = (?) AND R.win_status = 'BLACK_WIN')";


        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name.getName());
            preparedStatement.setString(2, name.getName());

            final var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("lose_count");
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public int countDraw(final PlayerName name) {
        final var query = "SELECT count(*) AS draw_count FROM result AS R " +
                "LEFT JOIN player AS BP ON R.black_player_id = BP.id " +
                "LEFT JOIN player AS WP ON R.white_player_id = WP.id " +
                "WHERE (R.win_status = 'DRAW') " +
                "AND (WP.name = (?) OR BP.name = (?))";


        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name.getName());
            preparedStatement.setString(2, name.getName());

            final var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("draw_count");
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
