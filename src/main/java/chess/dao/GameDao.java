package chess.dao;

import static chess.dao.utils.ConnectionManager.createConnection;

import chess.dao.exception.UncheckedSQLException;
import chess.dao.utils.JDBCHelper;
import chess.domain.team.Team;
import chess.entity.Game;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class GameDao implements GameDaoInterface {

    @Override
    public long insert(final Game game) {
        final String query = "INSERT INTO game(white_id, black_id) VALUES (?, ?)";
        try (
            final Connection connection = createConnection();
            final PreparedStatement pstmt = JDBCHelper.createPreparedStatement(
                connection, query, Arrays.asList(game.getWhiteId(), game.getBlackId())
            )
        ) {
            pstmt.executeUpdate();
            return JDBCHelper.getGeneratedKey(pstmt);
        } catch (final SQLException e) {
            throw new UncheckedSQLException(e.getMessage());
        }
    }

    @Override
    public Optional<Game> selectById(final Long id) {
        final String query = "SELECT * FROM game WHERE id = ?";
        try (
            final Connection connection = createConnection();
            final PreparedStatement pstmt = JDBCHelper.createPreparedStatement(
                connection, query, Collections.singletonList(id)
            );
            final ResultSet resultSet = pstmt.executeQuery()
        ) {
            return makeGame(resultSet);
        } catch (final SQLException e) {
            throw new UncheckedSQLException(e.getMessage());
        }
    }

    private Optional<Game> makeGame(final ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return Optional.empty();
        }
        return Optional.of(
            new Game(
                resultSet.getLong("id"),
                resultSet.getLong("white_id"),
                resultSet.getLong("black_id"),
                Team.from(resultSet.getString("turn")),
                resultSet.getBoolean("is_finished"),
                resultSet.getTimestamp("created_time").toLocalDateTime()
            )
        );
    }
}
