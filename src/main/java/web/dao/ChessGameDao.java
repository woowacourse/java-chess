package web.dao;

import chess.Score;
import chess.piece.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import web.dto.ChessGameDto;
import web.dto.GameStatus;

public class ChessGameDao {

    private final JdbcTemplate jdbcTemplate;

    public ChessGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ChessGameDto> findAll() {
        return jdbcTemplate.query(
                "SELECT id, name, status, current_color, black_score, white_score FROM chess_game",
                this::createChessGameDto);
    }

    private ChessGameDto createChessGameDto(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        GameStatus status = GameStatus.valueOf(rs.getString("status"));
        Color currentColor = Color.valueOf(rs.getString("current_color"));
        Score blackScore = new Score(rs.getBigDecimal("black_score"));
        Score whiteScore = new Score(rs.getBigDecimal("white_score"));
        return new ChessGameDto(id, name, status, blackScore, whiteScore, currentColor);
    }

    public ChessGameDto findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT id, name, status, current_color, black_score, white_score FROM chess_game WHERE id = ?",
                this::createChessGameDto, id);
    }

    public void saveChessGame(String name, GameStatus status, Color currentColor, Score blackScore, Score whiteScore) {
        jdbcTemplate.update(
                "INSERT INTO chess_game(name, status, current_color, black_score, white_score) VALUES(?, ?, ?, ?, ?)",
                name, status.name(), currentColor.name(),
                blackScore.getValue().toPlainString(), whiteScore.getValue().toPlainString());
    }

    public void updateChessGame(ChessGameDto chessGameDto) {
        jdbcTemplate.update(
                "UPDATE chess_game SET status=?, current_color=?, black_score=?, white_score=? WHERE id = ?",
                chessGameDto.getStatus(), chessGameDto.getCurrentColor(),
                chessGameDto.getBlackScore().getValue().toPlainString(),
                chessGameDto.getWhiteScore().getValue().toPlainString(), chessGameDto.getId());
    }

    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM chess_game");
    }
}
