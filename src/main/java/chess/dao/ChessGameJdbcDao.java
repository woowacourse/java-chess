package chess.dao;

import chess.domain.position.Position;
import chess.service.Move;
import java.util.ArrayList;
import java.util.List;

public class ChessGameJdbcDao implements ChessGameDao {

    private static final int NOT_EXIST_GAME = -1;
    private final JdbcTemplate jdbcTemplate;

    public ChessGameJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveMove(Move move, int gameId) {
        var query = "INSERT INTO move_history(source, target, move_time, game_id) VALUES(?, ?, ?, ?)";
        jdbcTemplate.executeUpdate(query, parsePosition(move.getSource()), parsePosition(move.getTarget()),
                move.getMoveTime(), gameId);
    }

    private String parsePosition(Position position) {
        return position.getFileCoordinate().name().toLowerCase() + position.getRankCoordinate().getRowNumber();
    }

    @Override
    public List<Move> findByGameId(int gameId) {
        var query = "SELECT * FROM move_history WHERE game_id = (?)";
        return jdbcTemplate.executeQuery(query, resultSet -> {
            List<Move> moveHistories = new ArrayList<>();
            while (resultSet.next()) {
                moveHistories.add(Move.of(
                        resultSet.getString("source"),
                        resultSet.getString("target"),
                        resultSet.getTimestamp("move_time").toLocalDateTime()
                ));
            }
            return moveHistories;
        }, gameId);
    }

    @Override
    public int findGameIdByNotFinished() {
        var query = "SELECT id FROM game WHERE finished = false";
        return jdbcTemplate.executeQuery(query, resultSet -> {
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
            return NOT_EXIST_GAME;
        });
    }

    @Override
    public void saveGame() {
        var query = "INSERT INTO game(finished) VALUES (false)";
        jdbcTemplate.executeUpdate(query);
    }

    @Override
    public void finishedGame() {
        var query = "UPDATE game SET finished = true WHERE finished = false";
        jdbcTemplate.executeUpdate(query);
    }

    @Override
    public boolean isExistNotFinishedGame() {
        var query = "SELECT EXISTS(SELECT * FROM game WHERE finished = false)";
        return jdbcTemplate.executeQuery(query, resultSet -> {
            if (resultSet.next()) {
                return resultSet.getBoolean(1);
            }
            return false;
        });
    }
}

