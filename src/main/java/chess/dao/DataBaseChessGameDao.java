package chess.dao;

import chess.domain.board.Turn;
import chess.domain.piece.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBaseChessGameDao implements ChessGameDao {

    @Override
    public List<Long> findAllId() {
        final String findAllGameId = "SELECT id FROM chess_game";
        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(findAllGameId)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            final List<Long> chessGameIds = new ArrayList<>();
            while (resultSet.next()) {
                final long chessGameId = resultSet.getLong(1);
                chessGameIds.add(chessGameId);
            }
            return chessGameIds;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long generateNewGame() {
        final String generateNewGameQuery = "INSERT INTO CHESS_GAME VALUES (null, ?)";
        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement preparedStatement =
                     connection.prepareStatement(generateNewGameQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, Color.WHITE.name());
            preparedStatement.executeUpdate();
            final ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
            throw new SQLException();
        } catch (final SQLException e) {
            throw new IllegalArgumentException("게임이 생성되지 않습니다.");
        }
    }

    @Override
    public void updateTurn(final Turn turn, final long gameId) {
        final String generateNewGameQuery = "UPDATE CHESS_GAME SET TURN = ? WHERE id = ?";
        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement preparedStatement =
                     connection.prepareStatement(generateNewGameQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, turn.getTurn().name());
            preparedStatement.setLong(2, gameId);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalArgumentException("게임이 생성되지 않습니다.");
        }
    }
}
