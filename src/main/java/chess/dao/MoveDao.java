package chess.dao;

import chess.dao.connection.ConnectionDriver;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoveDao {
    private final ConnectionDriver connectionDriver;

    public MoveDao() {
        this.connectionDriver = new ConnectionDriver();
    }

    //todo : gameId가 적절한 값이 없는 경우!
    public List<List<String>> findMovesByGameId(final int gameId) {
        String query = "SELECT start_position, end_position FROM move WHERE game_id = ?";

        try (final PreparedStatement preparedStatement = connectionDriver.getConnection().prepareStatement(query)) {
            preparedStatement.setObject(1, gameId);

            final ResultSet resultSet = preparedStatement.executeQuery();
            List<List<String>> moves = new ArrayList<>();
            while (resultSet.next()) {
                moves.add(List.of("move",
                        resultSet.getString("start_position"),
                        resultSet.getString("end_position")));
            }
            return moves;
        } catch (final SQLException e) {
            throw new IllegalArgumentException("게임이 존재하지 않습니다."+e.getMessage());
        }
    }
 
}
