package chess.dao;

import chess.dao.connection.ConnectionDriver;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MoveDao {
    private final ConnectionDriver connectionDriver;

    public MoveDao() {
        this.connectionDriver = new ConnectionDriver();
    }

    public List<List<String>> findMoves() {
        String query = "SELECT start_position, end_position FROM move";

        try (final PreparedStatement preparedStatement = connectionDriver.getConnection().prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            List<List<String>> moves = new ArrayList<>();
            while (resultSet.next()) {
                moves.add(List.of(
                        resultSet.getString("start_position"),
                        resultSet.getString("end_position")));
            }
            return moves;
        } catch (final SQLException e) {
            throw new IllegalArgumentException("게임이 존재하지 않습니다."+e.getMessage());
        }
    }

    public void save(final String startPosition, final String endPosition) {
        String query = "INSERT INTO move (game_id, start_position, end_position) VALUE (1, ?, ?)";

        try (final PreparedStatement preparedStatement = connectionDriver.getConnection().prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, startPosition);
            preparedStatement.setObject(2, endPosition);
            preparedStatement.executeUpdate();

        } catch (final SQLException e) {
            throw new IllegalArgumentException("INSERT 오류:" + e.getMessage());
        }
    }

    public void deleteAllMoves() {
        String query = "DELETE FROM move";

        try (final PreparedStatement preparedStatement = connectionDriver.getConnection().prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalArgumentException("DELETE 오류:" +e.getMessage());
        }
    }
}
