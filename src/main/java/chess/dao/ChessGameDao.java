package chess.dao;

import chess.dao.connection.ConnectionDriver;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ChessGameDao {

    private final ConnectionDriver connectionDriver;

    public ChessGameDao() {
        this.connectionDriver = new ConnectionDriver();
    }

    public Optional<Integer> findLastGameIdByStatus(String status, String status2) {
        String query = "SELECT * FROM game WHERE status = ? or status = ? ORDER BY game_id DESC LIMIT 1";

        try (final PreparedStatement preparedStatement = connectionDriver.getConnection().prepareStatement(query)) {
            preparedStatement.setObject(1, status);
            preparedStatement.setObject(2, status2);

            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(resultSet.getInt(1));
        } catch (final SQLException e) {
            throw new IllegalArgumentException("게임이 존재하지 않습니다."+ e.getMessage());
        }
    }
}
