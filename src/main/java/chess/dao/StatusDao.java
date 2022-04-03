package chess.dao;

import chess.domain.GameStatus;
import chess.domain.chesspiece.Color;
import chess.dto.StatusDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusDao {

    public int save(final GameStatus gameStatus, final Color currentTurn) {
        final String sql = "INSERT INTO Status(GameStatus, CurrentTurn) VALUES (?, ?)";

        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, gameStatus.getValue());
            statement.setString(2, currentTurn.getValue());

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete() {
        final String sql = "DELETE FROM Status";

        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public StatusDto find() {
        final String sql = "SELECT * FROM Status";

        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql);
             final ResultSet resultSet = statement.executeQuery()) {

            if (!resultSet.next()) {
                return null;
            }
            return StatusDto.of(
                    resultSet.getString("GameStatus"),
                    resultSet.getString("CurrentTurn")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int update(final GameStatus gameStatus, final Color currentTurn) {
        delete();
        return save(gameStatus, currentTurn);
    }
}
