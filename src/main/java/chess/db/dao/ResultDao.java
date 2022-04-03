package chess.db.dao;

import chess.model.GameResult;
import chess.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultDao {

    private static final String RESULT_NOT_FOUND_EXCEPTION = "해당되는 ID의 게임은 존재하지 않습니다.";

    private final String table;

    public ResultDao(String table) {
        this.table = table;
    }

    public GameResult findByGameId(int gameId) {
        final String sql = "SELECT winner, white_score, black_score "
                + "FROM " + table + " WHERE game_id = " + gameId;
        try (final Connection connection = DatabaseUtil.getConnection()) {
            ResultSet resultSet = DatabaseUtil.getQueryResult(sql, connection);
            if (!resultSet.next()) {
                throw new IllegalArgumentException(RESULT_NOT_FOUND_EXCEPTION);
            }
            return new GameResult(
                    resultSet.getString("winner"),
                    resultSet.getDouble("white_score"),
                    resultSet.getDouble("black_score"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("데이터 조회 작업에 실패하였습니다.");
        }
    }

    public void save(int gameId, GameResult gameResult) {
        final String sql = "INSERT INTO " + table
                + " (game_id,  winner, white_score, black_score) VALUES "
                + "(" + gameId + ", '" + gameResult.getWinner() + "', "
                + gameResult.getWhiteScore()+", "
                + gameResult.getBlackScore()+ ")";
        DatabaseUtil.executeCommand(sql);
    }
}
