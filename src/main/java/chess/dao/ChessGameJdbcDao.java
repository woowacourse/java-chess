package chess.dao;

import chess.dto.MoveDto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessGameJdbcDao implements ChessGameDao {

    @Override
    public void saveMove(MoveDto moveDto) {
        var query = "INSERT INTO move_history(source, target) VALUES(?, ?)";
        try (var connection = ConnectionGenerator.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, moveDto.getSource());
            preparedStatement.setString(2, moveDto.getTarget());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MoveDto> findAll() {
        var query = "SELECT * FROM move_history";
        List<MoveDto> moveHistories = new ArrayList<>();
        try (var connection = ConnectionGenerator.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                moveHistories.add(MoveDto.of(
                        resultSet.getInt("id"),
                        resultSet.getString("source"),
                        resultSet.getString("target")
                ));
            }
            return moveHistories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll() {
        var query = "DELETE FROM move_history";
        try (var connection = ConnectionGenerator.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

