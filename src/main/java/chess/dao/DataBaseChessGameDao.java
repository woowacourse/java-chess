package chess.dao;

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

    public Long generateNewGame() {
        final String generateNewGameQuery = "INSERT * INTO CHESS_GAME VALUES (WHITE)";
        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement preparedStatement =
                     connection.prepareStatement(generateNewGameQuery, Statement.RETURN_GENERATED_KEYS)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.getLong(1);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
