package web.dao;

import chess.Score;
import chess.piece.Color;
import java.util.List;
import java.util.Locale;
import web.dto.ChessGameDto;
import web.dto.GameStatus;

public class ChessGameDao {

    private final JdbcTemplate jdbcTemplate;

    public ChessGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ChessGameDto> findAll() {
        return jdbcTemplate.query("SELECT id, name FROM chess_game", rs -> {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            return new ChessGameDto(id, name);
        });
    }

    public GameStatus findGameStatus(int id) {
        return jdbcTemplate.queryForObject("SELECT status FROM chess_game WHERE id = ?",
                rs -> GameStatus.valueOf(rs.getString("status")), id);
    }

    public void saveChessGame(String name, GameStatus status, Color color, Score blackScore, Score whiteScore) {
        jdbcTemplate.update(
                "INSERT INTO chess_game(name, status, current_color, black_score, white_score) VALUES(?, ?, ?, ?, ?)",
                name, status.name(), color.name(),
                blackScore.getValue().toPlainString(), whiteScore.getValue().toPlainString());
    }

    public void updateChessGame(int id, GameStatus status, Color color, Score blackScore, Score whiteScore) {
        jdbcTemplate.update(
                "UPDATE chess_game SET status=?, current_color=?, black_score=?, white_score=? WHERE id = ?",
                status.name(), color.name(), blackScore.getValue().toPlainString(),
                whiteScore.getValue().toPlainString(), id);
    }

    public void updateGameStatus(int id, GameStatus status) {
        jdbcTemplate.update("UPDATE chess_game SET status = ? WHERE id = ?", status.name(), id);
    }

    public Color findWinner(int id) {
        return jdbcTemplate.queryForObject("SELECT winner FROM chess_game WHERE id = ?",
                rs -> Color.valueOf(rs.getString("winner")), id);
    }

    public void updateWinner(int id, Color color) {
        jdbcTemplate.update("UPDATE chess_game SET winner = ? WHERE id = ?", color.name(), id);
    }

    public Color findCurrentColor(int id) {
        return jdbcTemplate.queryForObject("SELECT current_color FROM chess_game WHERE id = ?",
                rs -> Color.valueOf(rs.getString("current_color")), id);
    }

    public void updateCurrentColor(int id, Color color) {
        jdbcTemplate.update("UPDATE chess_game SET current_color = ? WHERE id = ?", color.name(), id);
    }

    public Score findScoreByColor(int id, Color color) {
        String column = String.format("%s_score", color.name().toLowerCase(Locale.ENGLISH));
        String sql = String.format("SELECT %s FROM chess_game WHERE id = ?", column);
        return jdbcTemplate.queryForObject(sql, rs -> new Score(rs.getBigDecimal(column)), id);
    }

    public void updateScoreByColor(int id, Score score, Color color) {
        String column = String.format("%s_score", color.name().toLowerCase(Locale.ENGLISH));
        String sql = String.format("UPDATE chess_game SET %s = ? WHERE id = ?", column);
        jdbcTemplate.update(sql, score.getValue().toPlainString(), id);
    }

    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM chess_game");
    }
}
