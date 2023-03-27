package chess.dao;

import chess.dto.MoveDto;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChessGameJdbcDao implements ChessGameDao {

    private static final int NOT_EXIST_GAME = -1;

    @Override
    public void saveMove(MoveDto moveDto, int gameId) {
        LocalDateTime now = LocalDateTime.now();
        var query = "INSERT INTO move_history(source, target, move_time, game_id) VALUES(?, ?, ?, ?)";
        try (var connection = ConnectionGenerator.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, moveDto.getSource());
            preparedStatement.setString(2, moveDto.getTarget());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(now));
            preparedStatement.setInt(4, gameId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MoveDto> findByGameId(int gameId) {
        var query = "SELECT * FROM move_history WHERE game_id = (?)";
        List<MoveDto> moveHistories = new ArrayList<>();
        try (var connection = ConnectionGenerator.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameId);
            var resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                moveHistories.add(MoveDto.of(
                        resultSet.getInt("id"),
                        resultSet.getString("source"),
                        resultSet.getString("target"),
                        resultSet.getTimestamp("move_time").toLocalDateTime()
                ));
            }
            return moveHistories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int findGameIdByNotFinished() {
        var query = "SELECT id FROM game WHERE finished = false";
        try (var connection = ConnectionGenerator.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
            return NOT_EXIST_GAME;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveGame() {
        var query = "INSERT INTO game(finished) VALUES (false)";
        try (var connection = ConnectionGenerator.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void finishedGame() {
        var query = "UPDATE game SET finished = true WHERE finished = false";
        try (var connection = ConnectionGenerator.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

