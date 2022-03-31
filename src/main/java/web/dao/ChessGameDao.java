package web.dao;

import chess.piece.Color;
import web.dto.GameStatus;

public class ChessGameDao {

    private static final int CHESS_GAME_ID = 0;
    private final JdbcTemplate jdbcTemplate;

    public ChessGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean existChessGame() {
        return jdbcTemplate.exist("SELECT * FROM ChessGame WHERE id = ?", CHESS_GAME_ID);
    }

    public GameStatus findGameStatus() {
        return jdbcTemplate.queryForObject("SELECT status FROM ChessGame WHERE id = ?",
                rs -> GameStatus.valueOf(rs.getString("status")), CHESS_GAME_ID);
    }

    public void saveGameStatus(GameStatus status, Color color) {
        jdbcTemplate.update("INSERT INTO ChessGame(id, status, currentColor, winner) VALUES(?, ?, ?, NULL)",
                CHESS_GAME_ID, status.name(), color.name());
    }

    public void updateGameStatus(GameStatus status) {
        jdbcTemplate.update("UPDATE ChessGame SET status = ? WHERE id = ?",
                status.name(), CHESS_GAME_ID);
    }

    public Color findWinner() {
        return jdbcTemplate.queryForObject("SELECT winner FROM ChessGame WHERE id = ?",
                rs -> Color.valueOf(rs.getString("winner")), CHESS_GAME_ID);
    }

    public void updateWinner(Color color) {
        jdbcTemplate.update("UPDATE ChessGame SET winner = ? WHERE id = ?",
                color.name(), CHESS_GAME_ID);
    }

    public Color findCurrentColor() {
        return jdbcTemplate.queryForObject("SELECT currentColor FROM ChessGame WHERE id = ?",
                rs -> Color.valueOf(rs.getString("currentColor")), CHESS_GAME_ID);
    }

    public void updateCurrentColor(Color color) {
        jdbcTemplate.update("UPDATE ChessGame SET currentColor = ?", color.name());
    }

    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM ChessGame WHERE id = ?", CHESS_GAME_ID);
    }
}
