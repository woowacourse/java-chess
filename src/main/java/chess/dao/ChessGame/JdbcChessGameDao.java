package chess.dao.ChessGame;

import chess.dao.ChessGame.dto.FindResponseDto;
import chess.dao.ChessGame.dto.SaveRequestDto;
import chess.dao.JdbcConnection;
import chess.domain.game.Turn;

import java.sql.*;

public class JdbcChessGameDao implements ChessGameDao {
    private static final long TEMPORAL_ID = 1;

    private final Connection connection = JdbcConnection.getConnection();

    @Override
    public Long save(final SaveRequestDto saveRequestDto) {
        final String query = "INSERT INTO chess_game VALUES (?, ?)";
        try (final PreparedStatement preparedStatement =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, TEMPORAL_ID);
            preparedStatement.setString(2, saveRequestDto.getTurn().name());
            preparedStatement.executeUpdate();
            return getLastId(preparedStatement);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private long getLastId(final PreparedStatement preparedStatement) throws SQLException {
        final ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        return resultSet.getLong(1);
    }

    @Override
    public FindResponseDto findById(final long id) {
        final String query = "SELECT * FROM chess_game WHERE id = ?";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);

            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new FindResponseDto(
                        resultSet.getLong("id"),
                        Turn.valueOf(resultSet.getString("turn"))
                );
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void updateTurn(final long id, final Turn turn) {
        final String query = "UPDATE chess_game SET turn = ? WHERE id = ?";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, turn.name());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete() {
        final String query = "DELETE FROM chess_game";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
